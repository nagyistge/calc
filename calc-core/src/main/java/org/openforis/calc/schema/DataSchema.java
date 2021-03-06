/**
 * 
 */
package org.openforis.calc.schema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Record;
import org.jooq.impl.DynamicTable;
import org.openforis.calc.engine.Workspace;
import org.openforis.calc.metadata.AoiHierarchy;
import org.openforis.calc.metadata.AoiLevel;
import org.openforis.calc.metadata.CategoricalVariable;
import org.openforis.calc.metadata.CategoryLevel;
import org.openforis.calc.metadata.Entity;
import org.openforis.calc.metadata.MultiwayVariable;
import org.openforis.calc.metadata.Variable;
import org.openforis.calc.persistence.jooq.CalcSchema;

/**
 * @author G. Miceli
 * @author M. Togna
 * 
 */
public class DataSchema extends RelationalSchema {

	private static final long serialVersionUID = 1L;

	private Workspace workspace;

	private Map<Integer, InputTable> dataTables;
	// private Map<Integer, ResultTable> resultTables;

	private Map<Integer, EntityDataView> dataViews;

	private ExtDataAoiTable phase1AoiTable;
	private ExtDataAoiTable primarySUAoiTable;

	// output tables
	private List<AoiHierarchyFlatTable> aoiHierchyTables;
	private Map<AoiLevel, ExpansionFactorTable> expansionFactorTables;
	private ClusterCountsTable clusterCountsTable;
	
	private StratumDimensionTable stratumDimensionTable;
	private Map<MultiwayVariable, CategoryDimensionTable> categoryDimensionTables;
	private Map<MultiwayVariable, SpeciesCategoryDimensionTable> speciesCategoryDimensionTables;

	public DataSchema(Workspace workspace) {
		this(workspace.getInputSchema(), workspace);
	}

	public DataSchema(String name, Workspace workspace) {
		super(name);
		this.workspace = workspace;

		initSchema();
	}

	protected void initSchema() {
		initDataTables();
		// initResultTables();

		initDataViews();

		this.phase1AoiTable = new ExtDataAoiTable("_phase1_aoi", this);
		this.primarySUAoiTable = new ExtDataAoiTable("_primary_sampling_unit_aoi", this);

		// output tables
		initAoiHirerchyTables();
		initExpansionFactorTables();
		initClusterCountsTables();
		initStratumDimensionTable();
		initCategoryDimensionTables();
		initSpeciesCategoryDimensionTables();
	}

	private void initDataTables() {
		this.dataTables = new HashMap<Integer, InputTable>();
		for (Entity entity : workspace.getEntities()) {
			InputTable inputTable = new InputTable(entity, this);
			addTable(inputTable);
			dataTables.put(entity.getId(), inputTable);
		}
	}

	// private void initResultTables() {
	// this.resultTables = new HashMap<Integer, ResultTable>();
	// for ( Entity entity : workspace.getEntities() ) {
	// ResultTable table = new ResultTable(entity, this);
	// addTable(table);
	// resultTables.put(entity.getId(), table);
	// }
	// }

