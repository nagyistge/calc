/**
 * 
 * Manager for calculation step execute and test sections
 * 
 * @author Mino Togna
 * @author Stefano Ricci
 *  
 */
function CalculationStepExecManager(container, calculationStepResultsManager) {
	// main ui container
	this.container = container;
	// results container to show after job execution
	this.calculationStepResultsManager = calculationStepResultsManager;
	// header
	this.header = this.container.find(".header");
	//exec btn
	this.execButton = this.container.find('button[name=exec-btn]');
	//test section
	this.testContainer = this.container.find(".test");
	//test btn
	this.testButton = this.container.find("button[name=test-btn]");
	//test input variables rows container
	this.settingsRowsContainer = this.container.find(".input-var-rows");
	//input variable test parameters row template
	this.testInputVariableRowTemplate = this.container.find(".test-input-var.template");
	
	//calc step associated. it's set by the show method 
	this.calculationStep = null;
	
	//job manager
	this.jobManager = new JobManager();
	this.calculationStepManager = new CalculationStepManager();
	
	//array with the variable settings
	this.settingsRows = new Array();
	
	this._init();
};

CalculationStepExecManager.prototype = (function() {
	
	/**
	 *  init function
	 */
	var init = function() {
		var $this = this;
		
		/*
		 * event handlers
		 */
		//start step execution on click
		this.execButton.click(function(e) {
			UI.disableAll();
			$this.jobManager.executeCalculationStep(
					$this.calculationStep.id, 
					//on complete show results
					function(job) {
						UI.enableAll();
						
						// instanciate data provider
						var entityId = $this.calculationStep.outputEntityId;
						var variables  = $this.calculationStep.variables;
						var dataProvider = new DataViewProvider(entityId , variables);
						
						// once completed hide this and shows results section
						$this.hide();
						$this.calculationStepResultsManager.show(dataProvider);
					}
					, true
			);
		});
		
		//validate parameters and starts the execution of the test
		this.testButton.click(function(e) {
			if ( $.proxy(validateTestSettings, $this)() ) {
				UI.disableAll();
				var variablesParameters = $.proxy(extractVariablesSettings, $this)();
				var parameters = {variables: variablesParameters};
				$this.calculationStepManager.test(
					$this.calculationStep.id,
					parameters,
					//on complete show results
					function(job) {
						UI.enableAll();
						
						// instanciate data provider
						var entityId = $this.calculationStep.outputEntityId;
						var variables  = $this.calculationStep.variables;
						var dataProvider = new CalculationStepTestDataProvider(job.id, entityId, variables);
						
						// once completed hide this and shows results section
						$this.hide();
						$this.calculationStepResultsManager.show(dataProvider);
					}
					, true
				);
			}
		});
	};
	
	/**
	 * show exec section
	 */
	var show = function(calculationStep) {
		this.calculationStep = calculationStep;
		
		// append legend with step name to header
		this.header.empty();
		var legend = $("<legend></legend>");
		legend.html(this.calculationStep.caption);
		this.header.append( legend );
		
		// update input variables rows
		$.proxy(updateTestSettings, this)();
		
		//show main container
		this.container.fadeIn(400);
	};
	
	var hide = function () {
		this.container.hide();
	};
	
	var updateTestSettings = function() {
		var $this = this;
		
		//delete rows corresponding to unused variables
		var unusedRows = $.proxy(getUnusedVariablesRows, $this)();
		$.each(unusedRows, function(index, row) {
			row.remove();
			ArrayUtils.removeItem($this.settingsRows, row);
		});
		
		//add new variable rows
		$.each($this.calculationStep.inputVariables, function(index, variableName) {
			var oldRow = $.proxy(getSettingsRow, $this)(variableName);
			if ( oldRow == null ) {
				var row = new VariableSettingsRow(variableName, $this.testInputVariableRowTemplate);
				$this.settingsRows.push(row);
				$this.settingsRowsContainer.append(row.rowElement);
			}
		});
	};
	
	var getSettingsRow = function(variableName) {
		for(var i=0; i < this.settingsRows.length; i++) {
			var row = this.settingsRows[i];
			if ( row.variableName == variableName ) {
				return row;
			}
		}
		return null;
	};
	
	var extractVariablesSettings = function() {
		var result = {};
		$.each(this.settingsRows, function(index, row) {
			var params = row.extractParameters();
			result[row.variableName] = params;
		});
		return result;
	};
	
	var validateTestSettings = function() {
		var result = true;
		$.each(this.settingsRows, function(index, row) {
			var valid = row.validate();
			result = result && valid;
		});
		return result;
	};
	
	var getUnusedVariablesRows = function() {
		var result = new Array();
		var inputVariables = this.calculationStep.inputVariables;
		$.each(this.settingsRows, function(index, row) {
			var variableName = row.variableName;
			var unused = inputVariables.indexOf(variableName) < 0;
			if ( unused ) {
				result.push(row);
			}
		});
		return result;
	};
	
	//prototype
	return {
		constructor : CalculationStepExecManager,
		
		//public methods
		_init : init,
		
		//show / hide 
		show : show
		,
		hide : hide
		
	};
})();

/**
 * Wrapper for a calculation step variable parameters row
 */
function VariableSettingsRow(variableName, rowTemplate) {
	this.variableName = variableName;
	
	this.rowElement = rowTemplate.clone();
	this.rowElement.removeClass("template");
	this.rowElement.show();
	this.variableNameLabel = this.rowElement.find(".variable-name");
	this.minField = this.rowElement.find("input[name=min]");
	this.maxField = this.rowElement.find("input[name=max]");
	this.incrementField = this.rowElement.find("input[name=increment]");

	//set default values in elements
	var defaultMin = 0;
	var defaultMax = 100;
	var defaultIncrement = 10;

	this.variableNameLabel.text(variableName);
	this.minField.val(defaultMin);
	this.maxField.val(defaultMax);
	this.incrementField.val(defaultIncrement);
	
	this._init();
};

VariableSettingsRow.prototype = (function() {
	
	/**
	 * Initializes event listeners on the fields
	 */
	var init = function() {
		//on fields change, validate the fields
		this.minField.change($.proxy(validate, this));
		this.maxField.change($.proxy(validate, this));
		this.incrementField.change($.proxy(validate, this));
	};
	
	/**
	 * Validates the fields and updates the UI error feedback
	 */
	var validate = function() {
		UI.Form.removeErrors(this.rowElement);
		var minValid = UI.Form.validation.required(this.minField, "Min") && UI.Form.validation.numeric(this.minField);
		var maxValid = UI.Form.validation.required(this.maxField, "Max") && UI.Form.validation.numeric(this.maxField);		
		var incrementValid = UI.Form.validation.required(this.incrementField, "Increment") && UI.Form.validation.numeric(this.incrementField);
		return minValid && maxValid && incrementValid;
	};
	
	/**
	 * Removes the row element from the UI
	 */
	var remove = function() {
		this.rowElement.remove();
	};
	
	/**
	 * Extracts the variable settings from the input field values  
	 */
	var extractParameters = function () {
		var result = { 
			variableName: this.variableName,
			max: this.maxField.val(),
			min: this.minField.val(),
			increment: this.incrementField.val()
		};
		return result;
	};
	
	//prototype
	return {
		constructor : VariableSettingsRow,
		
		//public methods
		_init : init,
		
		validate : validate
		,
		remove : remove
		,
		extractParameters : extractParameters
		
	};
})();
