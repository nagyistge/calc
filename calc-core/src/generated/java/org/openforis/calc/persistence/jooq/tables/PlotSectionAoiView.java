/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.6.0"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings("all")
public class PlotSectionAoiView extends org.jooq.impl.TableImpl<org.openforis.calc.persistence.jooq.tables.records.PlotSectionAoiViewRecord> {

	private static final long serialVersionUID = -1222770204;

	/**
	 * The singleton instance of calc.plot_section_aoi_view
	 */
	public static final org.openforis.calc.persistence.jooq.tables.PlotSectionAoiView PLOT_SECTION_AOI_VIEW = new org.openforis.calc.persistence.jooq.tables.PlotSectionAoiView();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.openforis.calc.persistence.jooq.tables.records.PlotSectionAoiViewRecord> getRecordType() {
		return org.openforis.calc.persistence.jooq.tables.records.PlotSectionAoiViewRecord.class;
	}

	/**
	 * The table column <code>calc.plot_section_aoi_view.plot_section_id</code>
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.PlotSectionAoiViewRecord, java.lang.Integer> PLOT_SECTION_ID = createField("plot_section_id", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The table column <code>calc.plot_section_aoi_view.aoi_id</code>
	 */
	public final org.jooq.TableField<org.openforis.calc.persistence.jooq.tables.records.PlotSectionAoiViewRecord, java.lang.Integer> AOI_ID = createField("aoi_id", org.jooq.impl.SQLDataType.INTEGER, this);

	public PlotSectionAoiView() {
		super("plot_section_aoi_view", org.openforis.calc.persistence.jooq.Calc.CALC);
	}

	public PlotSectionAoiView(java.lang.String alias) {
		super(alias, org.openforis.calc.persistence.jooq.Calc.CALC, org.openforis.calc.persistence.jooq.tables.PlotSectionAoiView.PLOT_SECTION_AOI_VIEW);
	}

	@Override
	public org.openforis.calc.persistence.jooq.tables.PlotSectionAoiView as(java.lang.String alias) {
		return new org.openforis.calc.persistence.jooq.tables.PlotSectionAoiView(alias);
	}
}