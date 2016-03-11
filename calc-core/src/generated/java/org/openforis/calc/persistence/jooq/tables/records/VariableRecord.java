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
public class VariableRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.calc.persistence.jooq.tables.records.VariableRecord> implements org.jooq.Record19<java.lang.Integer, java.lang.Integer, java.lang.String, org.openforis.calc.metadata.Variable.Scale, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Boolean> {

	private static final long serialVersionUID = -359183332;

	/**
	 * Setter for <code>calc.variable.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>calc.variable.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>calc.variable.entity_id</code>.
	 */
	public void setEntityId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>calc.variable.entity_id</code>.
	 */
	public java.lang.Integer getEntityId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>calc.variable.input_value_column</code>.
	 */
	public void setInputValueColumn(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>calc.variable.input_value_column</code>.
	 */
	public java.lang.String getInputValueColumn() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>calc.variable.scale</code>.
	 */
	public void setScale(org.openforis.calc.metadata.Variable.Scale value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>calc.variable.scale</code>.
	 */
	public org.openforis.calc.metadata.Variable.Scale getScale() {
		return (org.openforis.calc.metadata.Variable.Scale) getValue(3);
	}

	/**
	 * Setter for <code>calc.variable.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>calc.variable.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>calc.variable.original_id</code>.
	 */
	public void setOriginalId(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>calc.variable.original_id</code>.
	 */
	public java.lang.Integer getOriginalId() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>calc.variable.default_value</code>.
	 */
	public void setDefaultValue(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>calc.variable.default_value</code>.
	 */
	public java.lang.String getDefaultValue() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>calc.variable.multiple_response</code>.
	 */
	public void setMultipleResponse(java.lang.Boolean value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>calc.variable.multiple_response</code>.
	 */
	public java.lang.Boolean getMultipleResponse() {
		return (java.lang.Boolean) getValue(7);
	}

	/**
	 * Setter for <code>calc.variable.override</code>.
	 */
	public void setOverride(java.lang.Boolean value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>calc.variable.override</code>.
	 */
	public java.lang.Boolean getOverride() {
		return (java.lang.Boolean) getValue(8);
	}

	/**
	 * Setter for <code>calc.variable.degenerate_dimension</code>.
	 */
	public void setDegenerateDimension(java.lang.Boolean value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>calc.variable.degenerate_dimension</code>.
	 */
	public java.lang.Boolean getDegenerateDimension() {
		return (java.lang.Boolean) getValue(9);
	}

	/**
	 * Setter for <code>calc.variable.input_category_id_column</code>.
	 */
	public void setInputCategoryIdColumn(java.lang.String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>calc.variable.input_category_id_column</code>.
	 */
	public java.lang.String getInputCategoryIdColumn() {
		return (java.lang.String) getValue(10);
	}

	/**
	 * Setter for <code>calc.variable.disaggregate</code>.
	 */
	public void setDisaggregate(java.lang.Boolean value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>calc.variable.disaggregate</code>.
	 */
	public java.lang.Boolean getDisaggregate() {
		return (java.lang.Boolean) getValue(11);
	}

	/**
	 * Setter for <code>calc.variable.output_value_column</code>.
	 */
	public void setOutputValueColumn(java.lang.String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>calc.variable.output_value_column</code>.
	 */
	public java.lang.String getOutputValueColumn() {
		return (java.lang.String) getValue(12);
	}

	/**
	 * Setter for <code>calc.variable.caption</code>.
	 */
	public void setCaption(java.lang.String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>calc.variable.caption</code>.
	 */
	public java.lang.String getCaption() {
		return (java.lang.String) getValue(13);
	}

	/**
	 * Setter for <code>calc.variable.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>calc.variable.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(14);
	}

	/**
	 * Setter for <code>calc.variable.sort_order</code>.
	 */
	public void setSortOrder(java.lang.Integer value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>calc.variable.sort_order</code>.
	 */
	public java.lang.Integer getSortOrder() {
		return (java.lang.Integer) getValue(15);
	}

	/**
	 * Setter for <code>calc.variable.variable_per_ha_id</code>.
	 */
	public void setVariablePerHaId(java.lang.Integer value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>calc.variable.variable_per_ha_id</code>.
	 */
	public java.lang.Integer getVariablePerHaId() {
		return (java.lang.Integer) getValue(16);
	}

	/**
	 * Setter for <code>calc.variable.category_level_id</code>.
	 */
	public void setCategoryLevelId(java.lang.Long value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>calc.variable.category_level_id</code>.
	 */
	public java.lang.Long getCategoryLevelId() {
		return (java.lang.Long) getValue(17);
	}

	/**
	 * Setter for <code>calc.variable.specie_category</code>.
	 */
	public void setSpecieCategory(java.lang.Boolean value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>calc.variable.specie_category</code>.
	 */
	public java.lang.Boolean getSpecieCategory() {
		return (java.lang.Boolean) getValue(18);
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
	// Record19 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row19<java.lang.Integer, java.lang.Integer, java.lang.String, org.openforis.calc.metadata.Variable.Scale, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Boolean> fieldsRow() {
		return (org.jooq.Row19) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row19<java.lang.Integer, java.lang.Integer, java.lang.String, org.openforis.calc.metadata.Variable.Scale, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Boolean> valuesRow() {
		return (org.jooq.Row19) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.ENTITY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.INPUT_VALUE_COLUMN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.openforis.calc.metadata.Variable.Scale> field4() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.SCALE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.ORIGINAL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.DEFAULT_VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field8() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.MULTIPLE_RESPONSE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field9() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.OVERRIDE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field10() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.DEGENERATE_DIMENSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field11() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.INPUT_CATEGORY_ID_COLUMN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field12() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.DISAGGREGATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field13() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.OUTPUT_VALUE_COLUMN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field14() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.CAPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field15() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field16() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.SORT_ORDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field17() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.VARIABLE_PER_HA_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field18() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.CATEGORY_LEVEL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field19() {
		return org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE.SPECIE_CATEGORY;
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
		return getEntityId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getInputValueColumn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.metadata.Variable.Scale value4() {
		return getScale();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getOriginalId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getDefaultValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value8() {
		return getMultipleResponse();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value9() {
		return getOverride();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value10() {
		return getDegenerateDimension();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value11() {
		return getInputCategoryIdColumn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value12() {
		return getDisaggregate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value13() {
		return getOutputValueColumn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value14() {
		return getCaption();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value15() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value16() {
		return getSortOrder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value17() {
		return getVariablePerHaId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value18() {
		return getCategoryLevelId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value19() {
		return getSpecieCategory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value2(java.lang.Integer value) {
		setEntityId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value3(java.lang.String value) {
		setInputValueColumn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value4(org.openforis.calc.metadata.Variable.Scale value) {
		setScale(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value5(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value6(java.lang.Integer value) {
		setOriginalId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value7(java.lang.String value) {
		setDefaultValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value8(java.lang.Boolean value) {
		setMultipleResponse(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value9(java.lang.Boolean value) {
		setOverride(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value10(java.lang.Boolean value) {
		setDegenerateDimension(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value11(java.lang.String value) {
		setInputCategoryIdColumn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value12(java.lang.Boolean value) {
		setDisaggregate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value13(java.lang.String value) {
		setOutputValueColumn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value14(java.lang.String value) {
		setCaption(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value15(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value16(java.lang.Integer value) {
		setSortOrder(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value17(java.lang.Integer value) {
		setVariablePerHaId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value18(java.lang.Long value) {
		setCategoryLevelId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord value19(java.lang.Boolean value) {
		setSpecieCategory(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VariableRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, org.openforis.calc.metadata.Variable.Scale value4, java.lang.String value5, java.lang.Integer value6, java.lang.String value7, java.lang.Boolean value8, java.lang.Boolean value9, java.lang.Boolean value10, java.lang.String value11, java.lang.Boolean value12, java.lang.String value13, java.lang.String value14, java.lang.String value15, java.lang.Integer value16, java.lang.Integer value17, java.lang.Long value18, java.lang.Boolean value19) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VariableRecord
	 */
	public VariableRecord() {
		super(org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE);
	}

	/**
	 * Create a detached, initialised VariableRecord
	 */
	public VariableRecord(java.lang.Integer id, java.lang.Integer entityId, java.lang.String inputValueColumn, org.openforis.calc.metadata.Variable.Scale scale, java.lang.String name, java.lang.Integer originalId, java.lang.String defaultValue, java.lang.Boolean multipleResponse, java.lang.Boolean override, java.lang.Boolean degenerateDimension, java.lang.String inputCategoryIdColumn, java.lang.Boolean disaggregate, java.lang.String outputValueColumn, java.lang.String caption, java.lang.String description, java.lang.Integer sortOrder, java.lang.Integer variablePerHaId, java.lang.Long categoryLevelId, java.lang.Boolean specieCategory) {
		super(org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE);

		setValue(0, id);
		setValue(1, entityId);
		setValue(2, inputValueColumn);
		setValue(3, scale);
		setValue(4, name);
		setValue(5, originalId);
		setValue(6, defaultValue);
		setValue(7, multipleResponse);
		setValue(8, override);
		setValue(9, degenerateDimension);
		setValue(10, inputCategoryIdColumn);
		setValue(11, disaggregate);
		setValue(12, outputValueColumn);
		setValue(13, caption);
		setValue(14, description);
		setValue(15, sortOrder);
		setValue(16, variablePerHaId);
		setValue(17, categoryLevelId);
		setValue(18, specieCategory);
	}
}
