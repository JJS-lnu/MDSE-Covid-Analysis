/**
 */
package se.lnu.joa.covid.model.analysis;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link se.lnu.joa.covid.model.analysis.DataSource#getName <em>Name</em>}</li>
 *   <li>{@link se.lnu.joa.covid.model.analysis.DataSource#getPath <em>Path</em>}</li>
 *   <li>{@link se.lnu.joa.covid.model.analysis.DataSource#getHeaders <em>Headers</em>}</li>
 *   <li>{@link se.lnu.joa.covid.model.analysis.DataSource#getRows <em>Rows</em>}</li>
 * </ul>
 *
 * @see se.lnu.joa.covid.model.analysis.AnalysisPackage#getDataSource()
 * @model
 * @generated
 */
public interface DataSource extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see se.lnu.joa.covid.model.analysis.AnalysisPackage#getDataSource_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link se.lnu.joa.covid.model.analysis.DataSource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see se.lnu.joa.covid.model.analysis.AnalysisPackage#getDataSource_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link se.lnu.joa.covid.model.analysis.DataSource#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Headers</b></em>' containment reference list.
	 * The list contents are of type {@link se.lnu.joa.covid.model.analysis.DataHeader}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Headers</em>' containment reference list.
	 * @see se.lnu.joa.covid.model.analysis.AnalysisPackage#getDataSource_Headers()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataHeader> getHeaders();

	/**
	 * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
	 * The list contents are of type {@link se.lnu.joa.covid.model.analysis.DataRow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rows</em>' containment reference list.
	 * @see se.lnu.joa.covid.model.analysis.AnalysisPackage#getDataSource_Rows()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataRow> getRows();

} // DataSource