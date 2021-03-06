/**
 * 
 */
package org.openforis.calc.engine;

import static org.openforis.calc.r.RScript.COMMA;
import static org.openforis.calc.r.RScript.NEW_LINE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.SelectQuery;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.openforis.calc.metadata.Aoi;
import org.openforis.calc.metadata.AoiLevel;
import org.openforis.calc.metadata.CategoricalVariable;
import org.openforis.calc.metadata.Entity;
import org.openforis.calc.metadata.QuantitativeVariable;
import org.openforis.calc.metadata.SamplingDesign.ColumnJoin;
import org.openforis.calc.metadata.SamplingDesign.TwoStagesSettings;
import org.openforis.calc.psql.Psql;
import org.openforis.calc.r.RVariable;
import org.openforis.calc.r.SetValue;
import org.openforis.calc.schema.DataSchema;
import org.openforis.calc.schema.DataTable;
import org.openforis.calc.schema.EntityAoiTable;
import org.openforis.calc.schema.ErrorTable;
import org.openforis.calc.schema.ExpansionFactorTable;
import org.openforis.calc.schema.FactTable;
import org.openforis.calc.schema.StratumDimensionTable;

/**
 * Task responsible of calculating the error for the given error table
 * Now based only on "Formulas for estimators and their variances in NFI 28.2.2014 K.T. Korhonen & Olli Salmensuu, point estimators"
 *  
 * @author Mino Togna
 *
 */
@Deprecated
public class CalculateErrorTask extends CalcRTask {

	private ErrorTable errorTable;
	private RVariable connection;
	private Workspace workspace;
	private Aoi aoi;

	protected CalculateErrorTask( CalcJob job , ErrorTable errorTable , RVariable connection , Aoi aoi){
		super( job.getrEnvironment() , getName(errorTable.getQuantitativeVariable(), aoi , errorTable.getCategoricalVariable()) );
		setJob( job );
		
		this.errorTable = errorTable;
		this.connection = connection;
		this.aoi = aoi;
		this.workspace = errorTable.getQuantitativeVariable().getEntity().getWorkspace();
		
		initScript();
	}

