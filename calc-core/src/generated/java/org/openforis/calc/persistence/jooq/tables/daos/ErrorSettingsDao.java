/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.daos;

import org.openforis.calc.metadata.ErrorSettings;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ErrorSettingsDao extends org.jooq.impl.DAOImpl<org.openforis.calc.persistence.jooq.tables.records.ErrorSettingsRecord, ErrorSettings, java.lang.Long> {

	/**
	 * Create a new ErrorSettingsDao without any configuration
	 */
	public ErrorSettingsDao() {
		super(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS, ErrorSettings.class);
	}

	/**
	 * Create a new ErrorSettingsDao with an attached configuration
	 */
	public ErrorSettingsDao(org.jooq.Configuration configuration) {
		super(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS, ErrorSettings.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Long getId(ErrorSettings object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public java.util.List<ErrorSettings> fetchById(java.lang.Long... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public ErrorSettings fetchOneById(java.lang.Long value) {
		return fetchOne(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS.ID, value);
	}

	/**
	 * Fetch records that have <code>workspace_id IN (values)</code>
	 */
	public java.util.List<ErrorSettings> fetchByWorkspaceId(java.lang.Long... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS.WORKSPACE_ID, values);
	}

	/**
	 * Fetch records that have <code>parameters IN (values)</code>
	 */
	public java.util.List<ErrorSettings> fetchByParameters(org.openforis.calc.engine.ParameterMap... values) {
		return fetch(org.openforis.calc.persistence.jooq.tables.ErrorSettingsTable.ERROR_SETTINGS.PARAMETERS, values);
	}
}
