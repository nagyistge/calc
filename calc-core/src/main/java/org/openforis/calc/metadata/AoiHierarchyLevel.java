package org.openforis.calc.metadata;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openforis.calc.common.AbstractNamedIdentifiable;
import org.openforis.calc.persistence.sql.Psql;

/**
 * Provides metadata about a AOI Hierarchy Level. A hierarchy might be an "Administrative division" or "Ecological division" of an area.
 * The hierarchy then has several hierarchy levels. e.g. country, region, province, distric for the Administrative division
 *  
 * 
 * @author A. Sanchez-Paus Diaz
 */
@javax.persistence.Entity
@Table(name = "aoi_level")
public class AoiHierarchyLevel extends AbstractNamedIdentifiable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aoi_hierarchy_id")
	private AoiHierarchy hierarchy;

	@Column(name = "rank")
	private Integer rank;

	public AoiHierarchy getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(AoiHierarchy aoiHierarchyId) {
		this.hierarchy = aoiHierarchyId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDimensionTable() {
		return Psql.toIdentifier(hierarchy.getName() + "_" + getName() + "_aoi_dim");
	}

}
