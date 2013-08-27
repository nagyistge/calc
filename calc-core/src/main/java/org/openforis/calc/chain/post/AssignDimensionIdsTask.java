package org.openforis.calc.chain.post;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.Update;
import org.openforis.calc.engine.Task;
import org.openforis.calc.metadata.BinaryVariable;
import org.openforis.calc.metadata.CategoricalVariable;
import org.openforis.calc.metadata.Entity;
import org.openforis.calc.psql.Psql;
import org.openforis.calc.psql.PsqlPart;
import org.openforis.calc.schema.CategoryDimensionTable;
import org.openforis.calc.schema.FactTable;
import org.openforis.calc.schema.OutputSchema;

/**
 * Populates fact tables dimension id column
 * 
 * @author G. Miceli
 * @author M. Togna
 */
public final class AssignDimensionIdsTask extends Task {
	private static final BigDecimal FALSE_VALUE = BigDecimal.valueOf(0.0);
	private static final BigDecimal TRUE_VALUE = BigDecimal.valueOf(1.0);

	@Override
	protected void execute() throws Throwable {
		OutputSchema outputSchema = getOutputSchema();
		Collection<FactTable> factTables = outputSchema.getFactTables();
		for (FactTable factTable : factTables) {
			Entity entity = factTable.getEntity();
			List<CategoricalVariable> vars = entity.getCategoricalVariables();
			for (CategoricalVariable var : vars) {
//				if ( var.isDisaggregate() ) {
					if ( var instanceof BinaryVariable ) {
						assignBinaryDimensionIds(factTable, (BinaryVariable) var);
					} else {
						assignCategoricalDimensionIds(factTable, var);
					}
//				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void assignBinaryDimensionIds(FactTable factTable, BinaryVariable var) {
		OutputSchema outputSchema = (OutputSchema) factTable.getSchema();
		CategoryDimensionTable dim = outputSchema.getCategoryDimensionTable(var);
		Field<Integer> id = factTable.getDimensionIdField(var);
		if ( dim != null && id != null ) {
			Table<?> cursor = new Psql()
				.select(dim.ID, dim.VALUE)
				.from(dim)
				.asTable("dim");
	
			Field<BigDecimal> dimValue = cursor.field(dim.VALUE);
			Field<Boolean> factValue = (Field<Boolean>) factTable.getCategoryValueField(var);
	
			Update<?> update = new Psql()
				.update(factTable)
				.set(id, cursor.field(dim.ID));
			
			PsqlPart joinCondition = new Psql()
					.decode()
					.when(factValue.isNull(), dimValue.isNull())
					.when(factValue.isTrue(), dimValue.eq(TRUE_VALUE))
					.when(factValue.isFalse(), dimValue.eq(FALSE_VALUE))
					.end();
			
			psql()
				.updateWith(cursor, update, joinCondition)
				.execute();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void assignCategoricalDimensionIds(FactTable factTable, CategoricalVariable var) {
		OutputSchema outputSchema = (OutputSchema) factTable.getSchema();
		CategoryDimensionTable dim = outputSchema.getCategoryDimensionTable(var);
		Field<Integer> id = factTable.getDimensionIdField(var);
		if ( dim != null && id != null ) {
			Table<?> cursor = new Psql()
				.select(dim.ID, dim.CODE)
				.from(dim)
				.asTable("dim");
	
			Update<?> update = new Psql()
				.update(factTable)
				.set(id, cursor.field(dim.ID));						
	
			Field<String> value = (Field<String>) factTable.getCategoryValueField(var);
			Condition joinCondition = value.eq(cursor.field(dim.CODE));
			
			psql()
				.updateWith(cursor, update, joinCondition)
				.execute();
		}
	}
}