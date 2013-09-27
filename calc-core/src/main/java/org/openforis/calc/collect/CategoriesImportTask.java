package org.openforis.calc.collect;

import static org.openforis.calc.persistence.jooq.tables.CategoryTable.CATEGORY;
import static org.openforis.calc.persistence.jooq.tables.EntityTable.ENTITY;
import static org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Insert;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Select;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.SchemaImpl;
import org.jooq.impl.TableImpl;
import org.openforis.calc.engine.Task;
import org.openforis.calc.engine.Workspace;
import org.openforis.calc.engine.WorkspaceService;
import org.openforis.calc.metadata.BinaryVariable;
import org.openforis.calc.metadata.CategoricalVariable;
import org.openforis.calc.metadata.Category;
import org.openforis.calc.metadata.CategoryDao;
import org.openforis.calc.metadata.Entity;
import org.openforis.calc.metadata.MultiwayVariable;
import org.openforis.calc.metadata.Variable;
import org.openforis.calc.schema.AbstractTable;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.relational.CollectRdbException;
import org.openforis.collect.relational.model.CodeColumn;
import org.openforis.collect.relational.model.CodeLabelColumn;
import org.openforis.collect.relational.model.CodeListCodeColumn;
import org.openforis.collect.relational.model.CodeListDescriptionColumn;
import org.openforis.collect.relational.model.CodeTable;
import org.openforis.collect.relational.model.Column;
import org.openforis.collect.relational.model.DataColumn;
import org.openforis.collect.relational.model.RelationalSchema;
import org.openforis.collect.relational.model.RelationalSchemaGenerator;
import org.openforis.collect.relational.model.Table;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.CodeAttributeDefinition;
import org.openforis.idm.metamodel.CodeList;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.metamodel.TaxonAttributeDefinition;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author S. Ricci
 * @author G. Miceli
 *
 */
public class CategoriesImportTask extends Task {

	@Autowired
	private WorkspaceService workspaceService;
	
	@Autowired
	private CategoryDao categoryDao;
	
	private RelationalSchema inputRelationalSchema;

	@Override
	protected void execute() throws Throwable {
		deleteCategories();
		
		generateSchema();
		
		insertCategories();
	}

	private void insertCategories() {
		List<Variable<?>> vars = getVariables();
		for (Variable<?> v : vars) {
			if ( v instanceof MultiwayVariable ) {
				DataColumn valueRDBColumn = getRDBDataColumn((CategoricalVariable<?>) v);
				if ( valueRDBColumn instanceof CodeColumn ) {
					CodeTable rdbCodeTable = getRDBCodeTable((CodeColumn) valueRDBColumn);
					if ( rdbCodeTable == null ) {
						//degenerate CategoricalVariable
					} else {
						copyCodesIntoCategoryTable(rdbCodeTable, v);
					}
				} else {
					AttributeDefinition colAttrDefn = valueRDBColumn.getAttributeDefinition();
					if ( colAttrDefn instanceof TaxonAttributeDefinition ) {
						copyDistinctColumnValuesIntoCategoryTable((MultiwayVariable) v);
					}
				}
			} else if ( v instanceof BinaryVariable ) {
				insertBooleanCategories((BinaryVariable) v);
			}
		}
	}

	private void deleteCategories() {
		Integer workspaceId = getWorkspace().getId();
		psql()
			.delete(CATEGORY)
			.where(CATEGORY.VARIABLE_ID.in(
					psql()
						.select(VARIABLE.ID)
						.from(VARIABLE)
							.join(ENTITY)
								.on(VARIABLE.ENTITY_ID.eq(ENTITY.ID))
						.where(ENTITY.WORKSPACE_ID.eq(workspaceId))
					))
			.execute();
	}

	private void copyCodesIntoCategoryTable(CodeTable rdbCodeTable, Variable<?> v) {
		Workspace ws = getWorkspace();
		List<Column<?>> columns = rdbCodeTable.getColumns();
		String codeColumnName = getColumnName(columns, CodeListCodeColumn.class);
		String descriptionColumnName = getColumnName(columns, CodeListDescriptionColumn.class);
		String labelColumnName = getColumnName(columns, CodeLabelColumn.class);
		
		CollectCodeListTable codeTable = new CollectCodeListTable(
				rdbCodeTable.getName(), ws.getInputSchema(),
				codeColumnName, labelColumnName, descriptionColumnName);
		
		Insert<? extends Record> insert = psql()
			.insertInto(CATEGORY, 
						CATEGORY.VARIABLE_ID, 
						CATEGORY.ORIGINAL_ID, 
						CATEGORY.CODE, 
						CATEGORY.CAPTION,
						CATEGORY.DESCRIPTION,
						CATEGORY.SORT_ORDER)
			.select(psql()
				.select(
						DSL.val(v.getId()),
						codeTable.getIdField(), 
						codeTable.getCodeField(),
						codeTable.getLabelField(),
						codeTable.getDescriptionField(),
						codeTable.getIdField()
				).from(codeTable));
		insert.execute();
	}

