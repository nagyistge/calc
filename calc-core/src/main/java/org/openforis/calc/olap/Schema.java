//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.01 at 03:55:36 PM CET 
//

package org.openforis.calc.olap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.openforis.calc.model.ObservationUnitMetadata;
import org.openforis.calc.model.SurveyMetadata;
import org.openforis.calc.model.VariableMetadata;
import org.openforis.calc.olap.Hierarchy.Level;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "annotations", "parameter", "dimension", "cube", "virtualCube", "namedSet", "role", "userDefinedFunction" })
@XmlRootElement(name = "Schema")
public class Schema {

	static final String ROLE_ADMIN = "ROLE_ADMIN";
	static final String ROLE_USER = "ROLE_USER";
	
	static final String GRANT_ACCESS_ALL = "all";
	static final String GRANT_ACCESS_NONE = "none";
	
	@XmlElement(name = "Annotations")
	protected Annotations annotations;
	@XmlElement(name = "Parameter")
	protected List<Parameter> parameter;
	@XmlElement(name = "Dimension")
	protected List<SharedDimension> dimension;
	@XmlElement(name = "Cube", required = true)
	protected List<Cube> cube;
	@XmlElement(name = "VirtualCube")
	protected List<VirtualCube> virtualCube;
	@XmlElement(name = "NamedSet")
	protected List<NamedSet> namedSet;
	@XmlElement(name = "Role")
	protected List<Role> role;
	@XmlElement(name = "UserDefinedFunction")
	protected List<UserDefinedFunction> userDefinedFunction;
	@XmlAttribute(required = true)
	protected String name;
	@XmlAttribute
	protected String description;
	@XmlAttribute
	protected String measuresCaption;
	@XmlAttribute
	protected String defaultRole;

	@XmlTransient
	private SurveyMetadata surveyMetadata;

	public Schema() {
	}

	public Schema(SurveyMetadata surveyMetadata) {
		this.surveyMetadata = surveyMetadata;
		setName( getMdxName(surveyMetadata.getSurveyName()) );
		
		initDimensions();
		initCubes();
		initRoles();
	}

	private void initRoles() {
		Role admin = new Role();
		admin.setName(ROLE_ADMIN);
		SchemaGrant adminSchemaGrant = new SchemaGrant();
		adminSchemaGrant.setAccess(GRANT_ACCESS_ALL);
		admin.addSchemaGrant(adminSchemaGrant );
		
		Role user = new Role();
		user.setName(ROLE_USER);
		SchemaGrant userSchemaGrant = new SchemaGrant();
		userSchemaGrant.setAccess(GRANT_ACCESS_ALL);
		user.addSchemaGrant(userSchemaGrant);
		
		List<Cube> cubes = getCubes();
		for ( Cube cube : cubes ) {
			CubeGrant cubeGrant = new CubeGrant();
			String cubeName = cube.getName();
			cubeGrant.setCube(cubeName);
			String access = cubeName.startsWith("_") ? GRANT_ACCESS_NONE : GRANT_ACCESS_ALL;
			cubeGrant.setAccess(access);
			userSchemaGrant.addCubeGrant(cubeGrant);
		}
		
		List<VirtualCube> virtualCubes = getVirtualCubes();
		for ( VirtualCube virtualCube : virtualCubes ) {
			CubeGrant cubeGrant = new CubeGrant();
			String cubeName = virtualCube.getName();
			cubeGrant.setCube(cubeName);
			String access = cubeName.startsWith("_") ? GRANT_ACCESS_NONE : GRANT_ACCESS_ALL;
			cubeGrant.setAccess(access);
			userSchemaGrant.addCubeGrant(cubeGrant);
		}
		
		getRoles().add(admin);
		getRoles().add(user);
	}

