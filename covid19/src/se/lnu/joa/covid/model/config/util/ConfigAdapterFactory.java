/**
 */
package se.lnu.joa.covid.model.config.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import se.lnu.joa.covid.model.config.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see se.lnu.joa.covid.model.config.ConfigPackage
 * @generated
 */
public class ConfigAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ConfigPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ConfigPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigSwitch<Adapter> modelSwitch =
		new ConfigSwitch<Adapter>() {
			@Override
			public Adapter caseConfig(Config object) {
				return createConfigAdapter();
			}
			@Override
			public Adapter caseDataModel(DataModel object) {
				return createDataModelAdapter();
			}
			@Override
			public Adapter caseVisualization(Visualization object) {
				return createVisualizationAdapter();
			}
			@Override
			public Adapter caseAnimation(Animation object) {
				return createAnimationAdapter();
			}
			@Override
			public Adapter caseRegression(Regression object) {
				return createRegressionAdapter();
			}
			@Override
			public Adapter caseAxis(Axis object) {
				return createAxisAdapter();
			}
			@Override
			public Adapter caseAxes(Axes object) {
				return createAxesAdapter();
			}
			@Override
			public Adapter caseVisualizationInfo(VisualizationInfo object) {
				return createVisualizationInfoAdapter();
			}
			@Override
			public Adapter caseScale(Scale object) {
				return createScaleAdapter();
			}
			@Override
			public Adapter caseFilter(Filter object) {
				return createFilterAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Config <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Config
	 * @generated
	 */
	public Adapter createConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.DataModel <em>Data Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.DataModel
	 * @generated
	 */
	public Adapter createDataModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Visualization <em>Visualization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Visualization
	 * @generated
	 */
	public Adapter createVisualizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Animation <em>Animation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Animation
	 * @generated
	 */
	public Adapter createAnimationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Regression <em>Regression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Regression
	 * @generated
	 */
	public Adapter createRegressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Axis <em>Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Axis
	 * @generated
	 */
	public Adapter createAxisAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Axes <em>Axes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Axes
	 * @generated
	 */
	public Adapter createAxesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.VisualizationInfo <em>Visualization Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.VisualizationInfo
	 * @generated
	 */
	public Adapter createVisualizationInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Scale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Scale
	 * @generated
	 */
	public Adapter createScaleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link se.lnu.joa.covid.model.config.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see se.lnu.joa.covid.model.config.Filter
	 * @generated
	 */
	public Adapter createFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ConfigAdapterFactory
