(function () {
    'use strict';

    /**
     * @ngdoc controller
     * @name data-prep.recipe.controller:RecipeBulletCtrl
     * @description The recipeBullet controller
     * @requires data-prep.services.recipe.service:RecipeService
     * @requires data-prep.services.recipe.service:RecipeBulletService
     */
    function RecipeBulletCtrl(RecipeService, RecipeBulletService) {
        var vm = this;

        /**
         * @ngdoc property
         * @name stepIndex
         * @propertyOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description The step index in the recipe
         * @type {number}
         */
        vm.stepIndex = RecipeService.getStepIndex(vm.step);

        /**
         * @ngdoc method
         * @name getBulletsToChange
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @param {object} allSvgs The list of bullets
         * @description Get the bullets to modify, according to the current bullet
         * @returns The bullets elements to changed
         */
        vm.getBulletsToChange = function (allSvgs) {
            //current step active : we should deactivate all the steps from current to the end
            if (!vm.step.inactive) {
                return allSvgs.slice(vm.stepIndex);
            }
            //current step inactive : we should activate the steps from last inactive to the current
            else {
                var lastActiveStepIndex = RecipeService.getActiveThresholdStepIndex();
                return allSvgs.slice(lastActiveStepIndex + 1, vm.stepIndex + 1);
            }
        };

        /**
         * @ngdoc method
         * @name isStartChain
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description Test if step is the first element of the chain
         * @returns true if step is the first step
         */
        vm.isStartChain = function () {
            return RecipeService.isFirstStep(vm.step);
        };

        /**
         * @ngdoc method
         * @name isEndChain
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description Test if step is the last element of the chain
         * @returns true if step is the last step
         */
        vm.isEndChain = function () {
            return RecipeService.isLastStep(vm.step);
        };

        /**
         * @ngdoc method
         * @name stepHoverStart
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description Trigger actions called at mouse enter
         */
        vm.stepHoverStart = function() {
            var index = RecipeService.getStepIndex(vm.step);
            RecipeBulletService.stepHoverStart(index);
        };

        /**
         * @ngdoc method
         * @name stepHoverEnd
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description Trigger actions called at mouse leave
         */
        vm.stepHoverEnd = RecipeBulletService.stepHoverEnd;

        /**
         * @ngdoc method
         * @name toggleStep
         * @methodOf data-prep.recipe.controller:RecipeBulletCtrl
         * @description Enable/disable step
         */
        vm.toggleStep = function() {
            RecipeBulletService.toggleStep(vm.step);
        };
    }

    angular.module('data-prep.recipe-bullet')
        .controller('RecipeBulletCtrl', RecipeBulletCtrl);
})();