/**
 * This class is generated by jOOQ
 */
package org.openforis.calc.persistence.jooq.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.6.0"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings("all")
public class TmpNumericValue implements java.io.Serializable {

	private static final long serialVersionUID = -414534471;

	private java.lang.Integer transactionId;
	private java.lang.Integer objectId;
	private java.lang.Integer variableId;
	private java.lang.Double  value;

	public java.lang.Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(java.lang.Integer transactionId) {
		this.transactionId = transactionId;
	}

	public java.lang.Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(java.lang.Integer objectId) {
		this.objectId = objectId;
	}

	public java.lang.Integer getVariableId() {
		return this.variableId;
	}

	public void setVariableId(java.lang.Integer variableId) {
		this.variableId = variableId;
	}

	public java.lang.Double getValue() {
		return this.value;
	}

	public void setValue(java.lang.Double value) {
		this.value = value;
	}
}