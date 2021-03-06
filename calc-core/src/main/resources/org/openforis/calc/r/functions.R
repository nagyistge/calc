#= remove all objects in session
rm( list = ls() ) ;

# processing chain starting time
calc.startTime <- Sys.time();

#== 
#== function used by open foris calc to check if the execution of a task returned an error 
#== 
checkError <- function( e, connection=NULL ){
  if( inherits(e, "try-error") || inherits(e, "simpleError") ){
    print("CALC-ERROR",quote=F);
    if( !is.null(connection) ){
      dbDisconnect(connection);
    }
    stop(e);
  }
};

#===
#=== Function used to load a library if installed, if not it installs it first 
#===
usePackage <- function(p)  {
  if (!is.element(p, installed.packages()[,1]))
    install.packages(p, dep = TRUE)
  library(p, character.only = TRUE)
};

#===
#=== Calc logging functions
#===
calc.log <- function( step, msg , level ){
  row1 <- paste( Sys.time() , step , sep = "     step : ");
  cat( row1  );
  
  row2 <- paste("\n" , level , sep="");
  row2 <- paste(row2 , ':' , sep="");
  row2 <- paste(row2 , msg , sep=" ");
  row2 <- paste(row2 , '\n' , sep=" ");
  cat( row2  );
};
calc.info <- function( step, msg ){
  calc.log( step , msg , "INFO" );
};
calc.warn <- function( step, msg ){
  calc.log( step , msg , "WARN" );
};
calc.error <- function( step, msg ){
  calc.log( step , msg , "ERROR" );
};
calc.debug <- function( step, msg ){
  calc.log( step , msg , "DEBUG" );
};
calc.info("1-init.R","Processing chain started");
#===
#=== Extracts the content of a file and returns it as an SQL quoted string  
#===
calc.getQuotedFileContentOLD <- function( filename ){
  
  filePath <- paste(scriptDir , filename , sep = .Platform$file.sep);
  
  fileContent <- readChar( filePath , file.info( filePath )$"size");
  
  fileContentQuoted <- dbQuoteString( conn = connection , x = fileContent );
    
  return ( fileContentQuoted );
};

calc.getQuotedFileContent <- function( filename ){
  newLinePlaceHolder <- 'CALC_NEW_LINE_PLACEHOLDER';
  
  filePath <- paste(scriptDir , filename , sep = .Platform$file.sep);
  
  c <- file(filePath, encoding = "UTF-8");
  fileContent <- paste(readLines(c, warn = F), collapse = newLinePlaceHolder)
  close(c);
  
  fileContent <- dbQuoteString( conn = connection , x = fileContent );
  fileContent <- gsub(newLinePlaceHolder,'\n',fileContent);

  return ( fileContent );
};

calc.persistUserScript <- function( filename , table , column , id ){
	fileContent <- calc.getQuotedFileContent( filename );
	
	query <- 'UPDATE calc.';
	query <- paste(query , table , sep = '');
	query <- paste(query , 'SET' , sep = ' ');
	query <- paste(query , column , sep = ' ');
	query <- paste(query , '=' , sep = ' ');
	query <- paste(query , fileContent , sep = ' ');
	query <- paste(query , 'WHERE id =' , sep = ' ');
	query <- paste(query , id , sep = ' ');
	
	#print( query );
	
	dbSendQuery(conn=connection, statement=query);
};

calc.persistCommonScript <- function( filename , id ){
	calc.persistUserScript( filename , 'processing_chain' , 'common_script' , id );
};

calc.persistBaseUnitWeightScript <- function( filename , id ){
	calc.persistUserScript( filename , 'sampling_design' , 'sampling_unit_weight_script' , id );
};

calc.persistEntityPlotAreaScript <- function( filename , id ){
	calc.persistUserScript( filename , 'entity' , 'plot_area_script' , id );
};

calc.persistCalculationStepScript <- function( filename , id ){
	calc.persistUserScript( filename , 'calculation_step' , 'script' , id );
};

calc.persistErrorScript <- function( filename , id ){
	calc.persistUserScript( filename , 'error_settings' , 'script' , id );
};

# sqldf options. 
# driver is set to SQLLite in order to read from dataframe , otherwise it uses PostgreSQL which is the default driver loaded by Calc
# https://code.google.com/p/sqldf/#Troubleshooting
options (
  gsubfn.engine = "R" , 
  sqldf.driver = "SQLite"
);

usePackage("RPostgreSQL");
usePackage("sqldf");

scriptDir <- dirname(sys.frame(1)$ofile);
if( scriptDir == 'system' ){
  scriptDir <- '.';
}

userScriptDir <- paste(scriptDir , "user" , sep = .Platform$file.sep);
