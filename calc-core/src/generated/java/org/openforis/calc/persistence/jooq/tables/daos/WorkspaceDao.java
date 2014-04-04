/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.daos;

import org.openforis.calc.engine.Workspace;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkspaceDao extends org.jooq.impl.DAOImpl<org.openforis.calc.persistence.jooq.tables.records.WorkspaceRecord, Workspace, java.lang.Integer> {

	/**
	 * Create a new WorkspaceDao without any configuration
	 */
	public WorkspaceDao() {
		super(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE, Workspace.class);
	}

	/**
	 * Create a new WorkspaceDao with an attached configuration
	 */
	public WorkspaceDao(org.jooq.Configuration configuration) {
		super(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE, Workspace.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(Workspace object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public java.util.List<Workspace> fetchById(java.lang.Integer... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public Workspace fetchOneById(java.lang.Integer value) {
		return fetchOne(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.ID, value);
	}

	/**
	 * Fetch records that have <code>name IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByName(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.NAME, values);
	}

	/**
	 * Fetch records that have <code>collect_survey_uri IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByCollectSurveyUri(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.COLLECT_SURVEY_URI, values);
	}

	/**
	 * Fetch records that have <code>input_schema IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByInputSchema(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.INPUT_SCHEMA, values);
	}

	/**
	 * Fetch records that have <code>output_schema IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByOutputSchema(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.OUTPUT_SCHEMA, values);
	}

	/**
	 * Fetch records that have <code>caption IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByCaption(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.CAPTION, values);
	}

	/**
	 * Fetch records that have <code>description IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByDescription(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.DESCRIPTION, values);
	}

	/**
	 * Fetch records that have <code>active IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByActive(java.lang.Boolean... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.ACTIVE, values);
	}

	/**
	 * Fetch records that have <code>phase1_plot_table IN (values)</code>
	 */
	public java.util.List<Workspace> fetchByPhase1PlotTable(java.lang.String... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.WorkspaceTable.WORKSPACE.PHASE1_PLOT_TABLE, values);
	}
}