	void initScript() {
		
//		boolean stratified = this.workspace.hasStratifiedSamplingDesign();
		
		QuantitativeVariable quantitativeVariable = errorTable.getQuantitativeVariable();
		boolean areaError = quantitativeVariable.getId() == -1;
		
//		Aoi aoi = errorTable.getAoi();
		CategoricalVariable<?> categoricalVariable = errorTable.getCategoricalVariable();
		
		String name = getName( quantitativeVariable, aoi, categoricalVariable );

		addScript( r().rScript( "# ==================== " + name + " ====================" ) );
		
		DataSchema schema = (DataSchema) this.errorTable.getSchema();
//		if( stratified ){
		// add read data scripts
		// strata
		Select<?> selectStrata = getStratumSelect( aoi, schema );
		RVariable strata = r().variable("strata");
		addScript( r().setValue( strata , r().dbGetQuery(connection, selectStrata) ) );			
//		}
		
		Select<?> selectPlots = getPlotSelect( aoi, schema, quantitativeVariable, this.errorTable.getCategoricalVariable() );
		// plots
		RVariable plots = r().variable("plots");
		addScript( r().setValue( plots , r().dbGetQuery(connection, selectPlots) ) );
		
		if( ! areaError ){
			// data (e.g. trees)
			Select<?> selectData = getDataSelect( aoi, schema,  quantitativeVariable,  this.errorTable.getCategoricalVariable() );
			RVariable data = r().variable("data");
			addScript( r().setValue( data , r().dbGetQuery(connection, selectData) ) );
		}
		
		
		String dataFrameName = areaError ? "plots" : "data";
		
		// for now hardcoded. let's see if in the future there's time for refactoring
		StringBuilder sb = new StringBuilder();
		
		sb.append( "if( nrow("+dataFrameName+") > 0){" );
		
		RVariable classes = r().variable( "classes" );
		SetValue setClasses = r().setValue( classes, r().sqldf( "select distinct class_id from " + dataFrameName ) );
//		addScript( setClasses );
		sb.append( setClasses.toString() );
		setClasses = r().setValue( classes, r().variable(classes, "class_id") );
		sb.append( setClasses.toString() );
//		addScript( setClasses );
		
		sb.append("for( cls in classes ){");
			sb.append( NEW_LINE );
			sb.append("select <- \"select *, case when class_id ==\";");
			sb.append( NEW_LINE );
			sb.append("select <- paste( select , cls , sep=\" \" );");
			sb.append( NEW_LINE );
			sb.append("select  <- paste( select , \"then 1 else 0 end as class from plots\" , sep=\" \" );");
			sb.append( NEW_LINE );
			sb.append("plotsClone <-  sqldf( select );");
			sb.append( NEW_LINE );
			
			if( !areaError ){
				sb.append("select <- \"select *, case when class_id ==\";");
				sb.append( NEW_LINE );
				sb.append("select <- paste( select , cls , sep=\" \" );");
				sb.append( NEW_LINE );
				sb.append("select  <- paste( select , \"then 1 else 0 end as class from data\" , sep=\" \" );");
				sb.append( NEW_LINE );
				sb.append("dataClone <-  sqldf( select );");
				sb.append( NEW_LINE );
			}
			
//			String setErrorsString = "errors <- calculateQuantityError( data = dataClone , plots = plotsClone ";
//			if( stratified ){
//				setErrorsString +=  ", strata = strata ";
				
//			} else {
//				sb.append( setErrorsString );
//			}
//			}
//			setErrorsString += ") ;";
//			sb.append( setErrorsString );
			if( areaError ){
				
				sb.append( "errors <- calculateAreaError( plotsClone , strata ) ;");
				sb.append( NEW_LINE );
				
			} else {
				
				sb.append( "errors <- calculateQuantityError( dataClone , plotsClone , strata ) ;");
				sb.append( NEW_LINE );
			}
			
			
			// insert results
			sb.append( "query <- 'INSERT INTO " );
			sb.append( NEW_LINE );
			sb.append( errorTable.getName() );
			sb.append( NEW_LINE );
			
			
			sb.append( "(" );
			if( areaError ){
				sb.append( NEW_LINE );
				sb.append( errorTable.getAreaAbsoluteError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getAreaRelativeError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getAreaVariance().getName() );

			} else {
				
				sb.append( NEW_LINE );
				sb.append( errorTable.getMeanQuantityAbsoluteError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getMeanQuantityRelativeError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getMeanQuantityVariance().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getTotalQuantityAbsoluteError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getTotalQuantityRelativeError().getName() );
				sb.append( COMMA );
				sb.append( NEW_LINE );
				sb.append( errorTable.getTotalQuantityVariance().getName() );
			}
			
			
			sb.append( COMMA );
			sb.append( NEW_LINE );
			sb.append( errorTable.getCategoryIdField().getName() );
			sb.append( COMMA );
			sb.append( NEW_LINE );
			sb.append( errorTable.getAggregateFactCountField().getName() );
//			sb.append( COMMA );
//			sb.append( NEW_LINE );
			Aoi aoiInsert = aoi;
			while( aoiInsert != null ){
				sb.append( COMMA );
				sb.append( NEW_LINE );
				AoiLevel aoiLevel = aoiInsert.getAoiLevel();
				sb.append( errorTable.getAoiIdField(aoiLevel).getName() );
				
				aoiInsert = aoiInsert.getParentAoi() ;
			}
			sb.append( NEW_LINE );
			sb.append( ")" );
			sb.append( NEW_LINE );
			
			sb.append( "VALUES(';" );
			sb.append( NEW_LINE );
		
			if( areaError ){
				
				sb.append( "query <- paste( query , ifelse( is.na(errors$areaAbsolute) , -1 , errors$areaAbsolute) , sep=\"\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$areaRelative) , -1 , errors$areaRelative ) , sep=\",\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$areaVariance) , -1 , errors$areaVariance) , sep=\",\" );" );
				
			} else {
				
				sb.append( "query <- paste( query , ifelse( is.na(errors$meanQuantityAbsolute) , -1 , errors$meanQuantityAbsolute) , sep=\"\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$meanQuantityRelative) , -1 , errors$meanQuantityRelative ) , sep=\",\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$meanQuantityVariance) , -1 , errors$meanQuantityVariance) , sep=\",\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$totalQuantityAbsolute) , -1 , errors$totalQuantityAbsolute) , sep=\",\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$totalQuantityRelative) , -1 , errors$totalQuantityRelative) , sep=\",\" );" );
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , ifelse( is.na(errors$totalQuantityVariance) , -1 , errors$totalQuantityVariance) , sep=\",\" );" );
			}
			sb.append( NEW_LINE );
			sb.append( "query <- paste( query , cls , sep=\",\"  );" );
			sb.append( NEW_LINE );
			sb.append( "query <- paste( query , 100 , sep=\",\"  );" );

			aoiInsert = aoi;
			while( aoiInsert != null ){
				sb.append( NEW_LINE );
				sb.append( "query <- paste( query , "+aoiInsert.getId()+" , sep=\",\"  );" );
				
				aoiInsert = aoiInsert.getParentAoi() ;
			}
			sb.append( NEW_LINE );
			
