package org.openforis.calc.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author G. Miceli
 *
 */
public class ObservationUnitMetadata {

	private ObservationUnit observationUnit;
	private Map<String, VariableMetadata> variableMap;
	
	public ObservationUnitMetadata(ObservationUnit observationUnit, Collection<VariableMetadata> variables) {
		this.observationUnit = observationUnit;
		setVariableMetadata(variables);
	}

	private void setVariableMetadata(Collection<VariableMetadata> variables) {
		this.variableMap = new LinkedHashMap<String, VariableMetadata>();
		for ( VariableMetadata var : variables ) {
			variableMap.put(var.getVariableName(), var);
		}
	}
	
	public Collection<VariableMetadata> getVariableMetadata() {
		return Collections.unmodifiableCollection(variableMap.values());
	}

	public VariableMetadata getVariableMetadataByName(String name) {
		if ( variableMap == null ) {
			throw new NullPointerException("variableMap not initialized");
		}
		return variableMap.get(name);
	}

	public Integer getObsUnitId() {
		return observationUnit.getObsUnitId();
	}

	public Integer getSurveyId() {
		return observationUnit.getSurveyId();
	}

	public String getObsUnitName() {
		return observationUnit.getObsUnitName();
	}

	public String getObsUnitType() {
		return observationUnit.getObsUnitType();
	}

	public Integer getObsUnitParentId() {
		return observationUnit.getObsUnitParentId();
	}

	public String getObsUnitDescription() {
		return observationUnit.getObsUnitDescription();
	}

	public String getObsUnitLabel() {
		return observationUnit.getObsUnitLabel();
	}
}