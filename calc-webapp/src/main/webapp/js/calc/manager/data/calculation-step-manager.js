/**
 * Manager for CalculationStep model class 
 */
function CalculationStepManager() {
	
}

CalculationStepManager.prototype = (function() {

	var contextPath = "rest/calculationstep";
	
	/**
	 * Load all the calculation steps for the active workspace
	 */
	var loadAll = function(callback) {
		$.ajax({
			url: contextPath + "/load.json",
			dataType:"json"
		}).done(function(response){
			callback(response);
		});
	};
	
	/**
	 * Load the calculation step with the specified id and call the callback function
	 */
	var load = function(id, callback) {
		$.ajax({
			url: contextPath + "/"+id+"/load.json",
			dataType:"json"
		})
		.done(function(response){
			if ( callback ) {
				callback(response);
			}
		});
	};
	
	/**
	 * Inserts or updates a calculation step 
	 */
	var save = function($step, successCallback, errorCallback, completeCallback) {
		$.ajax({
			url: contextPath + "/save.json",
			dataType: "json",
			data: $step,
			type: "POST"
		})
		.done(function(response) {
    		if(successCallback) {
	    		successCallback(response);
    		};
		})
		.error(function(e) {
			if ( errorCallback ) {
				errorCallback(e);
			}
		})
		.complete(function() {
			if ( completeCallback ) {
				completeCallback();
			}
		});
		
	};
	
	
	/**
	 * Delete the calculation step with the specified id
	 */
	var remove = function(id, callback) {
		$.ajax({
			url: contextPath + "/"+id+"/delete.json",
			dataType:"json",
			type: "POST"
		})
		.done(function(response){
			if ( callback ) {
				callback(response);
			}
		});
	};
	
	/**
	 * Updates the calculation step sortOrder with the specified one
	 */
	var updateStepNumber = function(id, stepNo, callback) {
		$.ajax({
			url: contextPath + "/"+id+"/stepno/" + stepNo + ".json",
			dataType:"json",
			type: "POST"
		})
		.done(function(response){
			if ( callback ) {
				callback(response);
			}
		});
	};
	
	/**
	 * Executes a job for the calculation step test with id stepId
	 */
	var test = function(stepId, parameters, success, hideModalStatusOnComplete){
		$.ajax({
			url : contextPath + "/"+stepId+"/test.json",
			type: "POST", 
			data: JSON.stringify(parameters),
			dataType: "json",
			contentType: "application/json"
		}).done(function(response) {
//			console.log("job manager job executed");
//			console.log(response);
			checkJobStatus(success, false, hideModalStatusOnComplete);
		})
		.error(function(e){
			console.log("error!!! on test calculation step");
			console.log(e);
		});
	};
	
	//prototype
	return {
		constructor : CalculationStepManager,
		
		//public methods
		loadAll : loadAll
		,
		load : load
		,
		save : save
		,
		remove : remove
		,
		updateStepNumber: updateStepNumber
		,
		test : test
	};
})();

//singleton instance
var _calculationStepManager = null;
CalculationStepManager.getInstance = function() { 
	if(!_calculationStepManager){
		_calculationStepManager = new CalculationStepManager();
	}
	return _calculationStepManager;
};
