/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.1.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VariableTable extends org.jooq.impl.TableImpl<org.jooq.Record> {

	private static final long serialVersionUID = 753267584;

	/**
	 * The singleton instance of <code>calc.variable</code>
	 */
	public static final org.openforis.calc.persistence.jooq.tables.VariableTable VARIABLE = new org.openforis.calc.persistence.jooq.tables.VariableTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.Record> getRecordType() {
		return org.jooq.Record.class;
	}

	/**
	 * The column <code>calc.variable.id</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>calc.variable.entity_id</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Integer> ENTITY_ID = createField("entity_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>calc.variable.input_value_column</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> INPUT_VALUE_COLUMN = createField("input_value_column", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.scale</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> SCALE = createField("scale", org.jooq.impl.SQLDataType.VARCHAR.length(25), this);

	/**
	 * The column <code>calc.variable.name</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.original_id</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Integer> ORIGINAL_ID = createField("original_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>calc.variable.default_value</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> DEFAULT_VALUE = createField("default_value", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.multiple_response</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Boolean> MULTIPLE_RESPONSE = createField("multiple_response", org.jooq.impl.SQLDataType.BOOLEAN, this);

	/**
	 * The column <code>calc.variable.override</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Boolean> OVERRIDE = createField("override", org.jooq.impl.SQLDataType.BOOLEAN, this);

	/**
	 * The column <code>calc.variable.dimension_table</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> DIMENSION_TABLE = createField("dimension_table", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.degenerate_dimension</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Boolean> DEGENERATE_DIMENSION = createField("degenerate_dimension", org.jooq.impl.SQLDataType.BOOLEAN, this);

	/**
	 * The column <code>calc.variable.input_category_id_column</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> INPUT_CATEGORY_ID_COLUMN = createField("input_category_id_column", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.disaggregate</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Boolean> DISAGGREGATE = createField("disaggregate", org.jooq.impl.SQLDataType.BOOLEAN, this);

	/**
	 * The column <code>calc.variable.output_value_column</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> OUTPUT_VALUE_COLUMN = createField("output_value_column", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.output_category_id_column</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> OUTPUT_CATEGORY_ID_COLUMN = createField("output_category_id_column", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.caption</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> CAPTION = createField("caption", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>calc.variable.description</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this);

	/**
	 * The column <code>calc.variable.sort_order</code>. 
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Integer> SORT_ORDER = createField("sort_order", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>calc.variable</code> table reference
	 */
	public VariableTable() {
		super("variable", org.openforis.calc.persistence.jooq.CalcSchema.CALC);
	}

	/**
	 * Create an aliased <code>calc.variable</code> table reference
	 */
	public VariableTable(java.lang.String alias) {
		super(alias, org.openforis.calc.persistence.jooq.CalcSchema.CALC, org.openforis.calc.persistence.jooq.tables.VariableTable.VARIABLE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.Record, java.lang.Integer> getIdentity() {
		return org.openforis.calc.persistence.jooq.Keys.IDENTITY_VARIABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.Record> getPrimaryKey() {
		return org.openforis.calc.persistence.jooq.Keys.VARIABLE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.Record>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.Record>>asList(org.openforis.calc.persistence.jooq.Keys.VARIABLE_PKEY, org.openforis.calc.persistence.jooq.Keys.VARIABLE_NAME_KEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.Record, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.Record, ?>>asList(org.openforis.calc.persistence.jooq.Keys.VARIABLE__VARIABLE_ENTITY_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.openforis.calc.persistence.jooq.tables.VariableTable as(java.lang.String alias) {
		return new org.openforis.calc.persistence.jooq.tables.VariableTable(alias);
	}
}
