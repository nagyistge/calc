/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.openforis.calc.persistence.jooq.tables.AoiHierarchyTable;
import org.openforis.calc.persistence.jooq.tables.records.AoiHierarchyRecord;


import org.openforis.calc.metadata.AoiHierarchy;
/**
 * A particular AOI hierarchy, such as "Administrative Units" or "Ecological 
 * Zones".
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AoiHierarchyDao extends DAOImpl<AoiHierarchyRecord, AoiHierarchy, Integer> {

	/**
	 * Create a new AoiHierarchyDao without any configuration
	 */
	public AoiHierarchyDao() {
		super(AoiHierarchyTable.AOI_HIERARCHY, AoiHierarchy.class);
	}

	/**
	 * Create a new AoiHierarchyDao with an attached configuration
	 */
	public AoiHierarchyDao(Configuration configuration) {
		super(AoiHierarchyTable.AOI_HIERARCHY, AoiHierarchy.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(AoiHierarchy object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<AoiHierarchy> fetchById(Integer... values) {
		return fetch(AoiHierarchyTable.AOI_HIERARCHY.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public AoiHierarchy fetchOneById(Integer value) {
		return fetchOne(AoiHierarchyTable.AOI_HIERARCHY.ID, value);
	}

	/**
	 * Fetch records that have <code>workspace_id IN (values)</code>
	 */
	public List<AoiHierarchy> fetchByWorkspaceId(Integer... values) {
		return fetch(AoiHierarchyTable.AOI_HIERARCHY.WORKSPACE_ID, values);
	}

	/**
	 * Fetch records that have <code>name IN (values)</code>
	 */
	public List<AoiHierarchy> fetchByName(String... values) {
		return fetch(AoiHierarchyTable.AOI_HIERARCHY.NAME, values);
	}

	/**
	 * Fetch records that have <code>caption IN (values)</code>
	 */
	public List<AoiHierarchy> fetchByCaption(String... values) {
		return fetch(AoiHierarchyTable.AOI_HIERARCHY.CAPTION, values);
	}

	/**
	 * Fetch records that have <code>description IN (values)</code>
	 */
	public List<AoiHierarchy> fetchByDescription(String... values) {
		return fetch(AoiHierarchyTable.AOI_HIERARCHY.DESCRIPTION, values);
	}
}
