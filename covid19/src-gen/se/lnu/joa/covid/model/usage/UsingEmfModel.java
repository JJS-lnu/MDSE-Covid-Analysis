package se.lnu.joa.covid.model.usage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import se.lnu.joa.covid.model.config.Animation;
import se.lnu.joa.covid.model.config.Config;
import se.lnu.joa.covid.model.config.ConfigFactory;
import se.lnu.joa.covid.model.config.ConfigPackage;
import se.lnu.joa.covid.model.config.DataModel;
import se.lnu.joa.covid.model.config.Regression;
import se.lnu.joa.covid.model.config.RegressionType;
import se.lnu.joa.covid.model.config.Visualization;
import se.lnu.joa.covid.model.config.VisualizationType;
import se.lnu.joa.covid.model.config.util.ConfigAdapterFactory;
import se.lnu.joa.covid.model.covid19.Covid19Factory;
import se.lnu.joa.covid.model.covid19.Covid19Package;
import se.lnu.joa.covid.model.covid19.DataPool;
import se.lnu.joa.covid.model.covid19.Epidemiology;
import se.lnu.joa.covid.model.covid19.Health;
import se.lnu.joa.covid.model.covid19.Index;
import se.lnu.joa.covid.model.covid19.util.Covid19AdapterFactory;

import org.eclipse.m2m.qvt.oml.*;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


