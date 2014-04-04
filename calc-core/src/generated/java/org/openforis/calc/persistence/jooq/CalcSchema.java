/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CalcSchema extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -1268710892;

	/**
	 * The singleton instance of <code>calc</code>
	 */
	public static final CalcSchema CALC = new CalcSchema();

	/**
	 * No further instances allowed
	 */
	private CalcSchema() {
		super("calc");
	}

	@Override
	public final java.util.List<org.jooq.Sequence<?>> getSequences() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final java.util.List<org.jooq.Sequence<?>> getSequences0() {
		return java.util.Arrays.<org.jooq.Sequence<?>>asList(
			org.openforis.calc.persistence.jooq.Sequences.AOI_HIERARCHY_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.AOI_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.AOI_LEVEL_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.CALCULATION_STEP_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.ENTITY_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.PROCESSING_CHAIN_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.SAMPLING_DESIGN_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.SRS_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.STRATUM_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.VARIABLE_AGGREGATE_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.VARIABLE_ID_SEQ,
			org.openforis.calc.persistence.jooq.Sequences.WORKSPACE_ID_SEQ);
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.openforis.calc.persistence.jooq.tables.AoiTable.AOI,
			org.openforis.calc.persistence.jooq.tables.AoiHierarchyTable.AOI_HIERARCHY,
			org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL,
			org.openforis.calc.persistence.jooq.tables.CalculationStepTable.CALCULATION_STEP,
			org.openforis.calc.persistence.jooq.tables.EntityTable.ENTITY,
			org.openforis.calc.persistence.jooq.tables.ProcessingChainTable.PROCESSING_CHAIN,
			org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN,
			org.openforis.calc.persistence.jooq.tables.StratumTable.STRATUM,
			org.openforis.calc.persistence.jooq.tables.StratumAoiViewTable.STRATUM_AOI_VIEW,
			org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE,
			org.openforis.calc.persistence.jooq.tables.VariableAggregateTable.VARIABLE_AGGREGATE,
			org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE);
	}
}
