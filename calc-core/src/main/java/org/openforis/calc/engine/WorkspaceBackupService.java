/**
 * 
 */
package org.openforis.calc.engine;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Record;
import org.jooq.impl.DynamicTable;
import org.json.simple.JSONArray;
import org.openforis.calc.Calc;
import org.openforis.calc.engine.WorkspaceBackup.ExternalData;
import org.openforis.calc.metadata.AuxiliaryTable;
import org.openforis.calc.metadata.Category;
import org.openforis.calc.metadata.CategoryHierarchy;
import org.openforis.calc.metadata.CategoryLevel;
import org.openforis.calc.metadata.CategoryLevel.CategoryLevelValue;
import org.openforis.calc.metadata.CategoryManager;
import org.openforis.calc.metadata.Entity;
import org.openforis.calc.metadata.MetadataManager;
import org.openforis.calc.metadata.SamplingDesign;
import org.openforis.calc.metadata.Variable;
import org.openforis.calc.persistence.jooq.CalcSchema;
import org.openforis.calc.schema.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Workspace backup implements to logic to export and import a workspace
 * 
 * @author Mino Togna
 * 
 */
@Service
public class WorkspaceBackupService {

	public static final String WORKSPACE_BACKUP_FILE_NAME 	= "workspace.json";
	public static final String VERSION_FILE_NAME 	= "calc-version.txt";
	public static final String DEV_VERSION 			= "PROJECT_VERSION";
	
	@Autowired
	private Calc calc;
	
	@Autowired
	private ObjectMapper jsonObjectMapper;

	@Autowired
	private MetadataManager metadataManager;
	
	@Autowired
	private CategoryManager categoryManager;
	
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private TaskManager taskManager;

	public WorkspaceBackupService() {
	}

	/**
	 * Clone the active workspace for export. 
	 * Input variables are excluded
	 */
	public WorkspaceBackup createBackup( Workspace ws ) {
		WorkspaceBackup backup = new WorkspaceBackup( ws );
		
		// variable original ids map
		List<Variable<?>> variables = ws.getVariables();
		Map<Integer, Integer> variableIds = new HashMap<Integer, Integer>();
		for ( Variable<?> variable : variables ) {
			variableIds.put( variable.getId(), variable.getOriginalId() );
		}
		backup.setVariableOriginalIds( variableIds );
		
		// remove input variables and input categories
		metadataManager.deleteInputVariables( ws );
		ws.removeInputCategories();
		
		// export phase1 data, primary sampling unit and user defined category values
		loadPhase1Data( backup );
		loadPrimarySUData( backup );
		loadOutputCategoryLevelValues( backup );
		loadAuxiliaryTables( backup );
		
		backup.setVersion( calc.getVersion() );
		
		return backup;
	}
	
	private void loadOutputCategoryLevelValues( WorkspaceBackup backup ) {
		Map<Integer, List<CategoryLevelValue>> categoryLevelValues = new HashMap<Integer, List<CategoryLevelValue>>();

		List<Category> categories = backup.getWorkspace().getCategories();
		for ( Category category : categories ) {
			if( category.isUserDefined() ){
				List<CategoryHierarchy> hierarchies = category.getHierarchies();
				for ( CategoryHierarchy categoryHierarchy : hierarchies ) {
					List<CategoryLevel> levels = categoryHierarchy.getLevels();
					for ( CategoryLevel categoryLevel : levels ) {
						List<CategoryLevelValue> values = categoryManager.loadCategoryLevelValues(categoryLevel);
						categoryLevelValues.put( categoryLevel.getId(), values );
					}
				}
			}
		}
		backup.setCategoryLevelValues( categoryLevelValues  );
	}

	/**
	 * Load the phase1 data for export
	 * @param ws
	 */
	private void loadPhase1Data( WorkspaceBackup backup ) {
		Workspace ws = backup.getWorkspace();
		
		String phase1PlotTable = ws.getPhase1PlotTable();
		if( ws.has2PhasesSamplingDesign() && StringUtils.isNotBlank(phase1PlotTable) ){
			
			ExternalData externalData 	= loadExternalData(phase1PlotTable, CalcSchema.CALC.getName());
			backup.setPhase1Data( externalData );
		}
	}
	/**
	 * Load the primary sampling unit data for export
	 * @param ws
	 */
	private void loadPrimarySUData( WorkspaceBackup backup ) {
		Workspace ws = backup.getWorkspace();
		
		if( ws.hasSamplingDesign() &&  ws.getSamplingDesign().getTwoStages() ) {
			// read table information 
			ExternalData externalData = loadExternalData(ws.getPrimarySUTableName(), ws.getExtendedSchemaName());
			backup.setPrimarySUData( externalData );
		}
	}

