(function() {
    'use strict';

    /**
     * @ngdoc object
     * @name data-prep.transformation-params
     * @description This module contains the controller and directives to manage transformation parameters
     * @requires data-prep.type-validation
     * @requires data-prep.services.utils.service
     */
    angular.module('data-prep.transformation-params', [
        'talend.widget',
        'data-prep.type-validation',
        'data-prep.services.utils'
    ]);
})();