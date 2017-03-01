(function() {
    'use strict';

    /**
     * @ngdoc controller
     * @name data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
     * @description Actions suggestion controller
     * @requires data-prep.services.transformation.service:ColumnSuggestionService
     * @requires data-prep.services.playground.service:PlaygroundService
     */
    function ActionsSuggestionsCtrl(ColumnSuggestionService, PlaygroundService, TransformationService, PreparationService) {
        var vm = this;
        vm.columnSuggestionService = ColumnSuggestionService;

        /**
         * @ngdoc property
         * @name dynamicTransformation
         * @propertyOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description The dynamic param transformation to display
         */
        vm.dynamicTransformation = null;

        /**
         * @ngdoc property
         * @name dynamicFetchInProgress
         * @propertyOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description Flag that indicates if a fetch of dynamic parameters is in progress
         */
        vm.dynamicFetchInProgress = false;

        /**
         * @ngdoc property
         * @name showDynamicModal
         * @propertyOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description Flag that change the dynamic parameters modal display
         */
        vm.showDynamicModal = false;

        /**
         * @ngdoc method
         * @name initDynamicParams
         * @methodOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description [PRIVATE] Fetch the transformation dynamic parameters and inject them into transformation menu params
         * @returns {promise} The GET request promise
         */
        var initDynamicParams = function(transfo) {
            var infos = {
                columnId: vm.column.id,
                datasetId:  PlaygroundService.currentMetadata.id,
                preparationId:  PreparationService.currentPreparationId
            };
            return TransformationService.initDynamicParameters(transfo, infos);
        };

        /**
         * @ngdoc method
         * @name select
         * @methodOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description Transformation selection.
         <ul>
            <li>Dynamic transformation : fetch the dynamic parameters and show the modal</li>
            <li>Static transformation : append the new step in the current preparation</li>
         </ul>
         */
        vm.select = function select(transfo) {
            if(transfo.dynamic) {
                vm.dynamicTransformation = transfo;
                vm.dynamicFetchInProgress = true;
                vm.showDynamicModal = true;

                //get new parameters
                initDynamicParams(transfo).finally(function() {
                    vm.dynamicFetchInProgress = false;
                });
            }
            else {
                vm.transformClosure(transfo)();
            }
        };

        /**
         * @ngdoc method
         * @name transformClosure
         * @methodOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
         * @description Transformation application closure. It take the transformation to build the closure.
         * The closure then take the parameters and append the new step in the current preparation
         */
        vm.transformClosure = function transform(transfo) {
            return function(params) {
                PlaygroundService.appendStep(transfo.name, vm.column, params)
                    .then(function() {
                        vm.showDynamicModal = false;
                    });
            };
        };
    }

    /**
     * @ngdoc property
     * @name column
     * @propertyOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
     * @description The transformations column.
     * This is bound to {@link data-prep.services.transformation:ColumnSuggestionService ColumnSuggestionService}.currentColumn
     */
    Object.defineProperty(ActionsSuggestionsCtrl.prototype,
        'column', {
            enumerable: true,
            configurable: false,
            get: function () {
                return this.columnSuggestionService.currentColumn;
            }
        });

    /**
     * @ngdoc property
     * @name suggestions
     * @propertyOf data-prep.actions-suggestions.controller:ActionsSuggestionsCtrl
     * @description The suggested transformations list.
     * This is bound to {@link data-prep.services.transformation:ColumnSuggestionService ColumnSuggestionService}.transformations
     */
    Object.defineProperty(ActionsSuggestionsCtrl.prototype,
        'suggestions', {
            enumerable: true,
            configurable: false,
            get: function () {
                return this.columnSuggestionService.transformations;
            }
        });

    angular.module('data-prep.actions-suggestions')
        .controller('ActionsSuggestionsCtrl', ActionsSuggestionsCtrl);
})();