			sb.append( NEW_LINE );			
			sb.append( "query <- paste( query , \")\" , sep=\"\"  );" );
			sb.append( NEW_LINE );
			
			sb.append( "dbSendQuery( conn = connection , statement = query );" );
			sb.append( NEW_LINE );
			
		// end for	
		sb.append("}");
		sb.append( NEW_LINE );
		// end if
		sb.append("}");
		sb.append( NEW_LINE );
		
		addScript( r().rScript(sb.toString()));
	}

	private Select<?> getStratumSelect( Aoi aoi , DataSchema schema ){
		
		StratumDimensionTable stratumTable = schema.getStratumDimensionTable();
		ExpansionFactorTable expfTable = schema.getExpansionFactorTable( aoi.getAoiLevel() );

		SelectQuery<Record> select = new Psql().selectQuery();
		if( workspace.has2StagesSamplingDesign() ){
			
			List<ColumnJoin> psuJoinColumns = workspace.getSamplingDesign().getTwoStagesSettingsObject().getPsuIdColumns();
			List<Field<?>> psuIdFields = new LinkedList<Field<?>>();
			for (ColumnJoin columnJoin : psuJoinColumns) {
				Field<?> field = expfTable.field( columnJoin.getColumn() );
				psuIdFields.add( field );
			}
			
			Field<?> psuId = getPsuIdField( psuIdFields );
			select.addSelect( psuId );
			
			select.addSelect( expfTable.PSU_TOTAL );
			select.addSelect( expfTable.PSU_SAMPLED_TOTAL );
			select.addSelect( expfTable.PSU_AREA );
			select.addSelect( expfTable.SSU_TOTAL );
			select.addSelect( expfTable.SSU_COUNT );
			select.addSelect( expfTable.NO_THEORETICAL_BU );
			select.addSelect( expfTable.WEIGHT );
			
		} else {
			select.addSelect( expfTable.AREA );
		}
		
		select.addFrom( expfTable );
		select.addConditions( expfTable.AOI_ID.eq(aoi.getId()) );
		
		if( workspace.hasStratifiedSamplingDesign() ){
			select.addSelect( stratumTable.getStratumNo().as( "stratum") );
			
			select.addJoin( stratumTable, 
					expfTable.STRATUM.eq( stratumTable.getStratumNo() )
					.and( stratumTable.getWorkspaceId().eq( workspace.getId() ) )
					);
		} else {
			select.addSelect( DSL.val(1).as( "stratum") );
		}
	
		return select;
	}

	private Field<?> getPsuIdField(List<? extends Field<?>> psuIdFields) {
		Field<?> psuId = null;
		for( Field<?> field : psuIdFields ){
			if( psuId == null ){
				psuId = DSL.concat( field );
			} else {
				psuId = psuId.concat( "_" ).concat( field );
			}
		}
		psuId = psuId.as("psu_id");
		return psuId;
	}
	
	private Select<?> getPlotSelect( Aoi aoi , DataSchema schema , QuantitativeVariable quantitativeVariable, CategoricalVariable<?> category ){
		Entity samplingUnit 					= workspace.getSamplingUnit();
		FactTable suFactTable 					= schema.getFactTable( samplingUnit );
		
		EntityAoiTable suAoiTable 				= schema.getEntityAoiTable( samplingUnit );
		
//		FactTable factTable 					= schema.getFactTable( quantitativeVariable.getEntity() );
//		SamplingUnitAggregateTable suAggTable 	= factTable.getSamplingUnitAggregateTable();
		
		
		SelectQuery<Record> select = new Psql().selectQuery();
		select.setDistinct(true);
		select.addSelect( suFactTable.getSamplingUnitIdField().as("plot_id") );
		if( workspace.hasStratifiedSamplingDesign() ){
			select.addSelect( suFactTable.getStratumField().as("stratum") );
		} else {
			select.addSelect( DSL.val(1).as( "stratum") );
		}
		if( workspace.hasClusterSamplingDesign() ){
			select.addSelect( suFactTable.getClusterField().as("cluster") );
		}
		if( workspace.has2StagesSamplingDesign() ){
			TwoStagesSettings twoStagesSettings = workspace.getSamplingDesign().getTwoStagesSettingsObject();
			List<ColumnJoin> psuJoinColumns = twoStagesSettings.getSamplingUnitPsuJoinColumns();
			List<Field<?>> psuIdFields = new LinkedList<Field<?>>();
			for (ColumnJoin columnJoin : psuJoinColumns) {
				Field<?> field = suFactTable.field( columnJoin.getColumn() );
				psuIdFields.add( field );
			}
			
			Field<?> psuIdField = getPsuIdField( psuIdFields );
			select.addSelect( psuIdField );
			
			select.addSelect( suFactTable.SSU_ID );
		}
		
		select.addSelect( suFactTable.field(DataTable.WEIGHT_COLUMN) );
		
		select.addFrom( suFactTable );
		
//		if( category.getEntity().isSamplingUnit() ){
		Field<Integer> dimensionIdField = suFactTable.getDimensionIdField( category );
		select.addSelect( dimensionIdField.as( "class_id" ) );
			
//		} else {
//			select.addJoin( suAggTable , JoinType.LEFT_OUTER_JOIN , suAggTable.getSamplingUnitIdField().eq(suFactTable.getIdField()) );
//			
//			Field<Integer> dimensionIdField = suAggTable.getDimensionIdField( category );
//			select.addSelect( dimensionIdField.as( "class_id" ) );
//		}
		
		if( workspace.has2StagesSamplingDesign() ){
			
			select.addConditions( suFactTable.getAoiIdField(aoi.getAoiLevel()).eq( aoi.getId().intValue() ) );
			
		} else {
			select.addJoin( suAoiTable, suAoiTable.getIdField().eq(suFactTable.getSamplingUnitIdField()) );
			select.addConditions( suAoiTable.getAoiIdField(aoi.getAoiLevel()).eq( aoi.getId().longValue() ) );
		}
		
		
		return select;
	}
	
	private Select<?> getDataSelect( Aoi aoi , DataSchema schema , QuantitativeVariable quantity , CategoricalVariable<?> category ) {
		Workspace workspace = schema.getWorkspace();
		Entity samplingUnit = workspace.getSamplingUnit();
		Entity entity = quantity.getEntity();
		FactTable factTable = schema.getFactTable(entity);
		EntityAoiTable suAoiTable = schema.getEntityAoiTable( samplingUnit );
		
		SelectQuery<Record> select = new Psql().selectQuery();
		select.setDistinct(true);
		
		select.addSelect( factTable.getSamplingUnitIdField().as("plot_id") );
		select.addGroupBy( factTable.getSamplingUnitIdField() );
		
		if( workspace.hasStratifiedSamplingDesign() ){
			select.addSelect( factTable.getStratumField().as("stratum") );
			select.addGroupBy( factTable.getStratumField() );
		} else {
			select.addSelect( DSL.val(1).as( "stratum") );
		}
		
		if( workspace.hasClusterSamplingDesign() ){
			select.addSelect( factTable.getClusterField().as("cluster") );
			select.addGroupBy( factTable.getClusterField() );		
		}
		
		if( workspace.has2StagesSamplingDesign() ){
			List<ColumnJoin> psuJoinColumns = workspace.getSamplingDesign().getTwoStagesSettingsObject().getSamplingUnitPsuJoinColumns();
			List<Field<?>> psuIdFields = new LinkedList<Field<?>>();
			for (ColumnJoin columnJoin : psuJoinColumns) {
				Field<?> field = factTable.field( columnJoin.getColumn() );
				psuIdFields.add( field );
				
				select.addGroupBy( field );
			}
			
			Field<?> psuIdField = getPsuIdField( psuIdFields );
			select.addSelect( psuIdField );
			
			select.addSelect( factTable.SSU_ID );
			select.addGroupBy( factTable.SSU_ID );
		}
		
		Field<BigDecimal> plotAreaField = factTable.getPlotAreaField();
		select.addSelect( DSL.sum( factTable.getQuantityField(quantity).div(plotAreaField) ).as("quantity") );
		
		Field<Integer> dimensionIdField = factTable.getDimensionIdField( category );
		select.addSelect( dimensionIdField.as( "class_id" ) );
		select.addGroupBy( dimensionIdField );
		
		select.addFrom( factTable );
		
		if( workspace.has2StagesSamplingDesign() ){
			
			select.addConditions( factTable.getAoiIdField(aoi.getAoiLevel()).eq( aoi.getId().intValue() ) );
			
		} else {
			select.addJoin( suAoiTable, suAoiTable.getIdField().eq( factTable.getParentIdField() ) );
			select.addConditions( suAoiTable.getAoiIdField(aoi.getAoiLevel()).eq( aoi.getId().longValue() ) );
		}
		
		return select;
	}
	
	private static String getName( QuantitativeVariable quantitativeVariable , Aoi aoi, CategoricalVariable<?> categoricalVariable ){
		StringBuilder taskName = new StringBuilder();
		
		taskName.append( "Calculate error for " );
		taskName.append( quantitativeVariable.getName() );
		taskName.append( " " );
		taskName.append( aoi.getCaption() );
		taskName.append( " " );
		taskName.append( categoricalVariable.getName() );
		
		return taskName.toString();
	}

}
