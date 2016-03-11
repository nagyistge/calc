/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VariableBase implements java.io.Serializable {

	private static final long serialVersionUID = -1238915415;

	private java.lang.Integer                          id;
	private java.lang.Integer                          entityId;
	private java.lang.String                           inputValueColumn;
	private org.openforis.calc.metadata.Variable.Scale scale;
	private java.lang.String                           name;
	private java.lang.Integer                          originalId;
	private java.lang.String                           defaultValue;
	private java.lang.Boolean                          multipleResponse;
	private java.lang.Boolean                          override;
	private java.lang.Boolean                          degenerateDimension;
	private java.lang.String                           inputCategoryIdColumn;
	private java.lang.Boolean                          disaggregate;
	private java.lang.String                           outputValueColumn;
	private java.lang.String                           caption;
	private java.lang.String                           description;
	private java.lang.Integer                          sortOrder;
	private java.lang.Integer                          variablePerHaId;
	private java.lang.Long                             categoryLevelId;
	private java.lang.Boolean                          specieCategory;

	public VariableBase() {}

	public VariableBase(
		java.lang.Integer                          id,
		java.lang.Integer                          entityId,
		java.lang.String                           inputValueColumn,
		org.openforis.calc.metadata.Variable.Scale scale,
		java.lang.String                           name,
		java.lang.Integer                          originalId,
		java.lang.String                           defaultValue,
		java.lang.Boolean                          multipleResponse,
		java.lang.Boolean                          override,
		java.lang.Boolean                          degenerateDimension,
		java.lang.String                           inputCategoryIdColumn,
		java.lang.Boolean                          disaggregate,
		java.lang.String                           outputValueColumn,
		java.lang.String                           caption,
		java.lang.String                           description,
		java.lang.Integer                          sortOrder,
		java.lang.Integer                          variablePerHaId,
		java.lang.Long                             categoryLevelId,
		java.lang.Boolean                          specieCategory
	) {
		this.id = id;
		this.entityId = entityId;
		this.inputValueColumn = inputValueColumn;
		this.scale = scale;
		this.name = name;
		this.originalId = originalId;
		this.defaultValue = defaultValue;
		this.multipleResponse = multipleResponse;
		this.override = override;
		this.degenerateDimension = degenerateDimension;
		this.inputCategoryIdColumn = inputCategoryIdColumn;
		this.disaggregate = disaggregate;
		this.outputValueColumn = outputValueColumn;
		this.caption = caption;
		this.description = description;
		this.sortOrder = sortOrder;
		this.variablePerHaId = variablePerHaId;
		this.categoryLevelId = categoryLevelId;
		this.specieCategory = specieCategory;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(java.lang.Integer entityId) {
		this.entityId = entityId;
	}

	public java.lang.String getInputValueColumn() {
		return this.inputValueColumn;
	}

	public void setInputValueColumn(java.lang.String inputValueColumn) {
		this.inputValueColumn = inputValueColumn;
	}

	public org.openforis.calc.metadata.Variable.Scale getScale() {
		return this.scale;
	}

	public void setScale(org.openforis.calc.metadata.Variable.Scale scale) {
		this.scale = scale;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(java.lang.Integer originalId) {
		this.originalId = originalId;
	}

	public java.lang.String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(java.lang.String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public java.lang.Boolean getMultipleResponse() {
		return this.multipleResponse;
	}

	public void setMultipleResponse(java.lang.Boolean multipleResponse) {
		this.multipleResponse = multipleResponse;
	}

	public java.lang.Boolean getOverride() {
		return this.override;
	}

	public void setOverride(java.lang.Boolean override) {
		this.override = override;
	}

	public java.lang.Boolean getDegenerateDimension() {
		return this.degenerateDimension;
	}

	public void setDegenerateDimension(java.lang.Boolean degenerateDimension) {
		this.degenerateDimension = degenerateDimension;
	}

	public java.lang.String getInputCategoryIdColumn() {
		return this.inputCategoryIdColumn;
	}

	public void setInputCategoryIdColumn(java.lang.String inputCategoryIdColumn) {
		this.inputCategoryIdColumn = inputCategoryIdColumn;
	}

	public java.lang.Boolean getDisaggregate() {
		return this.disaggregate;
	}

	public void setDisaggregate(java.lang.Boolean disaggregate) {
		this.disaggregate = disaggregate;
	}

	public java.lang.String getOutputValueColumn() {
		return this.outputValueColumn;
	}

	public void setOutputValueColumn(java.lang.String outputValueColumn) {
		this.outputValueColumn = outputValueColumn;
	}

	public java.lang.String getCaption() {
		return this.caption;
	}

	public void setCaption(java.lang.String caption) {
		this.caption = caption;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(java.lang.Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public java.lang.Integer getVariablePerHaId() {
		return this.variablePerHaId;
	}

	public void setVariablePerHaId(java.lang.Integer variablePerHaId) {
		this.variablePerHaId = variablePerHaId;
	}

	public java.lang.Long getCategoryLevelId() {
		return this.categoryLevelId;
	}

	public void setCategoryLevelId(java.lang.Long categoryLevelId) {
		this.categoryLevelId = categoryLevelId;
	}

	public java.lang.Boolean getSpecieCategory() {
		return this.specieCategory;
	}

	public void setSpecieCategory(java.lang.Boolean specieCategory) {
		this.specieCategory = specieCategory;
	}
}
