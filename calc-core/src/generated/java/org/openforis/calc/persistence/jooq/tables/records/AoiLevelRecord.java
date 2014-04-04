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
public class AoiLevelRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.calc.persistence.jooq.tables.records.AoiLevelRecord> implements org.jooq.Record6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = 1457631253;

	/**
	 * Setter for <code>calc.aoi_level.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>calc.aoi_level.aoi_hierarchy_id</code>.
	 */
	public void setAoiHierarchyId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.aoi_hierarchy_id</code>.
	 */
	public java.lang.Integer getAoiHierarchyId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>calc.aoi_level.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>calc.aoi_level.caption</code>.
	 */
	public void setCaption(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.caption</code>.
	 */
	public java.lang.String getCaption() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>calc.aoi_level.rank</code>.
	 */
	public void setRank(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.rank</code>.
	 */
	public java.lang.Integer getRank() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>calc.aoi_level.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>calc.aoi_level.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(5);
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
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.AOI_HIERARCHY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.CAPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.RANK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL.DESCRIPTION;
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
		return getAoiHierarchyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getCaption();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getRank();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value2(java.lang.Integer value) {
		setAoiHierarchyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value3(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value4(java.lang.String value) {
		setCaption(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value5(java.lang.Integer value) {
		setRank(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord value6(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AoiLevelRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.Integer value5, java.lang.String value6) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AoiLevelRecord
	 */
	public AoiLevelRecord() {
		super(org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL);
	}

	/**
	 * Create a detached, initialised AoiLevelRecord
	 */
	public AoiLevelRecord(java.lang.Integer id, java.lang.Integer aoiHierarchyId, java.lang.String name, java.lang.String caption, java.lang.Integer rank, java.lang.String description) {
		super(org.openforis.calc.persistence.jooq.tables.AoiLevelTable.AOI_LEVEL);

		setValue(0, id);
		setValue(1, aoiHierarchyId);
		setValue(2, name);
		setValue(3, caption);
		setValue(4, rank);
		setValue(5, description);
	}
}