	private void copyDistinctColumnValuesIntoCategoryTable(MultiwayVariable v) {
		Entity entity = v.getEntity();
		Workspace ws = entity.getWorkspace();
		String entityDataTableName = entity.getDataTable();
		String inputSchemaName = ws.getInputSchema();
		CollectGenericDataTable dbTable = new CollectGenericDataTable(entityDataTableName, inputSchemaName);
		TableField<Record, String> dbColumn = dbTable.getOrCreateField(v.getInputValueColumn(), SQLDataType.VARCHAR);
		
		Select<Record1<String>> subSelect = psql()
				.selectDistinct(dbColumn)
				.from(dbTable)
				.orderBy(dbColumn);
		@SuppressWarnings("unchecked")
		Field<String> valueColumn = (Field<String>) subSelect.field(0);
		
		Insert<? extends Record> insert = psql()
				.insertInto(CATEGORY, 
							CATEGORY.VARIABLE_ID,
							CATEGORY.CODE, 
							CATEGORY.SORT_ORDER)
				.select(psql()
					.select(
							DSL.val(v.getId()),
							valueColumn,
							DSL.rowNumber().over().partitionByOne())
					.from(subSelect)
 				);
		insert.execute();
	}
	
	protected void insertBooleanCategories(BinaryVariable v) {
		insertBooleanCategory(v, Boolean.TRUE, 1);
		insertBooleanCategory(v, Boolean.FALSE, 2);
		insertBooleanCategory(v, null, 3);
	}

	protected void insertBooleanCategory(BinaryVariable v, Boolean value, int sortOrder) {
		Category c = new Category();
		c.setVariable(v);
		c.setCode(value == null ? "N": value.booleanValue() ? "T": "F");
		c.setDescription(value == null ? "NA": value.booleanValue() ? "TRUE": "FALSE");
		c.setSortOrder(sortOrder);
		categoryDao.save(c);
	}

	private DataColumn getRDBDataColumn(CategoricalVariable<?> v) {
		Entity entity = v.getEntity();
		String tableName = entity.getDataTable();
		Table<?> table = getRDBTable(tableName);
		Column<?> column = getRDBColumn(table, v.getInputValueColumn());
		if ( column instanceof DataColumn ) {
			return (DataColumn) column;
		} else {
			throw new IllegalArgumentException("DataColumn type expected, found: " + column.getClass().getName());
		}
	}

	private CodeTable getRDBCodeTable(CodeColumn column) {
		NodeDefinition codeFieldDefn = column.getNodeDefinition();
		CodeAttributeDefinition codeAttrDefn = (CodeAttributeDefinition) codeFieldDefn.getParentDefinition();
		CodeList codeList = codeAttrDefn.getList();
		CodeTable codeListTable = inputRelationalSchema.getCodeListTable(codeList, codeAttrDefn.getListLevelIndex());
		return codeListTable;
	}
	
	//TODO move to RDB Table
	protected Column<?> getRDBColumn(Table<?> table, String name) {
		List<Column<?>> columns = table.getColumns();
		for (Column<?> c : columns) {
			if ( c.getName().equals(name) ) {
				return c;
			}
		}
		throw new IllegalArgumentException("Column not found: " + name);
	}

	//TODO move to RDB schema
	protected Table<?> getRDBTable(String name) {
		List<Table<?>> tables = inputRelationalSchema.getTables();
		for (Table<?> table : tables) {
			if ( table.getName().equals(name) ) {
				return table;
			}
		}
		throw new IllegalArgumentException("Table not found: " + name);
	}
	
	protected List<Variable<?>> getVariables() {
		List<Variable<?>> result = new ArrayList<Variable<?>>();
		Workspace ws = getWorkspace();
		List<Entity> entities = ws.getEntities();
		for (Entity entity : entities) {
			List<Variable<?>> variables = entity.getVariables();
			for (Variable<?> v : variables) {
				result.add(v);
			}
		}
		return result;
	}
	
	protected String getColumnName(List<Column<?>> columns, Class<?> type) {
		for (Column<?> column : columns) {
			if ( type.isAssignableFrom(column.getClass()) ) {
				return column.getName();
			}
		}
		throw new IllegalArgumentException("Column of type " + type.getName() + " not found");
	}

	private void generateSchema() {
		Workspace ws = getWorkspace();
		CollectSurvey survey = ((CollectJob) getJob()).getSurvey();
		RelationalSchemaGenerator rdbGenerator = new RelationalSchemaGenerator();
		try {
			inputRelationalSchema = rdbGenerator.generateSchema(survey, ws.getInputSchema());
		} catch (CollectRdbException e) {
			throw new RuntimeException(e);
		}
	}

	private static class CollectGenericDataTable extends AbstractTable {

		private static final long serialVersionUID = 1L;
		
		public CollectGenericDataTable(String name, String schema) {
			super(name, new SchemaImpl(schema));
		}
		
		@SuppressWarnings("unchecked")
		public <R extends Record, T> TableField<R, T>  getOrCreateField(String name, DataType<T> type) {
			Field<?> field = ((TableImpl<R>) this).field(name);
			if ( field == null ) {
				field = createField(name, type, this);
			}
			return (TableField<R, T>) field;
		}
		
	}

}
