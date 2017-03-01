(function () {
    'use strict';

    /**
     * @ngdoc directive
     * @name data-prep.recipe-bullet.directive:RecipeBullet
     * @description This directive display the recipe bullet
     * @restrict E
     * @usage
     * <recipe-bullet step='step'></recipe-bullet>
     * @param {object} step The bound step
     */
    function RecipeBullet($timeout) {
        return {
            restrict: 'E',
            scope: {
                step: '='
            },
            templateNamespace: 'svg',
            controller: 'RecipeBulletCtrl',
            controllerAs: 'recipeBulletCtrl',
            bindToController: true,
            templateUrl: 'components/recipe/bullet/recipe-bullet.html',
            link: function (scope, iElement, iAttrs, ctrl) {
                /**
                 * @ngdoc property
                 * @name recipeElement
                 * @propertyOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] The recipe element
                 * @type {object}
                 */
                var recipeElement = angular.element('.recipe').eq(0);
                /**
                 * @ngdoc property
                 * @name bulletTopCable
                 * @propertyOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] The top cable element
                 * @type {object}
                 */
                var bulletTopCable = iElement.find('path').eq(0)[0];
                /**
                 * @ngdoc property
                 * @name bulletTopCable
                 * @propertyOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] The circle element
                 * @type {object}
                 */
                var bulletCircleElement = iElement.find('circle')[0];
                /**
                 * @ngdoc property
                 * @name bulletBottomCable
                 * @propertyOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] The bottom cable element
                 * @type {object}
                 */
                var bulletBottomCable = iElement.find('path').eq(1)[0];
                /**
                 * @ngdoc property
                 * @name bulletsToBeChanged
                 * @propertyOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] The bullet element array that changes.
                 * This is saved to be able to revert the changes at mouse leave.
                 * @type {Array}
                 */
                var bulletsToBeChanged = [];

                /**
                 * @ngdoc method
                 * @name getAllBulletsCircle
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Get all bullet circle SVG element
                 * @returns {Array} An array containing all bullet circle svg element
                 */
                var getAllBulletsCircle = function() {
                    return recipeElement.find('recipe-bullet').find('circle').toArray();
                };

                /**
                 * @ngdoc method
                 * @name getBulletSvgAtIndex
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Get the bullet SVG element at a specific index
                 * @pparam {number} index The index of the wanted element
                 * @returns {object} The bullet svg element at provided index
                 */
                var getBulletSvgAtIndex = function(index) {
                    return recipeElement.find('.all-svg-cls').eq(index);
                };

                /**
                 * @ngdoc method
                 * @name setClass
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Create a closure that set a provided class to an element
                 * @pparam {string} newClass The new class string to set
                 * @returns {function} The closure
                 */
                var setClass = function(newClass) {
                    return function(circle) {
                        circle.setAttribute('class', newClass);
                    };
                };

                /**
                 * @ngdoc method
                 * @name activateAllCables
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Remove all disable class of bullet cables
                 */
                var activateAllCables = function() {
                    var allDisabledCables = recipeElement.find('.single-maillon-cables-disabled').toArray();
                    _.each(allDisabledCables, function(cable) {
                        cable.setAttribute('class', '');
                    });
                };

                /**
                 * @ngdoc method
                 * @name deActivateBottomCable
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Deactivate the bottom cable at a specific index
                 * @param {number} index The index of the element to deactivate
                 */
                function deActivateBottomCable(index) {
                    var bullet = index === ctrl.stepIndex ? iElement.find('.all-svg-cls') : getBulletSvgAtIndex(index);
                    var branch = bullet.find('>path').eq(1)[0];
                    branch.setAttribute('class', 'single-maillon-cables-disabled');
                }

                /**
                 * @ngdoc method
                 * @name updateSVGSizes
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Calculate and set the svg size infos (circle position, cables size)
                 */
                var updateSVGSizes = function() {
                    var parentHeight = iElement.parent().height();
                    var circleSize = 20;
                    var distanceBetweenBullets = 5;
                    var remainingHeight = ((parentHeight - circleSize) / 2) + distanceBetweenBullets;

                    var topPath = 'M 15 0 L 15 ' + remainingHeight + ' Z';
                    var circleCenterY = remainingHeight + 10;
                    var bottomPath = 'M 15 ' + (circleCenterY + 12) + ' L 15 ' + (circleCenterY + 10 + remainingHeight) + ' Z';

                    bulletTopCable.setAttribute('d', topPath);
                    bulletCircleElement.setAttribute('cy', circleCenterY);
                    bulletBottomCable.setAttribute('d', bottomPath);
                };

                /**
                 * @ngdoc method
                 * @name mouseEnterListener
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Element mouseenter listener.
                 * It will update the bullet styles accordingly to the step state and the other bullets
                 */
                var mouseEnterListener = function () {
                    ctrl.stepHoverStart();
                    var allBulletsSvgs = getAllBulletsCircle();

                    bulletsToBeChanged = ctrl.getBulletsToChange(allBulletsSvgs);
                    var newClass = ctrl.step.inactive ? 'maillon-circle-disabled-hovered' : 'maillon-circle-enabled-hovered';
                    _.each(bulletsToBeChanged, setClass(newClass));
                };

                /**
                 * @ngdoc method
                 * @name mouseLeaveListener
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Element mouseleave listener.
                 * It will cancel the style set during mouseenter
                 */
                var mouseLeaveListener = function () {
                    ctrl.stepHoverEnd();
                    _.each(bulletsToBeChanged, setClass(''));
                };

                /**
                 * @ngdoc method
                 * @name circleClickListener
                 * @methodOf data-prep.recipe-bullet.directive:RecipeBullet
                 * @description [PRIVATE] Circle Element click listener.
                 * It will trigger the step activation/deactivation and redraw cables
                 */
                var circleClickListener = function (event) {
                    event.stopPropagation();
                    ctrl.toggleStep();

                    activateAllCables();
                    if (!ctrl.step.inactive && !ctrl.isStartChain()) {
                        deActivateBottomCable(ctrl.stepIndex - 1);
                    } else if (!ctrl.isEndChain()) {
                        deActivateBottomCable(ctrl.stepIndex);
                    }
                };

                iElement.mouseenter(mouseEnterListener);
                iElement.mouseleave(mouseLeaveListener);
                bulletCircleElement.addEventListener('click', circleClickListener);

                $timeout(updateSVGSizes);
            }
        };
    }

    angular.module('data-prep.recipe-bullet')
        .directive('recipeBullet', RecipeBullet);
})();