	private void initDimensions() {
		Collection<ObservationUnitMetadata> obsMetadata = surveyMetadata.getObservationMetadata();
		SharedDimension aoiDim = getAoiDimension();
		getDimensions().add(aoiDim);

		SharedDimension specimenDim = getSpeciesDimension();
		getDimensions().add(specimenDim);

		for ( ObservationUnitMetadata obsUnitMetadata : obsMetadata ) {
			Collection<VariableMetadata> vars = obsUnitMetadata.getVariableMetadata();
			for ( VariableMetadata var : vars ) {
				if ( var.isCategorical() && var.isForAnalysis() ) {
					SharedDimension dimension = new SharedDimension(var);
					getDimensions().add(dimension);
				}
			}
		}
	}

	/**
	 * <Dimension type="StandardDimension" visible="true" name="Species"> 
	 * 		<Hierarchy name="Species" visible="true" hasAll="true">
		 * 		<Table name="taxon" schema="calc">
		 * 		</Table>
		 * 		<Level name="Species" visible="true" table="taxon" column="taxon_id" nameColumn="scientific_name" uniqueMembers="false"> 
		 * 		</Level> 
	 * 		</Hierarchy>
	 * </Dimension>
	 */
	private SharedDimension getSpeciesDimension() {
		SharedDimension dim = new SharedDimension();
		dim.setType(SharedDimension.TYPE_STANDARD_DIMENSION);
		dim.setVisible(true);
		dim.setHighCardinality(false);
		dim.setName("Species");

		Hierarchy hierarchy = new Hierarchy();
		hierarchy.setName("Species");
		hierarchy.setVisible(true);
		hierarchy.setHasAll(true);

		Table aoiTable = new Table();
		aoiTable.setName("taxon");
		aoiTable.setSchema("calc");
		hierarchy.setTable(aoiTable);

		Level level = new Level();
		level.setName("Species");
		level.setVisible(true);
		level.setTable("taxon");
		level.setColumn("taxon_id");
		level.setNameColumn("scientific_name");
		level.setType("String");
		level.setUniqueMembers(false);
		level.setLevelType("Regular");
		level.setHideMemberIf("Never");
		hierarchy.getLevels().add(level);

		dim.getHierarchies().add(hierarchy);
		return dim;
	}

	private SharedDimension getAoiDimension() {
		SharedDimension aoi = new SharedDimension();
		aoi.setType(SharedDimension.TYPE_STANDARD_DIMENSION);
		aoi.setVisible(true);
		aoi.setHighCardinality(false);
		aoi.setName("AOI");

		Hierarchy aoiHierarchy = new Hierarchy();
		aoiHierarchy.setName("AOI");
		aoiHierarchy.setVisible(true);
		aoiHierarchy.setHasAll(true);

		Table aoiTable = new Table();
		aoiTable.setName("aoi");
		aoiTable.setSchema("calc");
		aoiHierarchy.setTable(aoiTable);

		Level aoiLevel = new Level();
		aoiLevel.setName("AOI");
		aoiLevel.setVisible(true);
		aoiLevel.setTable("aoi");
		aoiLevel.setColumn("aoi_id");
		aoiLevel.setNameColumn("aoi_label");
		aoiLevel.setType("String");
		aoiLevel.setUniqueMembers(false);
		aoiLevel.setLevelType("Regular");
		aoiLevel.setHideMemberIf("Never");
		aoiHierarchy.getLevels().add(aoiLevel);

		aoi.getHierarchies().add(aoiHierarchy);
		return aoi;
	}

	private void initCubes() {
		Collection<ObservationUnitMetadata> obsMetadata = surveyMetadata.getObservationMetadata();
		for ( ObservationUnitMetadata obsUnitMetadata : obsMetadata ) {
			if( isValidCube(obsUnitMetadata) ) {
				Cube cube = new Cube(obsUnitMetadata);
				getCubes().add(cube);
				if ( obsUnitMetadata.isTypeSpecimen() ) {
					VirtualCube virtualCube = new VirtualCube(obsUnitMetadata);
					getVirtualCubes().add(virtualCube);
				}
			}
		}
	}

