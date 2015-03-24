/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SamplingDesignRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.calc.persistence.jooq.tables.records.SamplingDesignRecord> implements org.jooq.Record15<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, java.lang.String, java.lang.Boolean, org.openforis.calc.engine.ParameterMap> {

	private static final long serialVersionUID = 1560940925;

	/**
	 * Setter for <code>calc.sampling_design.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>calc.sampling_design.sampling_unit_id</code>.
	 */
	public void setSamplingUnitId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.sampling_unit_id</code>.
	 */
	public java.lang.Integer getSamplingUnitId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>calc.sampling_design.workspace_id</code>.
	 */
	public void setWorkspaceId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.workspace_id</code>.
	 */
	public java.lang.Integer getWorkspaceId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>calc.sampling_design.srs</code>.
	 */
	public void setSrs(java.lang.Boolean value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.srs</code>.
	 */
	public java.lang.Boolean getSrs() {
		return (java.lang.Boolean) getValue(3);
	}

	/**
	 * Setter for <code>calc.sampling_design.systematic</code>.
	 */
	public void setSystematic(java.lang.Boolean value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.systematic</code>.
	 */
	public java.lang.Boolean getSystematic() {
		return (java.lang.Boolean) getValue(4);
	}

	/**
	 * Setter for <code>calc.sampling_design.two_phases</code>.
	 */
	public void setTwoPhases(java.lang.Boolean value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.two_phases</code>.
	 */
	public java.lang.Boolean getTwoPhases() {
		return (java.lang.Boolean) getValue(5);
	}

	/**
	 * Setter for <code>calc.sampling_design.stratified</code>.
	 */
	public void setStratified(java.lang.Boolean value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.stratified</code>.
	 */
	public java.lang.Boolean getStratified() {
		return (java.lang.Boolean) getValue(6);
	}

	/**
	 * Setter for <code>calc.sampling_design.cluster</code>.
	 */
	public void setCluster(java.lang.Boolean value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.cluster</code>.
	 */
	public java.lang.Boolean getCluster() {
		return (java.lang.Boolean) getValue(7);
	}

	/**
	 * Setter for <code>calc.sampling_design.phase1_join_settings</code>.
	 */
	public void setPhase1JoinSettings(org.openforis.calc.engine.ParameterMap value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.phase1_join_settings</code>.
	 */
	public org.openforis.calc.engine.ParameterMap getPhase1JoinSettings() {
		return (org.openforis.calc.engine.ParameterMap) getValue(8);
	}

	/**
	 * Setter for <code>calc.sampling_design.stratum_join_settings</code>.
	 */
	public void setStratumJoinSettings(org.openforis.calc.engine.ParameterMap value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.stratum_join_settings</code>.
	 */
	public org.openforis.calc.engine.ParameterMap getStratumJoinSettings() {
		return (org.openforis.calc.engine.ParameterMap) getValue(9);
	}

	/**
	 * Setter for <code>calc.sampling_design.cluster_column_settings</code>.
	 */
	public void setClusterColumnSettings(org.openforis.calc.engine.ParameterMap value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.cluster_column_settings</code>.
	 */
	public org.openforis.calc.engine.ParameterMap getClusterColumnSettings() {
		return (org.openforis.calc.engine.ParameterMap) getValue(10);
	}

	/**
	 * Setter for <code>calc.sampling_design.aoi_join_settings</code>.
	 */
	public void setAoiJoinSettings(org.openforis.calc.engine.ParameterMap value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.aoi_join_settings</code>.
	 */
	public org.openforis.calc.engine.ParameterMap getAoiJoinSettings() {
		return (org.openforis.calc.engine.ParameterMap) getValue(11);
	}

	/**
	 * Setter for <code>calc.sampling_design.sampling_unit_weight_script</code>.
	 */
	public void setSamplingUnitWeightScript(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.sampling_unit_weight_script</code>.
	 */
	public java.lang.String getSamplingUnitWeightScript() {
		return (java.lang.String) getValue(12);
	}

	/**
	 * Setter for <code>calc.sampling_design.two_stages</code>.
	 */
	public void setTwoStages(java.lang.Boolean value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.two_stages</code>.
	 */
	public java.lang.Boolean getTwoStages() {
		return (java.lang.Boolean) getValue(13);
	}

	/**
	 * Setter for <code>calc.sampling_design.two_stages_settings</code>.
	 */
	public void setTwoStagesSettings(org.openforis.calc.engine.ParameterMap value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>calc.sampling_design.two_stages_settings</code>.
	 */
	public org.openforis.calc.engine.ParameterMap getTwoStagesSettings() {
		return (org.openforis.calc.engine.ParameterMap) getValue(14);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record15 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row15<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, java.lang.String, java.lang.Boolean, org.openforis.calc.engine.ParameterMap> fieldsRow() {
		return (org.jooq.Row15) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row15<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, org.openforis.calc.engine.ParameterMap, java.lang.String, java.lang.Boolean, org.openforis.calc.engine.ParameterMap> valuesRow() {
		return (org.jooq.Row15) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.SAMPLING_UNIT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.WORKSPACE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field4() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.SRS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field5() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.SYSTEMATIC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field6() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.TWO_PHASES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field7() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.STRATIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field8() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.CLUSTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.engine.ParameterMap> field9() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.PHASE1_JOIN_SETTINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.engine.ParameterMap> field10() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.STRATUM_JOIN_SETTINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.engine.ParameterMap> field11() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.CLUSTER_COLUMN_SETTINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.engine.ParameterMap> field12() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.AOI_JOIN_SETTINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.SAMPLING_UNIT_WEIGHT_SCRIPT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field14() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.TWO_STAGES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.engine.ParameterMap> field15() {
		return org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN.TWO_STAGES_SETTINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getSamplingUnitId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getWorkspaceId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value4() {
		return getSrs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value5() {
		return getSystematic();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value6() {
		return getTwoPhases();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value7() {
		return getStratified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value8() {
		return getCluster();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.engine.ParameterMap value9() {
		return getPhase1JoinSettings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.engine.ParameterMap value10() {
		return getStratumJoinSettings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.engine.ParameterMap value11() {
		return getClusterColumnSettings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.engine.ParameterMap value12() {
		return getAoiJoinSettings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getSamplingUnitWeightScript();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value14() {
		return getTwoStages();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.engine.ParameterMap value15() {
		return getTwoStagesSettings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value2(java.lang.Integer value) {
		setSamplingUnitId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value3(java.lang.Integer value) {
		setWorkspaceId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value4(java.lang.Boolean value) {
		setSrs(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value5(java.lang.Boolean value) {
		setSystematic(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value6(java.lang.Boolean value) {
		setTwoPhases(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value7(java.lang.Boolean value) {
		setStratified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value8(java.lang.Boolean value) {
		setCluster(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value9(org.openforis.calc.engine.ParameterMap value) {
		setPhase1JoinSettings(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value10(org.openforis.calc.engine.ParameterMap value) {
		setStratumJoinSettings(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value11(org.openforis.calc.engine.ParameterMap value) {
		setClusterColumnSettings(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value12(org.openforis.calc.engine.ParameterMap value) {
		setAoiJoinSettings(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value13(java.lang.String value) {
		setSamplingUnitWeightScript(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value14(java.lang.Boolean value) {
		setTwoStages(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord value15(org.openforis.calc.engine.ParameterMap value) {
		setTwoStagesSettings(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplingDesignRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.Boolean value4, java.lang.Boolean value5, java.lang.Boolean value6, java.lang.Boolean value7, java.lang.Boolean value8, org.openforis.calc.engine.ParameterMap value9, org.openforis.calc.engine.ParameterMap value10, org.openforis.calc.engine.ParameterMap value11, org.openforis.calc.engine.ParameterMap value12, java.lang.String value13, java.lang.Boolean value14, org.openforis.calc.engine.ParameterMap value15) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SamplingDesignRecord
	 */
	public SamplingDesignRecord() {
		super(org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN);
	}

	/**
	 * Create a detached, initialised SamplingDesignRecord
	 */
	public SamplingDesignRecord(java.lang.Integer id, java.lang.Integer samplingUnitId, java.lang.Integer workspaceId, java.lang.Boolean srs, java.lang.Boolean systematic, java.lang.Boolean twoPhases, java.lang.Boolean stratified, java.lang.Boolean cluster, org.openforis.calc.engine.ParameterMap phase1JoinSettings, org.openforis.calc.engine.ParameterMap stratumJoinSettings, org.openforis.calc.engine.ParameterMap clusterColumnSettings, org.openforis.calc.engine.ParameterMap aoiJoinSettings, java.lang.String samplingUnitWeightScript, java.lang.Boolean twoStages, org.openforis.calc.engine.ParameterMap twoStagesSettings) {
		super(org.openforis.calc.persistence.jooq.tables.SamplingDesignTable.SAMPLING_DESIGN);

		setValue(0, id);
		setValue(1, samplingUnitId);
		setValue(2, workspaceId);
		setValue(3, srs);
		setValue(4, systematic);
		setValue(5, twoPhases);
		setValue(6, stratified);
		setValue(7, cluster);
		setValue(8, phase1JoinSettings);
		setValue(9, stratumJoinSettings);
		setValue(10, clusterColumnSettings);
		setValue(11, aoiJoinSettings);
		setValue(12, samplingUnitWeightScript);
		setValue(13, twoStages);
		setValue(14, twoStagesSettings);
	}
}
