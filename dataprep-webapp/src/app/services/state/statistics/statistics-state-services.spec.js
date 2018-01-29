/*  ============================================================================

  Copyright (C) 2006-2018 Talend Inc. - www.talend.com

  This source code is available under agreement available at
  https://github.com/Talend/data-prep/blob/master/LICENSE

  You should have received a copy of the agreement
  along with this program; if not, write to Talend SA
  9 rue Pages 92150 Suresnes, France

  ============================================================================*/

describe('Statistics state service', function () {
    'use strict';

    beforeEach(angular.mock.module('data-prep.services.state'));

    it('should set histogram', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.histogram).toBeFalsy();
        var histogram = { data: [] };

        //when
        StatisticsStateService.setHistogram(histogram);

        //then
        expect(statisticsState.histogram).toBe(histogram);
    }));

    it('should set filtered histogram', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.filteredHistogram).toBeFalsy();
        var filteredHistogram = { data: [] };

        //when
        StatisticsStateService.setFilteredHistogram(filteredHistogram);

        //then
        expect(statisticsState.filteredHistogram).toBe(filteredHistogram);
    }));

    it('should set active limits', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.activeLimits).toBeFalsy();
        var activeLimits = [15, 25];

        //when
        StatisticsStateService.setHistogramActiveLimits(activeLimits);

        //then
        expect(statisticsState.activeLimits).toBe(activeLimits);
    }));

    it('should set patterns', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.patterns).toBeFalsy();
        var patterns = { data: [] };

        //when
        StatisticsStateService.setPatterns(patterns);

        //then
        expect(statisticsState.patterns).toBe(patterns);
    }));

    it('should set filtered patterns', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.filteredPatterns).toBeFalsy();
        var filteredPatterns = { data: [] };

        //when
        StatisticsStateService.setFilteredPatterns(filteredPatterns);

        //then
        expect(statisticsState.filteredPatterns).toBe(filteredPatterns);
    }));

    it('should set loading flag', inject(function (statisticsState, StatisticsStateService) {
        //given
        expect(statisticsState.loading).toBeFalsy();

        //when
        StatisticsStateService.setLoading(true);

        //then
        expect(statisticsState.loading).toBe(true);
    }));

    it('should reset all statistics', inject(function (statisticsState, StatisticsStateService) {
        //given
        statisticsState.histogram = {};
        statisticsState.filteredHistogram = {};
        statisticsState.patterns = {};
        statisticsState.filteredPatterns = {};
        statisticsState.activeLimits = {};
        statisticsState.boxPlot = {};
        statisticsState.rangeLimits = {};
        statisticsState.details = {};
        statisticsState.loading = true;

        //when
        StatisticsStateService.reset();

        //then
        expect(statisticsState.histogram).toBe(null);
        expect(statisticsState.filteredHistogram).toBe(null);
        expect(statisticsState.patterns).toBe(null);
        expect(statisticsState.filteredPatterns).toBe(null);
        expect(statisticsState.activeLimits).toBe(null);
        expect(statisticsState.boxPlot).toBe(null);
        expect(statisticsState.rangeLimits).toBe(null);
        expect(statisticsState.details).toBe(null);
        expect(statisticsState.loading).toBe(false);
    }));
});