	private void initDataViews() {
		this.dataViews = new HashMap<Integer, EntityDataView>();
		for (Entity entity : workspace.getEntities()) {
			EntityDataView view = new EntityDataView(entity, this);
			addView(view);
			dataViews.put(entity.getId(), view);
		}
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public DataTable getDataTable(Entity entity) {
		return dataTables.get(entity.getId());
	}

	public ResultTable getResultTable(Entity entity) {
		return this.getResultTable(entity, false);
	}

	public ResultTable getResultTable(Entity entity, boolean temporary) {
		// /|| entity.isSamplingUnit()
		Collection<Variable<?>> userDefinedVariables = entity.getUserDefinedVariables();

		if (userDefinedVariables.size() > 0) {
			ResultTable table = new ResultTable(entity, this, temporary);
			return table;
		}
		return null;
	}

	public EntityDataView getDataView(Entity entity) {
		return dataViews.get(entity.getId());
	}

	/*
	 * ================================== Output tables ==================================
	 */

	public List<FactTable> getFactTables() {
		List<FactTable> tables = new ArrayList<FactTable>();
		for (Entity entity : workspace.getEntities()) {
			if (entity.isAggregable()) {
				FactTable factTable = getFactTable(entity);
				tables.add(factTable);
			}
		}
		return tables;
	}

	public FactTable getFactTable(Entity entity) {
		if (entity.isAggregable()) {
			FactTable factTable = new FactTable(entity, this);
			return factTable;
		}
		return null;
	}

	public ExtDataAoiTable getPhase1AoiTable() {
		return phase1AoiTable;
	}

	public ExtDataAoiTable getPrimarySUAoiTable() {
		return primarySUAoiTable;
	}

	public DataAoiTable getSamplingUnitAoiTable() {
		Entity su = workspace.getSamplingUnit();
		if (su != null) {
			return new DataAoiTable("_" + su.getDataTable() + "_aoi", this);
		}
		return null;
	}

	public EntityAoiTable getEntityAoiTable(Entity entity) {
		return new EntityAoiTable(entity, this);
	}

	private void initAoiHirerchyTables() {
		this.aoiHierchyTables = new ArrayList<AoiHierarchyFlatTable>();

		List<AoiHierarchy> aoiHierarchies = getWorkspace().getAoiHierarchies();
		for (AoiHierarchy hierarchy : aoiHierarchies) {
			AoiHierarchyFlatTable table = new AoiHierarchyFlatTable(hierarchy, this);
			this.aoiHierchyTables.add(table);
		}

	}

	public List<AoiHierarchyFlatTable> getAoiHierchyTables() {
		return aoiHierchyTables;
	}
	
	private void initExpansionFactorTables() {
		this.expansionFactorTables = new HashMap<AoiLevel, ExpansionFactorTable>();

		for (AoiHierarchy aoiHierarchy : workspace.getAoiHierarchies()) {
			for (AoiLevel level : aoiHierarchy.getLevels()) {

				ExpansionFactorTable table = new ExpansionFactorTable(level, this);
				this.expansionFactorTables.put(level, table);

			}
		}
	}
	
	public ExpansionFactorTable getExpansionFactorTable(AoiLevel aoiLevel) {
		return this.expansionFactorTables.get(aoiLevel);
	}
	
	private void initClusterCountsTables() {
//		this.clusterCountsTable = new HashMap<AoiLevel, ClusterCountsTable>();
		if (workspace.hasClusterSamplingDesign()) {
			Entity clusterEntity = workspace.getSamplingDesign().getClusterEntity();

			this.clusterCountsTable = new ClusterCountsTable( this, clusterEntity );
		}
	}
	
	public ClusterCountsTable getClusterCountsTable() {
		return this.clusterCountsTable;
	}

	public DynamicTable<Record> getPhase1Table() {
		String phase1PlotTable = workspace.getPhase1PlotTable();

		if (StringUtils.isNotBlank(phase1PlotTable)) {
			return new DynamicTable<Record>(phase1PlotTable, CalcSchema.CALC.getName());
		}

		return null;
	}

	private void initStratumDimensionTable() {
		if (this.workspace.hasStratifiedSamplingDesign()) {
			this.stratumDimensionTable = new StratumDimensionTable(workspace);
		}
	}

	public StratumDimensionTable getStratumDimensionTable() {
		return this.stratumDimensionTable;
	}

	protected void initCategoryDimensionTables() {
		this.categoryDimensionTables = new HashMap<MultiwayVariable, CategoryDimensionTable>();

		List<Entity> entities = workspace.getEntities();
		for (Entity entity : entities) {
			// if( entity.isAggregable() ) {

			// Add dimensions for categorical variables
			for (CategoricalVariable<?> var : entity.getCategoricalVariables()) {
				// for (CategoricalVariable<?> var : entity.getDimensions()) {
				if (var instanceof MultiwayVariable && !((MultiwayVariable) var).isSpecieCategory()) {
					MultiwayVariable multiVar = (MultiwayVariable) var;
					CategoryLevel categoryLevel = var.getCategoryLevel();
					if (categoryLevel != null) {
						String schemaName = categoryLevel.getSchemaName();
						if (schemaName.equals(this.getName())) { // && !var.getDegenerateDimension() && var.getDisaggregate()
							// last test . if not input variable, it has to be in the output chain
							// if( !var.isUserDefined() || entity.getDefaultProcessingChainCategoricalOutputVariables().contains(multiVar) ){
							CategoryDimensionTable table = new CategoryDimensionTable(this, multiVar);
							addTable(table);
							categoryDimensionTables.put(multiVar, table);

							// }
						}
					}
				}
			}

			// }
		}
	}

	protected void initSpeciesCategoryDimensionTables() {
		this.speciesCategoryDimensionTables = new HashMap<MultiwayVariable, SpeciesCategoryDimensionTable>();
		for (Entity entity : workspace.getEntities()) {
			// Add dimensions for species categorical variables
			for ( CategoricalVariable<?> var : entity.getSpeciesCategoricalVariables() ) {
				if (var instanceof MultiwayVariable && ((MultiwayVariable) var).isSpecieCategory()) {
					MultiwayVariable multiVar = (MultiwayVariable) var;
					CategoryLevel categoryLevel = var.getCategoryLevel();
					if (categoryLevel != null) {
						String schemaName = categoryLevel.getSchemaName();
						if (schemaName.equals(this.getName())) {
							SpeciesCategoryDimensionTable table = new SpeciesCategoryDimensionTable(this, multiVar);
							addTable(table);
							this.speciesCategoryDimensionTables.put(multiVar, table);
						}
					}
				}
			}

		}
	}

	public Collection<CategoryDimensionTable> getCategoryDimensionTables() {
		return Collections.unmodifiableCollection(categoryDimensionTables.values());
	}

	public CategoryDimensionTable getCategoryDimensionTable(MultiwayVariable variable) {
		return categoryDimensionTables.get(variable);
	}

	public Collection<SpeciesCategoryDimensionTable> getSpeciesCategoryDimensionTables() {
		return Collections.unmodifiableCollection(speciesCategoryDimensionTables.values());
	}

}
