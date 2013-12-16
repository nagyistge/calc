/**
 * Data provider for calculation step test
 *  
 * @author S. Ricci
 */
function CalculationStepTestDataProvider(jobId, entityId, variables) {
	// base context path for rest call
	this.contextPath = "rest/job/test/";
	
	this.jobId = jobId;
	this.entityId = entityId;
	this.variables = variables;
}

CalculationStepTestDataProvider.prototype = (function() {
	
	/**
	 * Count the number of available records to show
	 */
	var count = function(success) {
		$.ajax({
			url : this.contextPath + "results/count.json",
			data: {jobId: this.jobId},
			dataType : "json"
		}).done(function(response) {
			var cnt = response.fields.count;
			success(cnt);
		});
	};
	
	/**
	 * query the server to get the data
	 */
	var data = function(offset , numberOfItems , excludeNulls, variables, success) {
		// prepare request parameters
		var data = { jobId: this.jobId, offset:offset };
		if(numberOfItems) {
			data.numberOfRows = numberOfItems;
		}
		if(excludeNulls){
			data.excludeNulls = excludeNulls;
		}
		
		$.ajax({
			url : this.contextPath + "results.json" ,
			dataType : "json",
			data : data
		}).done(function(response) {
			success(response);
		});
	};
	
	
	//prototype
	return {
		constructor : CalculationStepTestDataProvider,
		
		//public methods
//		_init : init,
		
		count : count ,
		
		data : data
		
	};
})();