	private boolean isValidCube(ObservationUnitMetadata obsUnitMetadata) {
		if( obsUnitMetadata.isTypePlot() ){
			return true;
		} else if(obsUnitMetadata.isTypeSpecimen()){
			Collection<VariableMetadata> vars = obsUnitMetadata.getVariableMetadata();
			for ( VariableMetadata var : vars ) {
				// it has to contain measures
				if( var.isNumeric() && var.isForAnalysis()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the value of the annotations property.
	 * 
	 * @return possible object is {@link Annotations }
	 * 
	 */
	public Annotations getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the value of the annotations property.
	 * 
	 * @param value
	 *            allowed object is {@link Annotations }
	 * 
	 */
	public void setAnnotations(Annotations value) {
		this.annotations = value;
	}

	/**
	 * Gets the value of the parameter property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the parameter property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getParameter().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Parameter }
	 * 
	 * 
	 */
	public List<Parameter> getParameter() {
		if ( parameter == null ) {
			parameter = new ArrayList<Parameter>();
		}
		return this.parameter;
	}

	/**
	 * Gets the value of the dimension property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the dimension property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getDimension().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link SharedDimension }
	 * 
	 * 
	 */
	public List<SharedDimension> getDimensions() {
		if ( dimension == null ) {
			dimension = new ArrayList<SharedDimension>();
		}
		return this.dimension;
	}

	/**
	 * Gets the value of the cube property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the cube property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCube().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Cube }
	 * 
	 * 
	 */
	public List<Cube> getCubes() {
		if ( cube == null ) {
			cube = new ArrayList<Cube>();
		}
		return this.cube;
	}

	/**
	 * Gets the value of the virtualCube property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the virtualCube property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getVirtualCube().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link VirtualCube }
	 * 
	 * 
	 */
	public List<VirtualCube> getVirtualCubes() {
		if ( virtualCube == null ) {
			virtualCube = new ArrayList<VirtualCube>();
		}
		return this.virtualCube;
	}

	/**
	 * Gets the value of the namedSet property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the namedSet property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNamedSet().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link NamedSet }
	 * 
	 * 
	 */
	public List<NamedSet> getNamedSet() {
		if ( namedSet == null ) {
			namedSet = new ArrayList<NamedSet>();
		}
		return this.namedSet;
	}

	/**
	 * Gets the value of the role property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the role property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRole().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Role }
	 * 
	 * 
	 */
	public List<Role> getRoles() {
		if ( role == null ) {
			role = new ArrayList<Role>();
		}
		return this.role;
	}

	/**
	 * Gets the value of the userDefinedFunction property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be
	 * present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the userDefinedFunction property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getUserDefinedFunction().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link UserDefinedFunction }
	 * 
	 * 
	 */
	public List<UserDefinedFunction> getUserDefinedFunction() {
		if ( userDefinedFunction == null ) {
			userDefinedFunction = new ArrayList<UserDefinedFunction>();
		}
		return this.userDefinedFunction;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the measuresCaption property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMeasuresCaption() {
		return measuresCaption;
	}

	/**
	 * Sets the value of the measuresCaption property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMeasuresCaption(String value) {
		this.measuresCaption = value;
	}

	/**
	 * Gets the value of the defaultRole property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDefaultRole() {
		return defaultRole;
	}

	/**
	 * Sets the value of the defaultRole property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDefaultRole(String value) {
		this.defaultRole = value;
	}

	static String getMdxName(String name) {
		String[] s = null;
		StringBuilder sb = new StringBuilder();
		if ( name.indexOf("_") > 0 ) {
			s = name.split("_");
		} else {
			s = new String[] { name };
		}

		for ( int i = 0 ; i < s.length ; i++ ) {
			String string = s[i];
			if ( i != 0 ) {
				sb.append(" ");
			}
			sb.append(string.substring(0, 1).toUpperCase());
			sb.append(string.substring(1, string.length()));
		}

		return sb.toString();
	}

}