	private ExternalData loadExternalData(String tableName, String schemaName) {
		DynamicTable<?> table 	= new DynamicTable<Record>( tableName, schemaName );
		JSONArray tableInfo 	= tableDao.info( table );
		
		table.initFields(tableInfo);
		List<DataRecord> records = tableDao.selectAll(table);
		
		ExternalData externalData = new ExternalData( tableInfo , records );
		return externalData;
	}
	
	/**
	 * Load the imported auxiliary tables
	 * @param backup
	 */
	private void loadAuxiliaryTables(WorkspaceBackup backup) {
		Workspace ws = backup.getWorkspace();	
		for (AuxiliaryTable table : ws.getAuxiliaryTables()) {
			String name 				= table.getName();
			ExternalData externalData 	= loadExternalData(name, table.getSchema());
			
			backup.addAuxiliaryTable(name, externalData);
		}
	}	
	
	
	public WorkspaceBackup extractBackup( ZipFile zipFile ) throws IOException {
		
		String wsString = extractZipEntry( zipFile , WORKSPACE_BACKUP_FILE_NAME );
		WorkspaceBackup backup = this.jsonObjectMapper.readValue( wsString, WorkspaceBackup.class );
		
		Workspace workspace = backup.getWorkspace();
		SamplingDesign samplingDesign = workspace.getSamplingDesign();
		if(samplingDesign != null ){
			Integer samplingUnitId = samplingDesign.getSamplingUnitId();
			Integer samplingUnitOrigId = backup.getVariableOriginalIds().get(samplingUnitId);
			Entity su = workspace.getEntityByOriginalId(samplingUnitOrigId);
//			Entity su = workspace.getEntityById(samplingUnitId);
			samplingDesign.setSamplingUnit(su);
		}
		return backup;
	}
	
	/**
	 * Export the given workspace to an output stream as zip file
	 * @param workspace
	 * @param outputStream
	 * @throws IOException
	 */
	public void exportToStream( WorkspaceBackup backup , OutputStream outputStream ) throws IOException{
		ZipOutputStream stream = new ZipOutputStream( outputStream );
		
		// write backup 
		StringWriter sw = new StringWriter();
		jsonObjectMapper.writeValue( sw, backup );
		String wsString = sw.toString();
		
		addZipEntry( wsString, WORKSPACE_BACKUP_FILE_NAME, stream );
		
		stream.close();
	}

	public Job createImportBackupJob( Workspace workspace , WorkspaceBackup workspaceBackup ){
		Job job = taskManager.createJob( workspace );
		
		DeleteOutputMetadataTask deleteTask = taskManager.createTask( DeleteOutputMetadataTask.class );
		job.addTask( deleteTask );
		
		ImportOutputMetadataTask importTask = taskManager.createTask( ImportOutputMetadataTask.class );
		importTask.setWorkspaceBackup(workspaceBackup);
		job.addTask( importTask );
		
		if( workspaceBackup.getWorkspace().hasSamplingDesign() ){
			taskManager.addPreProcessingTasks( job );
		}
		taskManager.addProcessingChainTasks(workspaceBackup.getWorkspace(), job);
		
		return job;
	}
	
	private void addZipEntry( String value , String name , ZipOutputStream stream ) throws IOException {
		ZipEntry info = new ZipEntry( name );
		stream.putNextEntry(info);
		stream.write(  encodeBase64(value.getBytes()) );
		stream.closeEntry();
	}
	
	private String extractZipEntry( ZipFile zipFile, String entryName ) throws IOException {
		ZipEntry entry = zipFile.getEntry(entryName);
		if ( entry == null ) {
			throw new IllegalStateException(entryName + " not found");
		}
		InputStream inputStream = zipFile.getInputStream(entry);

		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer);
		inputStream.close();

		byte[] decodedString = decodeBase64(writer.toString());
		return new String(decodedString);
	}
	
}
