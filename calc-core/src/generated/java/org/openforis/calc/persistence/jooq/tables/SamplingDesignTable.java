/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SamplingDesignTable extends org.jooq.impl.TableImpl<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> {

	private static final long serialVersionUID = 1815851901;

	/**
	 * The singleton instance of <code>calc.sampling_design</code>
	 */
	public static final org.openforis.calc.persistence.jooq.tables.SamplingDesignTable SAMPLING_DESIGN = new org.openforis.calc.persistence.jooq.tables.SamplingDesignTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> getRecordType() {
		return org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord.class;
	}

	/**
	 * The column <code>calc.sampling_design.id</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.sampling_unit_id</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Integer> SAMPLING_UNIT_ID = createField("sampling_unit_id", org.jooq.impl.SQLDataType.INTEGER.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.workspace_id</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Integer> WORKSPACE_ID = createField("workspace_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.srs</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Boolean> SRS = createField("srs", org.jooq.impl.SQLDataType.BOOLEAN.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.systematic</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Boolean> SYSTEMATIC = createField("systematic", org.jooq.impl.SQLDataType.BOOLEAN.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.two_phases</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Boolean> TWO_PHASES = createField("two_phases", org.jooq.impl.SQLDataType.BOOLEAN.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.stratified</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Boolean> STRATIFIED = createField("stratified", org.jooq.impl.SQLDataType.BOOLEAN.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.cluster</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Boolean> CLUSTER = createField("cluster", org.jooq.impl.SQLDataType.BOOLEAN.defaulted(true), this, "");

	/**
	 * The column <code>calc.sampling_design.phase1_join_settings</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, org.openforis.calc.engine.ParameterMap> PHASE1_JOIN_SETTINGS = createField("phase1_join_settings", org.jooq.impl.SQLDataType.VARCHAR.length(1020).defaulted(true).asConvertedDataType(new org.openforis.calc.persistence.jooq.ParameterMapConverter()), this, "");

	/**
	 * The column <code>calc.sampling_design.stratum_join_settings</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, org.openforis.calc.engine.ParameterMap> STRATUM_JOIN_SETTINGS = createField("stratum_join_settings", org.jooq.impl.SQLDataType.VARCHAR.length(1020).defaulted(true).asConvertedDataType(new org.openforis.calc.persistence.jooq.ParameterMapConverter()), this, "");

	/**
	 * The column <code>calc.sampling_design.cluster_column_settings</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, org.openforis.calc.engine.ParameterMap> CLUSTER_COLUMN_SETTINGS = createField("cluster_column_settings", org.jooq.impl.SQLDataType.VARCHAR.length(1020).defaulted(true).asConvertedDataType(new org.openforis.calc.persistence.jooq.ParameterMapConverter()), this, "");

	/**
	 * The column <code>calc.sampling_design.aoi_join_settings</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, org.openforis.calc.engine.ParameterMap> AOI_JOIN_SETTINGS = createField("aoi_join_settings", org.jooq.impl.SQLDataType.VARCHAR.length(1020).defaulted(true).asConvertedDataType(new org.openforis.calc.persistence.jooq.ParameterMapConverter()), this, "");

	/**
	 * The column <code>calc.sampling_design.sampling_unit_weight_script</code>.
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.String> SAMPLING_UNIT_WEIGHT_SCRIPT = createField("sampling_unit_weight_script", org.jooq.impl.SQLDataType.CLOB.defaulted(true), this, "");

	/**
	 * Create a <code>calc.sampling_design</code> table reference
	 */
	public SamplingDesignTable() {
		this("sampling_design", null);
	}

	/**
	 * Create an aliased <code>calc.sampling_design</code> table reference
	 */
	public SamplingDesignTable(java.lang.String alias) {
		this(alias, org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN);
	}

	private SamplingDesignTable(java.lang.String alias, org.jooq.Table<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> aliased) {
		this(alias, aliased, null);
	}

	private SamplingDesignTable(java.lang.String alias, org.jooq.Table<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.openforis.calc.persistence.jooq.CalcSchema.CALC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, java.lang.Integer> getIdentity() {
		return org.openforis.calc.persistence.jooq.Keys.IDENTITY_SAMPLING_DESIGN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> getPrimaryKey() {
		return org.openforis.calc.persistence.jooq.Keys.PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord>>asList(org.openforis.calc.persistence.jooq.Keys.PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord, ?>>asList(org.openforis.calc.persistence.jooq.Keys.SAMPLING_DESIGN__SAMPLING_DESIGN_SAMPLING_UNIT_FKEY, org.openforis.calc.persistence.jooq.Keys.SAMPLING_DESIGN__SAMPLING_DESIGN_WORKPSACE_FK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.persistence.jooq.tables.SamplingDesignTable as(java.lang.String alias) {
		return new org.openforis.calc.persistence.jooq.tables.SamplingDesignTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.openforis.calc.persistence.jooq.tables.SamplingDesignTable rename(java.lang.String name) {
		return new org.openforis.calc.persistence.jooq.tables.SamplingDesignTable(name, null);
	}
}