public class UsingEmfModel {
    public static void main(String[] args) {
    	final Covid19AdapterFactory aFactory;
    	final ConfigAdapterFactory cFactory;
    	
    	final String configFile = "config.yaml";
    	final String indexFile = "index.csv";
    	final String epidemiologyFile = "epidemiology.csv";
    	final String healthFile = "health.csv";
    
    	
		try {
			EcorePackage.eINSTANCE.eClass();    // Makes sure EMF is up and running
			Covid19Package.eINSTANCE.eClass(); 
			ConfigPackage.eINSTANCE.eClass();
			aFactory = new Covid19AdapterFactory();
			cFactory = new ConfigAdapterFactory();
			
			// Read input files
			DataPool pool = readCsvData(indexFile, epidemiologyFile, healthFile);
	        Config config = readConfig(configFile);
	        	        			
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Resource.Factory.Registry cReg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			Map<String, Object> cM = cReg.getExtensionToFactoryMap();
			
			m.put("dataModel", new XMIResourceFactoryImpl());
			cM.put("configModel", new XMIResourceFactoryImpl());
			
			// Obtain a new resource set
	        ResourceSet resSet = new ResourceSetImpl();
	        ResourceSet configResourceSet = new ResourceSetImpl();
	        
	        // create a resource
	        Resource resource = resSet.createResource(URI
	                .createURI("dataModel/My2.dataModel"));
	        Resource configResource = configResourceSet.createResource(URI.createURI("configModel/My2.configModel"));
	        
	        // Get the first model element and cast it to the right type, in my
	        // example everything is hierarchical included in this first node
	        resource.getContents().add(pool);
	        configResource.getContents().add(config);
	        	        
	        // now save the content.
	        try {
	            resource.save(Collections.EMPTY_MAP);
	            configResource.save(Collections.EMPTY_MAP);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
			/* Temporarily commented
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("Covid19", new XMIResourceFactoryImpl());
			
			URI uri = URI.createURI("platform:/resource/covid19/model/DataPool.xmi");
				        			
			resourceSet.createResource(uri).getContents().add(pool);
						
			URI transformationURI = URI.createURI("platform:/resource/Covid19QVT/transforms/DataAndConfigToAnalytic.qvto"); 
			
			// Define the transformation input
			Resource inResource = resourceSet.getResource(uri, false);
			EList<EObject> inObjects = inResource.getContents(); 
						
			// Create the Input and Output Models
			ModelExtent dataModel = new BasicModelExtent(inObjects);
			ModelExtent configModel = new BasicModelExtent(inObjects); // create new basic model for configuration model (MM2), but the argument must be changed!
			ModelExtent outModel = new BasicModelExtent();
						
			// Set up execution environment and configuration properties
			TransformationExecutor executor = new TransformationExecutor(transformationURI);
			ExecutionContextImpl context = new ExecutionContextImpl();
	
			ExecutionDiagnostic result = executor.execute(context, dataModel, configModel, outModel); // update to a transformation with 2 input and 1 output
			System.out.println(executor.loadTransformation());
			
			if (result.getSeverity() == Diagnostic.OK) {
				// The objects got captured in outModel => save into resource
				List<EObject> outObjects = outModel.getContents();
				ResourceSet resourceSet2 = new ResourceSetImpl();
				Resource outResource = resourceSet2.getResource(
						URI.createURI("HealthData.Covid19.New"), false); // TODO: Change the URI!!
				outResource.getContents().addAll(outObjects);
				outResource.save(Collections.emptyMap());
			} else {
				System.err.println("Error in running transformation\n"+result.getMessage()); // Check if there was any issue in transformation
				
			}
			
			*/
						
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    	
        
    }
    
    private static Config readConfig(String configSource) {
        ConfigFactory configFactory = ConfigFactory.eINSTANCE;
    	Config config = configFactory.createConfig();
    	
    	Yaml yaml = new Yaml(new Constructor(AnalyticConfig.class));
    	try {
			InputStream inputStream = new FileInputStream(configSource);
			
			// Load YAML file to the proper POJO class
			AnalyticConfig aConfig = yaml.load(inputStream);
		
						
			// Create a DataModel from config file
			DataModel dm = configFactory.createDataModel();
			dm.setDataSource(aConfig.getDataModel().getDataSource());
			dm.setDatasetName(aConfig.getDataModel().getDatasetName());
			
			// Create a Visualization from config file
			Visualization vlz = configFactory.createVisualization();
			vlz.setType(VisualizationType.get(aConfig.getVisualization().getType()));
			vlz.setXAxis(aConfig.getVisualization().getxAxis());
			vlz.setYAxis(aConfig.getVisualization().getyAxis());
			vlz.setColor(aConfig.getVisualization().getColor());
			vlz.setTitle(aConfig.getVisualization().getTitle());
			vlz.setSubTitle(aConfig.getVisualization().getSubTitle());
			vlz.setXAxisLabel(aConfig.getVisualization().getxAxisLabel());
			vlz.setYAxisLabel(aConfig.getVisualization().getyAxisLabel());
			vlz.setCaption(aConfig.getVisualization().getCaption());
			 
			// create a Animation from config file
			Animation ani = configFactory.createAnimation();
			ani.setLabel(aConfig.getAnimation().getLabel());
			ani.setTransitionTime(aConfig.getAnimation().getTransitionTime());
			ani.setWidth(aConfig.getAnimation().getWidth());
			ani.setHeight(aConfig.getAnimation().getHeight());
			ani.setDuration(aConfig.getAnimation().getDuration());
			ani.setOutputName(aConfig.getAnimation().getOutputName());
			ani.setOutputPath(aConfig.getAnimation().getOutputPath());
			
			
			// create Regression from config file
			Regression reg = configFactory.createRegression();
			reg.setType(RegressionType.get(aConfig.getRegression().getType()));
			reg.setDependentValue(aConfig.getRegression().getDependentValue());
			reg.setIndependentValue(aConfig.getRegression().getIndependentValue());
			
			config.setSource(dm);
			config.setVisualizatoin(vlz);
			config.setAnimation(ani);
			config.setRegression(reg);
			
			return config;
			
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    private static DataPool readCsvData(String indexFile,String epidemiologyFile,String healthFile) throws IOException {
    	Path indexPath = FileSystems.getDefault().getPath(indexFile);
        Path epidemiologyPath = FileSystems.getDefault().getPath(epidemiologyFile);
        Path healthPath = FileSystems.getDefault().getPath(healthFile);
        
        // Retrieve the default factory singleton
        Covid19Factory factory = Covid19Factory.eINSTANCE;
        
        // Create an instance of DataPool
        DataPool pool = factory.createDataPool();
		
        // Index
        Reader in = new FileReader(indexPath.toString());
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : records) {
		    // Create a record
		    Index id = factory.createIndex();
		    
		    // Fill record with data
	        id.setKey(record.get(0));
	        id.setWikidata(record.get(1));
	        id.setDatacommons(record.get(2));
	        id.setCountry_code(record.get(3));
	        id.setCountry_name(record.get(4));
	        id.setSubregion1_code(record.get(5));
	        id.setSubregion1_name(record.get(6));
	        id.setSubregion2_code(record.get(7));
	        id.setSubregion2_name(record.get(8));
	        id.setLocality_code(record.get(9));
	        id.setLocality_name(record.get(10));
	        id.setAlpha_2(record.get(11));
	        id.setAlpha_3(record.get(12));
	        id.setAggregation_level(record.get(13));
	        
	        // Add record to Index data
	        pool.getIndexData().add(id);
		}
		
		// Epidemiology
		in = new FileReader(epidemiologyPath.toString());
		records = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : records) {
		    // Create a record
		    Epidemiology ed = factory.createEpidemiology();
		    
		    // Fill record with data
		    ed.setDate(record.get(0));
	        ed.setKey(record.get(1));
	        ed.setNew_confirmed(record.get(2));
	        ed.setNew_deceased(record.get(3));
	        ed.setNew_recovered(record.get(4));
	        ed.setNew_tested(record.get(5));
	        ed.setTotal_confirmed(record.get(5));
	        ed.setTotal_deceased(record.get(6));
	        ed.setTotal_recovered(record.get(7));
	        ed.setTotal_tested(record.get(8));
	        
	        // Add record to Epidemiology data
	        pool.getEpidemiologyData().add(ed);
		}
		
		// Health
		in = new FileReader(healthPath.toString());
		records = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : records) {
		    // Create a record
		    Health hd = factory.createHealth();
		    
		    // Fill record with data
	        hd.setKey(record.get(0));
	        hd.setLife_expectancy(record.get(1));
	        hd.setSmoking_prevalence(record.get(2));
	        hd.setDiabetes_prevalence(record.get(3));
	        hd.setInfant_mortality_rate(record.get(4));
	        hd.setAdult_male_mortality_rate(record.get(5));
	        hd.setAdult_female_mortality_rate(record.get(6));
	        hd.setPollution_mortality_rate(record.get(7));
	        hd.setComorbidity_mortality_rate(record.get(8));
	        hd.setHospital_beds(record.get(9));
	        hd.setNurses(record.get(10));
	        hd.setPhysicians(record.get(11));
	        hd.setHealth_expenditure(record.get(12));
	        hd.setOut_of_pocket_health_expenditure(record.get(13));
	        
	        // Add record to Health data
	        pool.getHealthData().add(hd);
		}
		
		return pool;
    }
}

//int i = 0;
//for(Index item : pool.getIndexData()) {
//	if (i > 0) {
//		System.out.print("Key: ");
//		System.out.println(item.getKey());
//		System.out.print("Wikidata: ");
//		System.out.println(item.getWikidata());
//		System.out.print("Datacommons: ");
//		System.out.println(item.getDatacommons());
//		System.out.print("Country Code: ");
//		System.out.println(item.getCountry_code());
//		System.out.print("Country Name: ");
//		System.out.println(item.getCountry_name());
//		System.out.print("Subregion 1 code: ");
//		System.out.println(item.getSubregion1_code());
//		System.out.print("Subregion 1 name: ");
//		System.out.println(item.getSubregion1_name());
//		System.out.print("Subregion 2 code: ");
//		System.out.println(item.getSubregion2_code());
//		System.out.print("Subregion 2 name: ");
//		System.out.println(item.getSubregion2_name());
//		System.out.print("Locality code: ");
//		System.out.println(item.getLocality_code());
//		System.out.print("Locality name: ");
//		System.out.println(item.getLocality_name());
//		System.out.print("3166-1-alpha-2: ");
//		System.out.println(item.getAlpha_2());
//		System.out.print("3166-1-alpha-3: ");
//		System.out.println(item.getAlpha_3());
//		System.out.print("Aggregation Level: ");
//		System.out.println(item.getAggregation_level());
//		System.out.println("\n");
//	}
//	i = i + 1;
//}

//i = 0;
//for(Epidemiology item : pool.getEpidemiologyData()) {
//	if (i > 0) {
//		System.out.print("Key: ");
//		System.out.println(item.getKey());
//		System.out.print("Date: ");
//		System.out.println(item.getDate());
//		System.out.print("New Confirmed cases: ");
//		System.out.println(item.getNew_confirmed());
//		System.out.print("New Deceased cases: ");
//		System.out.println(item.getNew_deceased());
//		System.out.print("New Recovered cases: ");
//		System.out.println(item.getNew_recovered());
//		System.out.print("New Tested cases: ");
//		System.out.println(item.getNew_tested());
//		System.out.print("Total Confirmed cases: ");
//		System.out.println(item.getTotal_confirmed());
//		System.out.print("Total Deceased cases: ");
//		System.out.println(item.getTotal_deceased());
//		System.out.print("Total Recovered cases: ");
//		System.out.println(item.getTotal_recovered());
//		System.out.print("Total Tested cases: ");
//		System.out.println(item.getTotal_tested());
//		System.out.println("\n");
//	}
//	i = i + 1;
//}

//i = 0;
//for(Health item : pool.getHealthData()) {
//	if (i > 0) {
//		System.out.print("Key: ");
//		System.out.println(item.getKey());
//		System.out.print("Life Expectancy: ");
//		System.out.println(item.getLife_expectancy());
//		System.out.print("Smoking Prevalence: ");
//		System.out.println(item.getSmoking_prevalence());
//		System.out.print("Diabetes Prevalence: ");
//		System.out.println(item.getDiabetes_prevalence());
//		System.out.print("Infant Morality Rate: ");
//		System.out.println(item.getInfant_mortality_rate());
//		System.out.print("Adult Male Morality Rate: ");
//		System.out.println(item.getAdult_male_mortality_rate());
//		System.out.print("Adult Female Morality Rate: ");
//		System.out.println(item.getAdult_female_mortality_rate());
//		System.out.print("Pollution Morality Rate: ");
//		System.out.println(item.getPollution_mortality_rate());
//		System.out.print("Cormobidity Morality Rate: ");
//		System.out.println(item.getComorbidity_mortality_rate());
//		System.out.print("Hospital Beds: ");
//		System.out.println(item.getHospital_beds());
//		System.out.print("Nurses: ");
//		System.out.println(item.getNurses());
//		System.out.print("Physicians: ");
//		System.out.println(item.getPhysicians());
//		System.out.print("Health Expenditure: ");
//		System.out.println(item.getHealth_expenditure());
//		System.out.print("Out of Pocket Health Expenditure: ");
//		System.out.println(item.getOut_of_pocket_health_expenditure());
//		System.out.println("\n");
//	}
//	i = i + 1;
//}