package org.openforis.calc.workspace;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.openforis.calc.common.UserObject;
import org.openforis.calc.metadata.Entity;

/**
 * Conceptually, a workspace contains all data, metadata, processing instructions, sampling design and any other information required for calculating results.
 * 
 * @author G. Miceli
 * @author M. Togna
 */
@javax.persistence.Entity
@Table(schema = "calc", name = "workspace")
public final class Workspace extends UserObject {
	@Column(name = "input_schema")
	private String inputSchema;

	@Column(name = "output_schema")
	private String outputSchema;
	
	@OneToMany(mappedBy = "workspace", fetch = FetchType.EAGER)
	private List<Entity> entities;
//	private List<ProcessingChain> processingChains;

	public Workspace() {
//		this.entities = new ArrayList<Entity>();
//		this.processingChains = new ArrayList<ProcessingChain>();
	}
	
	public void setInputSchema(String inputSchema) {
		this.inputSchema = inputSchema;
	}

	public String getInputSchema() {
		return this.inputSchema;
	}

	public void setOutputSchema(String outputSchema) {
		this.outputSchema = outputSchema;
	}

	public String getOutputSchema() {
		return this.outputSchema;
	}

	public List<Entity> getEntities() {
		return Collections.unmodifiableList(entities);
	}
//	
//	void setEntities(List<Entity> entities) {
//		this.entities = entities;
//	}
//
//	void setProcessingChains(List<ProcessingChain> processingChains) {
//		this.processingChains = processingChains;
//	}
}