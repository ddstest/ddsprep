describe('Statistics service', function () {
    'use strict';

    var barChartNumCol = {
        'domain': 'barchartAndNumeric',
        'type': 'numeric',
        'id': '0000',
        'statistics': {
            'frequencyTable': [],
            'histogram': {
                items: [
                    {
                        'occurrences': 5,
                        'range': {
                            'min': 0,
                            'max': 10
                        }
                    },
                    {
                        'occurrences': 15,
                        'range': {
                            'min': 10,
                            'max': 20
                        }
                    }
                ]
            },
            count: 4,
            distinctCount: 5,
            duplicateCount: 6,
            empty: 7,
            invalid: 8,
            valid: 9,
            min: 10,
            max: 11,
            mean: 12,
            variance: 13,
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateCol = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'MONTH',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2015
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 2,
                                year: 2015
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 2,
                                year: 2015
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 3,
                                year: 2015
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColCENTURY = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'CENTURY',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2000
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2100
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2100
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2200
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColDECADE = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'DECADE',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2000
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2010
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2010
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2020
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColYEAR = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'YEAR',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2014
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2015
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2015
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2016
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColHAFLYEAR = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'HALF_YEAR',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2014
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 7,
                                year: 2014
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 7,
                                year: 2014
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2015
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColQUARTER = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'QUARTER',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2014
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 4,
                                year: 2014
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 4,
                                year: 2014
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 7,
                                year: 2014
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColMONTH = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'MONTH',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2015
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 2,
                                year: 2015
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 2,
                                year: 2015
                            },
                            'max': {
                                dayOfMonth: 1,
                                monthValue: 3,
                                year: 2015
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColWEEK = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'WEEK',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 4,
                                monthValue: 1,
                                year: 2016
                            },
                            'max': {
                                dayOfMonth: 11,
                                monthValue: 1,
                                year: 2016
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 11,
                                monthValue: 1,
                                year: 2016
                            },
                            'max': {
                                dayOfMonth: 18,
                                monthValue: 1,
                                year: 2016
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColDAY = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: {
                pace: 'DAY',
                items: [
                    {
                        'occurrences': 15,
                        'range': {
                            'min': {
                                dayOfMonth: 1,
                                monthValue: 1,
                                year: 2016
                            },
                            'max': {
                                dayOfMonth: 2,
                                monthValue: 1,
                                year: 2016
                            }
                        }
                    },
                    {
                        'occurrences': 5,
                        'range': {
                            'min': {
                                dayOfMonth: 2,
                                monthValue: 1,
                                year: 2016
                            },
                            'max': {
                                dayOfMonth: 3,
                                monthValue: 1,
                                year: 2016
                            }
                        }
                    }
                ]
            },
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartDateColWithoutHistogram = {
        'domain': 'barchartAndDate',
        'type': 'date',
        'id': '0000',
        'statistics': {
            frequencyTable: [],
            histogram: null,
            patternFrequencyTable: [
                {
                    pattern: 'd/M/yyyy',
                    frequency: 15
                },
                {
                    pattern: 'M/d/yyyy',
                    frequency: 5
                }
            ],
            count: 20,
            distinctCount: 14,
            duplicateCount: 6,
            empty: 0,
            invalid: 0,
            valid: 0,
            min: 'NaN',
            max: 'NaN',
            mean: 'NaN',
            variance: 'NaN',
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartStrCol = {
        id: '0010',
        'domain': 'barchartAndString',
        'type': 'string',
        'statistics': {
            'frequencyTable': [
                {
                    'data': '   toto',
                    'occurences': 202
                },
                {
                    'data': 'titi',
                    'occurences': 2
                },
                {
                    'data': 'coucou',
                    'occurences': 102
                },
                {
                    'data': 'cici',
                    'occurences': 22
                }
            ],
            'patternFrequencyTable': [
                {
                    'pattern': '   Aa9',
                    'occurences': 202
                },
                {
                    'pattern': 'yyyy-M-d',
                    'occurences': 2
                }
            ],
            textLengthSummary: {
                averageLength: 10.13248646854654,
                minimalLength: 12,
                maximalLength: 14
            },
            count: 4,
            distinctCount: 5,
            duplicateCount: 6,
            empty: 7,
            invalid: 8,
            valid: 9,
            min: 10,
            max: 11,
            mean: 12,
            variance: 13,
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartStrCol2 = {
        id: '0010',
        'domain': 'barchartAndString',
        'type': 'string',
        'statistics': {
            'frequencyTable': [
                {
                    'data': '   toto',
                    'occurences': 1
                },
                {
                    'data': 'titi',
                    'occurences': 1
                },
                {
                    'data': 'coucou',
                    'occurences': 1
                },
                {
                    'data': 'cici',
                    'occurences': 1
                }
            ],
            textLengthSummary: {
                averageLength: 10.13248646854654,
                minimalLength: 12,
                maximalLength: 14
            },
            count: 4,
            distinctCount: 5,
            duplicateCount: 6,
            empty: 7,
            invalid: 8,
            valid: 9,
            min: 10,
            max: 11,
            mean: 12,
            variance: 13,
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var mapCol = {
        'domain': 'US_STATE_CODE',
        'type': '',
        'statistics': {
            'frequencyTable': [
                {
                    'data': 'MI',
                    'occurences': 202
                },
                {
                    'data': 'WA',
                    'occurences': 2
                },
                {
                    'data': 'DE',
                    'occurences': 102
                },
                {
                    'data': 'IL',
                    'occurences': 22
                }
            ],
            textLengthSummary: {
                averageLength: 10.13248646854654,
                minimalLength: 12,
                maximalLength: 14
            },
            count: 4,
            distinctCount: 5,
            duplicateCount: 6,
            empty: 7,
            invalid: 8,
            valid: 9,
            min: 10,
            max: 11,
            mean: 12,
            variance: 13,
            quantiles: {
                lowerQuantile: 'NaN'
            }
        }
    };

    var barChartBoolCol = {
        'domain': 'barchartAndBool',
        'type': 'boolean',
        'statistics': {
            'frequencyTable': [
                {
                    'data': 'true',
                    'occurences': 2
                },
                {
                    'data': 'false',
                    'occurences': 20
                },
                {
                    'data': '',
                    'occurences': 10
                }
            ]
        }
    };

    var unknownTypeCol = {
        'domain': '',
        'type': 'unknown'
    };

    var stateMock;
    var workerWrapper;
    var workerPostedMessage;
    window.postMessage = function (message) {
        workerPostedMessage = message;
    };

    beforeEach(module('data-prep.services.statistics', function ($provide) {
        stateMock = {
            playground: {
                grid: {},
                filter: {gridFilters: []},
                data: {}
            }
        };
        $provide.constant('state', stateMock);
    }));

    beforeEach(inject(function ($q, WorkerService) {
        spyOn(WorkerService, 'create').and.callFake(function (importLibs, helperFns, mainFn) {
            workerWrapper = {
                postMessage: function (args) {
                    mainFn.apply(null, args);
                    return $q.when(workerPostedMessage);
                },
                terminate: jasmine.createSpy('terminate')
            };
            return workerWrapper;
        });
    }));

    describe('filters', function () {
        beforeEach(inject(function (FilterService) {
            spyOn(FilterService, 'addFilter').and.returnValue();
        }));

        it('should create a function that reinit range limits when the selected column is the same', inject(function (StatisticsService) {
            //given
            var originalRangeLimits = {
                min: 5,
                max: 55,
                minBrush: 5,
                maxBrush: 22,
                minFilterVal: 0,
                maxFilterVal: 22
            };
            StatisticsService.histogram = {};
            StatisticsService.rangeLimits = originalRangeLimits;
            var column = {id: '0000', statistics: {min: 5, max: 55}};
            stateMock.playground.grid.selectedColumn = column;

            var removeCallback = StatisticsService.getRangeFilterRemoveFn();

            //when
            removeCallback({colId: '0000'});

            //then
            expect(StatisticsService.rangeLimits).not.toBe(originalRangeLimits);
            expect(StatisticsService.rangeLimits).toEqual({
                min: 5,
                max: 55
            });
            expect(StatisticsService.histogram.activeLimits).toEqual([column.statistics.min, column.statistics.max]);
        }));

        it('should create a function that do nothing when the selected column is NOT the same', inject(function (StatisticsService) {
            //given
            var originalRangeLimits = {
                min: 5,
                max: 55,
                minBrush: 5,
                maxBrush: 22,
                minFilterVal: 0,
                maxFilterVal: 22
            };
            StatisticsService.histogram = {};
            StatisticsService.rangeLimits = originalRangeLimits;
            stateMock.playground.grid.selectedColumn = {id: '0000', statistics: {min: 5, max: 55}};

            var removeCallback = StatisticsService.getRangeFilterRemoveFn();

            //when
            removeCallback({colId: '0001'}); // not the same column as the filters one

            //then
            expect(StatisticsService.rangeLimits).toBe(originalRangeLimits);
        }));
    });

    describe('Process Data : Basic Charts', function () {
        describe('Map', function () {
            it('should set stateDistribution for geo chart when the column domain contains STATE_CODE', inject(function (StatisticsService) {
                //given
                expect(StatisticsService.stateDistribution).toBeFalsy();
                stateMock.playground.grid.selectedColumn = mapCol;

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.stateDistribution).toBe(mapCol);
            }));

            it('should reset non geo chart data when the column domain contains STATE_CODE', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = mapCol;
                StatisticsService.boxPlot = {};
                StatisticsService.histogram = {};

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.boxPlot).toBeFalsy();
                expect(StatisticsService.histogram).toBeFalsy();
            }));
        });

        describe('Histogram', function () {
            it('should reset non histogram data when column type is "string"', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartStrCol;
                stateMock.playground.grid.filteredOccurences = {'   toto': 3, 'titi': 2};
                StatisticsService.boxPlot = {};
                StatisticsService.stateDistribution = {};

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.boxPlot).toBeFalsy();
                expect(StatisticsService.stateDistribution).toBeFalsy();
            }));

            it('should set the frequency data with formatted value when column type is "string" with filter', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartStrCol;
                stateMock.playground.grid.filteredOccurences = {'   toto': 3, 'titi': 2};
                expect(StatisticsService.histogram).toBeFalsy();

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.histogram).toEqual({
                    data: [
                        {
                            data: '   toto',
                            occurences: 202,
                            formattedValue: '<span class="hiddenChars">   </span>toto',
                            filteredOccurrences: 3
                        },
                        {data: 'titi', occurences: 2, formattedValue: 'titi', filteredOccurrences: 2},
                        {data: 'coucou', occurences: 102, formattedValue: 'coucou', filteredOccurrences: 0},
                        {data: 'cici', occurences: 22, formattedValue: 'cici', filteredOccurrences: 0}
                    ],
                    key: 'occurrences',
                    label: 'Occurrences',
                    column: barChartStrCol
                });
            }));

            it('should set the frequency data with formatted value when column type is "string" without filter', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartStrCol2;
                stateMock.playground.grid.filteredOccurences = {'   toto': 1, 'coucou': 1, 'cici': 1, 'titi': 1};
                expect(StatisticsService.histogram).toBeFalsy();

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.histogram).toEqual({
                    data: [
                        {
                            data: '   toto',
                            occurences: 1,
                            formattedValue: '<span class="hiddenChars">   </span>toto',
                            filteredOccurrences: 1
                        },
                        {data: 'titi', occurences: 1, formattedValue: 'titi', filteredOccurrences: 1},
                        {data: 'coucou', occurences: 1, formattedValue: 'coucou', filteredOccurrences: 1},
                        {data: 'cici', occurences: 1, formattedValue: 'cici', filteredOccurrences: 1}
                    ],
                    key: 'occurrences',
                    label: 'Occurrences',
                    column: barChartStrCol2
                });
            }));

            it('should reset non histogram data when column type is "boolean"', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartBoolCol;
                stateMock.playground.grid.filteredOccurences = {'true': 3, 'false': 2};
                StatisticsService.boxPlot = {};
                StatisticsService.stateDistribution = {};

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.boxPlot).toBeFalsy();
                expect(StatisticsService.stateDistribution).toBeFalsy();
            }));

            it('should set the frequency data when column type is "boolean"', inject(function (StatisticsService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartBoolCol;
                stateMock.playground.grid.filteredOccurences = {'true': 3, 'false': 2};
                expect(StatisticsService.histogram).toBeFalsy();

                //when
                StatisticsService.processData();

                //then
                expect(StatisticsService.histogram).toEqual({
                    key: 'occurrences',
                    label: 'Occurrences',
                    column: barChartBoolCol,
                    data: barChartBoolCol.statistics.frequencyTable
                });
            }));

            describe('number', function () {
                it('should set the range data frequency when column type is "number" with filters', inject(function ($rootScope, StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartNumCol;
                    stateMock.playground.filter.gridFilters = [{}];
                    stateMock.playground.grid.filteredOccurences = {1: 2, 3: 1, 11: 6};
                    StatisticsService.statistics = {
                        common: {
                            COUNT: 4,
                            DISTINCT_COUNT: 5,
                            DUPLICATE_COUNT: 6,
                            VALID: 9,
                            EMPTY: 7,
                            INVALID: 8
                        }, specific: {MIN: 10, MAX: 11, MEAN: 12, VARIANCE: 13}
                    };
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();
                    $rootScope.$digest();

                    //then
                    expect(StatisticsService.histogram.data[0].occurrences).toBe(5); //[0, 10[
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[0].range.min,
                        max: barChartNumCol.statistics.histogram.items[0].range.max
                    });
                    expect(StatisticsService.histogram.data[1].occurrences).toBe(15); //[10, 20[
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[1].range.min,
                        max: barChartNumCol.statistics.histogram.items[1].range.max
                    });

                    expect(StatisticsService.filteredHistogram.data[0].filteredOccurrences).toBe(3); //[0, 10[
                    expect(StatisticsService.filteredHistogram.data[0].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[0].range.min,
                        max: barChartNumCol.statistics.histogram.items[0].range.max
                    });
                    expect(StatisticsService.filteredHistogram.data[1].filteredOccurrences).toBe(6); //[10, 20[
                    expect(StatisticsService.filteredHistogram.data[1].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[1].range.min,
                        max: barChartNumCol.statistics.histogram.items[1].range.max
                    });
                }));

                it('should set the range data frequency without filters', inject(function ($rootScope, StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartNumCol;
                    stateMock.playground.filter.gridFilters = [];
                    stateMock.playground.grid.filteredOccurences = null;
                    StatisticsService.statistics = {
                        common: {
                            COUNT: 4,
                            DISTINCT_COUNT: 5,
                            DUPLICATE_COUNT: 6,
                            VALID: 9,
                            EMPTY: 7,
                            INVALID: 8
                        }, specific: {MIN: 10, MAX: 11, MEAN: 12, VARIANCE: 13}
                    };
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();
                    $rootScope.$digest();

                    //then
                    expect(StatisticsService.histogram.data[0].occurrences).toBe(5); //[0, 10[
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[0].range.min,
                        max: barChartNumCol.statistics.histogram.items[0].range.max
                    });
                    expect(StatisticsService.histogram.data[1].occurrences).toBe(15); //[10, 20[
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[1].range.min,
                        max: barChartNumCol.statistics.histogram.items[1].range.max
                    });

                    expect(StatisticsService.filteredHistogram.data[0].filteredOccurrences).toBe(5); //[0, 10[
                    expect(StatisticsService.filteredHistogram.data[0].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[0].range.min,
                        max: barChartNumCol.statistics.histogram.items[0].range.max
                    });
                    expect(StatisticsService.filteredHistogram.data[1].filteredOccurrences).toBe(15); //[10, 20[
                    expect(StatisticsService.filteredHistogram.data[1].data).toEqual({
                        type: 'number',
                        min: barChartNumCol.statistics.histogram.items[1].range.min,
                        max: barChartNumCol.statistics.histogram.items[1].range.max
                    });
                }));

                it('should set histogram vertical mode to true when column type is "number"', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartNumCol;
                    StatisticsService.statistics = {
                        common: {
                            COUNT: 4,
                            DISTINCT_COUNT: 5,
                            DUPLICATE_COUNT: 6,
                            VALID: 9,
                            EMPTY: 7,
                            INVALID: 8
                        }, specific: {MIN: 10, MAX: 11, MEAN: 12, VARIANCE: 13}
                    };
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.vertical).toBe(true);
                }));
            });

            describe('date', function () {
                it('should NOT set the range histogram when there is no histogram', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColWithoutHistogram;
                    StatisticsService.statistics = {};
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram).toBeFalsy();
                }));

                it('should set the range data frequency when column type is "date" with filters', inject(function ($rootScope, StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateCol;
                    stateMock.playground.filter.gridFilters = [{}];
                    stateMock.playground.grid.filteredOccurences = {
                        '05/01/2015': 6,
                        '12/01/2015': 4,
                        'aze': 2,
                        '02/25/2015': 3
                    };
                    StatisticsService.statistics = {};
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();
                    $rootScope.$digest();

                    //then
                    expect(StatisticsService.histogram.data[0].occurrences).toBe(15); //['01/01/2015', '01/02/2015'[
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2015, 1, 1)
                    });
                    expect(StatisticsService.histogram.data[1].occurrences).toBe(5); //['01/02/2015', '01/03/2015'[
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Feb 2015',
                        min: new Date(2015, 1, 1),
                        max: new Date(2015, 2, 1)
                    });

                    expect(StatisticsService.filteredHistogram.data[0].filteredOccurrences).toBe(10); //['01/01/2015', '01/02/2015'[
                    expect(StatisticsService.filteredHistogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2015, 1, 1)
                    });
                    expect(StatisticsService.filteredHistogram.data[1].filteredOccurrences).toBe(3); //['01/02/2015', '01/03/2015'[
                    expect(StatisticsService.filteredHistogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Feb 2015',
                        min: new Date(2015, 1, 1),
                        max: new Date(2015, 2, 1)
                    });
                }));

                it('should set the range data frequency with no filters', inject(function ($rootScope, StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateCol;
                    stateMock.playground.filter.gridFilters = [];
                    stateMock.playground.grid.filteredOccurences = null;
                    StatisticsService.statistics = {};
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();
                    $rootScope.$digest();

                    //then
                    expect(StatisticsService.histogram.data[0].occurrences).toBe(15); //['01/01/2015', '01/02/2015'[
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2015, 1, 1)
                    });
                    expect(StatisticsService.histogram.data[1].occurrences).toBe(5); //['01/02/2015', '01/03/2015'[
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Feb 2015',
                        min: new Date(2015, 1, 1),
                        max: new Date(2015, 2, 1)
                    });

                    expect(StatisticsService.filteredHistogram.data[0].filteredOccurrences).toBe(15); //['01/01/2015', '01/02/2015'[
                    expect(StatisticsService.filteredHistogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2015, 1, 1)
                    });
                    expect(StatisticsService.filteredHistogram.data[1].filteredOccurrences).toBe(5); //['01/02/2015', '01/03/2015'[
                    expect(StatisticsService.filteredHistogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Feb 2015',
                        min: new Date(2015, 1, 1),
                        max: new Date(2015, 2, 1)
                    });
                }));

                it('should set histogram vertical mode to true when column type is "date"', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateCol;
                    stateMock.playground.grid.filteredOccurences = {
                        '05/01/2015': 10,
                        '12/01/2015': 5,
                        'aze': 3,
                        '02/25/2015': 5
                    };
                    StatisticsService.statistics = {};
                    expect(StatisticsService.histogram).toBeFalsy();

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.vertical).toBe(true);
                }));

                it('should adapt date range label to century', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColCENTURY;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: '[2000, 2100[',
                        min: new Date(2000, 0, 1),
                        max: new Date(2100, 0, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: '[2100, 2200[',
                        min: new Date(2100, 0, 1),
                        max: new Date(2200, 0, 1)
                    });
                }));

                it('should adapt date range label to decade', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColDECADE;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: '[2000, 2010[',
                        min: new Date(2000, 0, 1),
                        max: new Date(2010, 0, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: '[2010, 2020[',
                        min: new Date(2010, 0, 1),
                        max: new Date(2020, 0, 1)
                    });
                }));

                it('should adapt date range label to year', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColYEAR;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: '2014',
                        min: new Date(2014, 0, 1),
                        max: new Date(2015, 0, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: '2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2016, 0, 1)
                    });
                }));

                it('should adapt date range label to half year', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColHAFLYEAR;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'H1 2014',
                        min: new Date(2014, 0, 1),
                        max: new Date(2014, 6, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'H2 2014',
                        min: new Date(2014, 6, 1),
                        max: new Date(2015, 0, 1)
                    });
                }));

                it('should adapt date range label to quarter', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColQUARTER;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Q1 2014',
                        min: new Date(2014, 0, 1),
                        max: new Date(2014, 3, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Q2 2014',
                        min: new Date(2014, 3, 1),
                        max: new Date(2014, 6, 1)
                    });
                }));

                it('should adapt date range label to month', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColMONTH;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 2015',
                        min: new Date(2015, 0, 1),
                        max: new Date(2015, 1, 1)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Feb 2015',
                        min: new Date(2015, 1, 1),
                        max: new Date(2015, 2, 1)
                    });
                }));

                it('should adapt date range label to week', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColWEEK;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'W01 2016',
                        min: new Date(2016, 0, 4),
                        max: new Date(2016, 0, 11)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'W02 2016',
                        min: new Date(2016, 0, 11),
                        max: new Date(2016, 0, 18)
                    });
                }));

                it('should adapt date range label to day', inject(function (StatisticsService) {
                    //given
                    stateMock.playground.grid.selectedColumn = barChartDateColDAY;

                    //when
                    StatisticsService.processData();

                    //then
                    expect(StatisticsService.histogram.data[0].data).toEqual({
                        type: 'date',
                        label: 'Jan 1, 2016',
                        min: new Date(2016, 0, 1),
                        max: new Date(2016, 0, 2)
                    });
                    expect(StatisticsService.histogram.data[1].data).toEqual({
                        type: 'date',
                        label: 'Jan 2, 2016',
                        min: new Date(2016, 0, 2),
                        max: new Date(2016, 0, 3)
                    });
                }));
            });
        });

        it('should reset charts data when column type is not supported', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = unknownTypeCol;
            StatisticsService.boxPlot = {};
            StatisticsService.histogram = {};
            StatisticsService.stateDistribution = {};

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.histogram).toBeFalsy();
            expect(StatisticsService.boxPlot).toBeFalsy();
            expect(StatisticsService.stateDistribution).toBeFalsy();
        }));
    });

    describe('Process Data : The range slider', function () {
        beforeEach(inject(function (StatisticsService) {
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'integer',
                domain: 'city name',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    min: 0,
                    max: 11,
                    mean: 12,
                    variance: 13,
                    quantiles: {
                        lowerQuantile: 'NaN'
                    },
                    histogram: {items: []}
                }
            };

            StatisticsService.statistics = {
                common: {
                    COUNT: 4,
                    DISTINCT_COUNT: 5,
                    DUPLICATE_COUNT: 6,
                    VALID: 9,
                    EMPTY: 7,
                    INVALID: 8
                },
                specific: {
                    MIN: 0,
                    MAX: 11,
                    MEAN: 12,
                    VARIANCE: 13
                }
            };
        }));

        it('should set range and brush limits to the min and max of the column', inject(function (StatisticsService) {
            //given
            stateMock.playground.filter.gridFilters = [];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11
            });
            expect(StatisticsService.histogram.activeLimits).toBe(null);
        }));

        it('should update the brush limits to the existing range filter values', inject(function (StatisticsService) {
            //given
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [5, 10]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 5,
                maxBrush: 10,
                minFilterVal: 5,
                maxFilterVal: 10
            });
            expect(StatisticsService.histogram.activeLimits).toEqual([5, 10]);
        }));

        it('should update the brush limits to the minimum', inject(function (StatisticsService) {
            //given : -5 < 0(minimum)
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [-15, -10]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 0,
                maxBrush: 0,
                minFilterVal: -15,
                maxFilterVal: -10
            });
        }));

        it('should update the brush limits to the [minimum, maximum] ', inject(function (StatisticsService) {
            //given : -5 < 0(minimum)
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [-15, 20]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 0,
                maxBrush: 11,
                minFilterVal: -15,
                maxFilterVal: 20
            });
        }));

        it('should update the brush limits to the maximum', inject(function (StatisticsService) {
            //given
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [25, 30]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 11,
                maxBrush: 11,
                minFilterVal: 25,
                maxFilterVal: 30
            });
        }));

        it('should update the brush limits to [minBrush, maximum]', inject(function (StatisticsService) {
            //given
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [5, 30]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 5,
                maxBrush: 11,
                minFilterVal: 5,
                maxFilterVal: 30
            });
        }));

        it('should update the brush limits to [minimum, maxBrush]', inject(function (StatisticsService) {
            //given
            stateMock.playground.filter.gridFilters = [{
                colId: '0001',
                type: 'inside_range',
                args: {interval: [-25, 10]}
            }];

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.rangeLimits).toEqual({
                min: 0,
                max: 11,
                minBrush: 0,
                maxBrush: 10,
                minFilterVal: -25,
                maxFilterVal: 10
            });
        }));
    });

    describe('Process Data : The boxplot data', function () {
        it('should reset boxplotData when quantile values are NaN', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'integer',
                domain: 'city name',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    min: 10,
                    max: 11,
                    mean: 12,
                    variance: 13,
                    quantiles: {
                        lowerQuantile: 'NaN'
                    }
                }
            };

            StatisticsService.statistics = {
                common: {
                    COUNT: 4,
                    DISTINCT_COUNT: 5,
                    DUPLICATE_COUNT: 6,
                    VALID: 9,
                    EMPTY: 7,
                    INVALID: 8
                },
                specific: {
                    MIN: 10,
                    MAX: 11,
                    MEAN: 12,
                    VARIANCE: 13
                }
            };

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.boxPlot).toBeFalsy();
        }));

        it('should set boxplotData statistics with quantile', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'integer',
                domain: 'code postal',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    min: 10,
                    max: 11,
                    mean: 12,
                    variance: 13,
                    quantiles: {
                        median: 14,
                        lowerQuantile: 15,
                        upperQuantile: 16
                    }
                }
            };

            StatisticsService.statistics = {
                common: {
                    COUNT: 4,
                    DISTINCT_COUNT: 5,
                    DUPLICATE_COUNT: 6,
                    VALID: 9,
                    EMPTY: 7,
                    INVALID: 8
                },
                specific: {MIN: 10, MAX: 11, MEAN: 12, VARIANCE: 13, MEDIAN: 14, LOWER_QUANTILE: 15, UPPER_QUANTILE: 16}
            };

            //when
            StatisticsService.processData();

            //then
            expect(StatisticsService.boxPlot).toEqual({
                min: 10,
                max: 11,
                q1: 15,
                q2: 16,
                median: 14,
                mean: 12,
                variance: 13
            });
        }));
    });

    describe('Process Aggregations : Aggregation charts', function () {
        var currentColumn = {'id': '0001', 'name': 'city'}; // the selected column
        var datasetId = '13654634856752';                   // the current data id
        var preparationId = '2132548345365';                // the current preparation id
        var stepId = '9878645468';                          // the currently viewed step id
        var column = {'id': '0002', 'name': 'state'};       // the column where to perform the aggregation
        var aggregation = 'MAX';                            // the aggregation operation

        var getAggregationsResponse = [                     // the REST aggregation GET result
            {'data': 'Lansing', 'max': 15},
            {'data': 'Helena', 'max': 5},
            {'data': 'Baton Rouge', 'max': 64},
            {'data': 'Annapolis', 'max': 4},
            {'data': 'Pierre', 'max': 104}
        ];

        beforeEach(inject(function (StatisticsService, RecipeService, StorageService) {
            stateMock.playground.grid.selectedColumn = currentColumn;
            stateMock.playground.dataset = {id: datasetId};
            stateMock.playground.preparation = {id: preparationId};
            spyOn(RecipeService, 'getLastActiveStep').and.returnValue({transformation: {stepId: stepId}});
            spyOn(StorageService, 'setAggregation').and.returnValue();
            spyOn(StorageService, 'removeAggregation').and.returnValue();
        }));

        describe('with NO provided aggregation', function () {
            it('should update histogram data with classical occurrence histogram with filter', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartStrCol;
                stateMock.playground.grid.filteredOccurences = {'   toto': 3, 'titi': 2};
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when());

                //when
                StatisticsService.processAggregation();

                //then
                expect(StatisticsRestService.getAggregations).not.toHaveBeenCalled();
                expect(StatisticsService.histogram).toEqual({
                    data: [
                        {
                            data: '   toto',
                            occurences: 202,
                            formattedValue: '<span class="hiddenChars">   </span>toto',
                            filteredOccurrences: 3
                        },
                        {data: 'titi', occurences: 2, formattedValue: 'titi', filteredOccurrences: 2},
                        {data: 'coucou', occurences: 102, formattedValue: 'coucou', filteredOccurrences: 0},
                        {data: 'cici', occurences: 22, formattedValue: 'cici', filteredOccurrences: 0}
                    ],
                    key: 'occurrences',
                    label: 'Occurrences',
                    column: barChartStrCol
                });
            }));

            it('should remove saved aggregation on current column/preparation/dataset from storage', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService, StorageService) {
                //given
                stateMock.playground.grid.selectedColumn = barChartStrCol;
                stateMock.playground.grid.filteredOccurences = {'   toto': 3, 'titi': 2};
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when());
                expect(StorageService.removeAggregation).not.toHaveBeenCalled();

                //when
                StatisticsService.processAggregation();

                //then
                expect(StorageService.removeAggregation).toHaveBeenCalledWith(datasetId, preparationId, barChartStrCol.id);
            }));
        });

        describe('with provided aggregation', function () {
            it('should update histogram data from REST call result and aggregation infos on dataset', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService) {
                //given
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when(getAggregationsResponse));
                stateMock.playground.preparation = null;

                //when
                StatisticsService.processAggregation(column, aggregation);
                $rootScope.$digest();

                //then
                expect(StatisticsRestService.getAggregations).toHaveBeenCalledWith({
                    datasetId: '13654634856752',
                    preparationId: null,
                    stepId: null,
                    operations: [{operator: 'MAX', columnId: '0002'}],
                    groupBy: ['0001']
                });
                expect(StatisticsService.histogram).toEqual({
                    data: [
                        {'data': 'Lansing', 'max': 15, 'formattedValue': 'Lansing'},
                        {'data': 'Helena', 'max': 5, 'formattedValue': 'Helena'},
                        {'data': 'Baton Rouge', 'max': 64, 'formattedValue': 'Baton Rouge'},
                        {'data': 'Annapolis', 'max': 4, 'formattedValue': 'Annapolis'},
                        {'data': 'Pierre', 'max': 104, 'formattedValue': 'Pierre'}
                    ],
                    key: 'MAX',
                    label: 'MAX',
                    column: stateMock.playground.grid.selectedColumn,
                    aggregationColumn: column,
                    aggregation: aggregation
                });
            }));

            it('should update histogram data from aggregation infos on preparation', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService) {
                //given
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when(getAggregationsResponse));

                //when
                StatisticsService.processAggregation(column, aggregation);
                $rootScope.$digest();

                //then
                expect(StatisticsRestService.getAggregations).toHaveBeenCalledWith({
                    datasetId: null,
                    preparationId: '2132548345365',
                    stepId: '9878645468',
                    operations: [{operator: 'MAX', columnId: '0002'}],
                    groupBy: ['0001']
                });
                expect(StatisticsService.histogram).toEqual({
                    data: [
                        {'data': 'Lansing', 'max': 15, 'formattedValue': 'Lansing'},
                        {'data': 'Helena', 'max': 5, 'formattedValue': 'Helena'},
                        {'data': 'Baton Rouge', 'max': 64, 'formattedValue': 'Baton Rouge'},
                        {'data': 'Annapolis', 'max': 4, 'formattedValue': 'Annapolis'},
                        {'data': 'Pierre', 'max': 104, 'formattedValue': 'Pierre'}
                    ],
                    key: 'MAX',
                    label: 'MAX',
                    column: stateMock.playground.grid.selectedColumn,
                    aggregationColumn: column,
                    aggregation: aggregation
                });
            }));

            it('should save column aggregation in storage', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService, StorageService) {
                //given
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when(getAggregationsResponse));
                expect(StorageService.setAggregation).not.toHaveBeenCalled();

                //when
                StatisticsService.processAggregation(column, aggregation);
                $rootScope.$digest();

                //then
                expect(StorageService.setAggregation).toHaveBeenCalledWith('13654634856752', '2132548345365', '0001', {
                    aggregation: 'MAX',
                    aggregationColumnId: '0002'
                });
            }));

            it('should reset histogram when REST WS call fails', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService) {
                //given
                spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.reject());

                //when
                StatisticsService.processAggregation(datasetId, preparationId, stepId, column, aggregation);
                $rootScope.$digest();

                //then
                expect(StatisticsService.histogram).toBeFalsy();
            }));
        });
    });

    describe('Update Statistics : The statistics values', function () {
        it('should init number statistics whithout quantile', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'integer',
                domain: 'city name',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    min: 10,
                    max: 11,
                    mean: 12,
                    variance: 13,
                    quantiles: {
                        lowerQuantile: 'NaN'
                    }
                }
            };

            //when
            StatisticsService.updateStatistics();

            //then
            expect(StatisticsService.statistics).toBeTruthy();
            expect(StatisticsService.statistics.common).toEqual({
                COUNT: 4,
                DISTINCT_COUNT: 5,
                DUPLICATE_COUNT: 6,
                VALID: 9,
                EMPTY: 7,
                INVALID: 8
            });
            expect(StatisticsService.statistics.specific).toEqual({
                MIN: 10,
                MAX: 11,
                MEAN: 12,
                VARIANCE: 13
            });
        }));

        it('should init number statistics with quantile', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'integer',
                domain: 'code postal',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    min: 10,
                    max: 11,
                    mean: 12,
                    variance: 13,
                    quantiles: {
                        median: 14,
                        lowerQuantile: 15,
                        upperQuantile: 16
                    }
                }
            };

            //when
            StatisticsService.updateStatistics();

            //then
            expect(StatisticsService.statistics).toBeTruthy();
            expect(StatisticsService.statistics.common).toEqual({
                COUNT: 4,
                DISTINCT_COUNT: 5,
                DUPLICATE_COUNT: 6,
                VALID: 9,
                EMPTY: 7,
                INVALID: 8
            });
            expect(StatisticsService.statistics.specific).toEqual({
                MIN: 10,
                MAX: 11,
                MEAN: 12,
                VARIANCE: 13,
                MEDIAN: 14,
                LOWER_QUANTILE: 15,
                UPPER_QUANTILE: 16
            });
        }));

        it('should init text statistics', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                type: 'string',
                domain: 'text',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9,
                    textLengthSummary: {
                        averageLength: 10.13248646854654,
                        minimalLength: 12,
                        maximalLength: 14
                    }
                }
            };
            expect(StatisticsService.statistics).toBeFalsy();

            //when
            StatisticsService.updateStatistics();

            //then
            expect(StatisticsService.statistics).toBeTruthy();
            expect(StatisticsService.statistics.common).toEqual({
                COUNT: 4,
                DISTINCT_COUNT: 5,
                DUPLICATE_COUNT: 6,
                VALID: 9,
                EMPTY: 7,
                INVALID: 8
            });
            expect(StatisticsService.statistics.specific).toEqual({
                AVG_LENGTH: 10.13,
                MIN_LENGTH: 12,
                MAX_LENGTH: 14
            });
        }));

        it('should init common statistics when the column type is not "number" or "text"', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                id: '0001',
                type: 'boolean',
                domain: 'US_STATE_CODE',
                statistics: {
                    count: 4,
                    distinctCount: 5,
                    duplicateCount: 6,
                    empty: 7,
                    invalid: 8,
                    valid: 9
                }
            };

            //when
            StatisticsService.updateStatistics();

            //then
            expect(StatisticsService.statistics).toBeTruthy();
            expect(StatisticsService.statistics.common).toEqual({
                COUNT: 4,
                DISTINCT_COUNT: 5,
                DUPLICATE_COUNT: 6,
                VALID: 9,
                EMPTY: 7,
                INVALID: 8
            });
            expect(StatisticsService.statistics.specific).toEqual({});
        }));
    });

    describe('Update Statistics : The statistics values', function () {

        beforeEach(inject(function () {
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                'name': 'city',
                'type': 'date',
                'domain': 'date',
                'statistics': {
                    'patternFrequencyTable': []
                }
            };
            stateMock.playground.grid.filteredRecords = [{'0001': '10-12-2015'}, {'0001': '2015-12-02'}, {'0001': 'To,/'}, {'0001': '2015-12-02'}, {'0001': '10/12-20'}];
        }));

        it('should update empty pattern statistics', inject(function ($rootScope, StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn.statistics.patternFrequencyTable = [
                {
                    'pattern': '',
                    'occurrences': 1
                }
            ];
            stateMock.playground.filter.gridFilters = [{}];
            stateMock.playground.grid.filteredRecords = [{
                '0001': 'toto'
            }];

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StatisticsService.patterns).toEqual([
                {
                    'pattern': '',
                    'occurrences': 1,
                    'filteredOccurrences': 0
                }
            ]);
        }));

        it('should update date pattern statistics', inject(function ($rootScope, StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn.statistics.patternFrequencyTable = [
                {
                    'pattern': 'd-M-yyyy',
                    'occurrences': 1
                },
                {
                    'pattern': 'yyyy-M-d',
                    'occurrences': 2
                }
            ];
            stateMock.playground.filter.gridFilters = [{}];
            stateMock.playground.grid.filteredRecords = [
                {'0001': '18-01-2015'},
                {'0001': '2016-01-01'}
            ];

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StatisticsService.patterns).toEqual([
                {
                    'pattern': 'd-M-yyyy',
                    'occurrences': 1,
                    'filteredOccurrences': 1
                },
                {
                    'pattern': 'yyyy-M-d',
                    'occurrences': 2,
                    'filteredOccurrences': 1
                }
            ]);
        }));

        it('should update non date pattern statistics', inject(function ($rootScope, StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn.statistics.patternFrequencyTable = [
                {
                    'pattern': 'Aa,/',
                    'occurrences': 3
                },
                {
                    'pattern': '99/99-99',
                    'occurrences': 4
                }
            ];
            stateMock.playground.filter.gridFilters = [{}];
            stateMock.playground.grid.filteredRecords = [
                {'0001': 'Bg,/'},
                {'0001': '26/42-98'}
            ];

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StatisticsService.patterns).toEqual([
                {
                    'pattern': 'Aa,/',
                    'occurrences': 3,
                    'filteredOccurrences': 1
                },
                {
                    'pattern': '99/99-99',
                    'occurrences': 4,
                    'filteredOccurrences': 1
                }
            ]);
        }));

        it('should update pattern statistics without filter', inject(function ($rootScope, StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn.statistics.patternFrequencyTable = [
                {
                    'pattern': 'Aa,/',
                    'occurrences': 3
                },
                {
                    'pattern': '99/99-99',
                    'occurrences': 4
                }
            ];
            stateMock.playground.filter.gridFilters = [];
            stateMock.playground.grid.filteredRecords = [];

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StatisticsService.patterns).toEqual([
                {
                    'pattern': 'Aa,/',
                    'occurrences': 3,
                    'filteredOccurrences': 3
                },
                {
                    'pattern': '99/99-99',
                    'occurrences': 4,
                    'filteredOccurrences': 4
                }
            ]);
        }));
    });

    describe('Update Statistics : Statistics routing (basic / aggregations)', function () {
        var currentColumn = {                               // the selected column
            'id': '0001',
            'name': 'city',
            'domain': '',
            'type': '',
            'statistics': {'patternFrequencyTable': []}
        };
        var datasetId = '13654634856752';                   // the current data id
        var preparationId = '2132548345365';                // the current preparation id
        var stepId = '9878645468';                          // the currently viewed step id
        var column = {'id': '0002', 'name': 'state'};       // the column where to perform the aggregation
        var aggregation = 'MAX';                            // the aggregation operation

        var getAggregationsResponse = [                     // the REST aggregation GET result
            {'data': 'Lansing', 'max': 15},
            {'data': 'Helena', 'max': 5},
            {'data': 'Baton Rouge', 'max': 64},
            {'data': 'Annapolis', 'max': 4},
            {'data': 'Pierre', 'max': 104}
        ];

        beforeEach(inject(function (StatisticsService, RecipeService, StorageService) {
            stateMock.playground.grid.selectedColumn = currentColumn;
            stateMock.playground.dataset = {id: datasetId};
            stateMock.playground.preparation = {id: preparationId};
            spyOn(RecipeService, 'getLastActiveStep').and.returnValue({transformation: {stepId: stepId}});
            spyOn(StorageService, 'setAggregation').and.returnValue();
            spyOn(StorageService, 'removeAggregation').and.returnValue();
        }));

        it('should update histogram data with classical occurrence when there is no saved aggregation on the current preparation/dataset/column with filter', inject(function ($rootScope, StatisticsService, StorageService) {
            //given
            stateMock.playground.grid.selectedColumn = barChartStrCol;
            stateMock.playground.grid.filteredOccurences = {'   toto': 3, 'titi': 2};
            spyOn(StorageService, 'getAggregation').and.returnValue();

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StorageService.getAggregation).toHaveBeenCalledWith(datasetId, preparationId, barChartStrCol.id);
            expect(StatisticsService.histogram).toEqual({
                data: [
                    {
                        data: '   toto',
                        occurences: 202,
                        formattedValue: '<span class="hiddenChars">   </span>toto',
                        filteredOccurrences: 3
                    },
                    {data: 'titi', occurences: 2, formattedValue: 'titi', filteredOccurrences: 2},
                    {data: 'coucou', occurences: 102, formattedValue: 'coucou', filteredOccurrences: 0},
                    {data: 'cici', occurences: 22, formattedValue: 'cici', filteredOccurrences: 0}
                ],
                key: 'occurrences',
                label: 'Occurrences',
                column: barChartStrCol
            });
        }));

        it('should update histogram data from saved aggregation configuration', inject(function ($q, $rootScope, StatisticsService, StatisticsRestService, DatagridService, StorageService) {
            //given
            spyOn(StatisticsRestService, 'getAggregations').and.returnValue($q.when(getAggregationsResponse));
            var savedAggregation = {
                aggregationColumnId: '0002',
                aggregation: 'MAX'
            };
            spyOn(StorageService, 'getAggregation').and.returnValue(savedAggregation);
            var datagridNumericColumns = [
                {id: '0002', name: 'state'},
                {id: '0003', name: 'name'}
            ];
            spyOn(DatagridService, 'getNumericColumns').and.returnValue(datagridNumericColumns);

            //when
            StatisticsService.updateStatistics();
            $rootScope.$digest();

            //then
            expect(StatisticsRestService.getAggregations).toHaveBeenCalledWith({
                datasetId: null,
                preparationId: '2132548345365',
                stepId: '9878645468',
                operations: [{operator: 'MAX', columnId: '0002'}],
                groupBy: ['0001']
            });
            expect(StatisticsService.histogram).toEqual({
                data: [
                    {'data': 'Lansing', 'max': 15, 'formattedValue': 'Lansing'},
                    {'data': 'Helena', 'max': 5, 'formattedValue': 'Helena'},
                    {'data': 'Baton Rouge', 'max': 64, 'formattedValue': 'Baton Rouge'},
                    {'data': 'Annapolis', 'max': 4, 'formattedValue': 'Annapolis'},
                    {'data': 'Pierre', 'max': 104, 'formattedValue': 'Pierre'}
                ],
                key: 'MAX',
                label: 'MAX',
                column: stateMock.playground.grid.selectedColumn,
                aggregationColumn: column,
                aggregation: aggregation
            });
        }));

        it('should reset non histogram charts', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = barChartNumCol;
            StatisticsService.stateDistribution = {};

            //when
            StatisticsService.updateStatistics();

            //then
            expect(StatisticsService.boxPlot).toBeFalsy();
            expect(StatisticsService.stateDistribution).toBeFalsy();
        }));
    });

    describe('utils', function () {
        beforeEach(inject(function (StatisticsRestService) {
            spyOn(StatisticsRestService, 'resetCache').and.returnValue();
        }));

        it('should reset all charts, statistics, cache', inject(function (StatisticsRestService, StatisticsService) {
            //given
            StatisticsService.boxPlot = {};
            StatisticsService.histogram = {};
            StatisticsService.stateDistribution = {};
            StatisticsService.statistics = {};

            //when
            StatisticsService.reset(true, true, true);

            //then
            expect(StatisticsService.boxPlot).toBeFalsy();
            expect(StatisticsService.histogram).toBeFalsy();
            expect(StatisticsService.stateDistribution).toBeFalsy();
            expect(StatisticsService.statistics).toBeFalsy();
            expect(StatisticsRestService.resetCache).toHaveBeenCalled();
        }));

        it('should reset date filtered occurrence worker', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = barChartDateCol;
            stateMock.playground.filter.gridFilters = [];
            stateMock.playground.grid.filteredOccurences = null;
            StatisticsService.statistics = {};
            expect(StatisticsService.histogram).toBeFalsy();

            StatisticsService.processData(); //create the worker
            expect(workerWrapper.terminate).not.toHaveBeenCalled();

            //when
            StatisticsService.reset();

            //then
            expect(workerWrapper.terminate).toHaveBeenCalled();
        }));

        it('should reset date pattern filtered occurrence worker', inject(function (StatisticsService) {
            //given
            stateMock.playground.grid.selectedColumn = {
                'id': '0001',
                'name': 'city',
                'type': 'date',
                'domain': 'date',
                statistics: {
                    patternFrequencyTable: [
                        {
                            'pattern': 'd-M-yyyy',
                            'occurrences': 1
                        },
                        {
                            'pattern': 'yyyy-M-d',
                            'occurrences': 2
                        }
                    ]
                }
            };
            stateMock.playground.filter.gridFilters = [{}];
            stateMock.playground.grid.filteredRecords = [
                {'0001': '18-01-2015'},
                {'0001': '2016-01-01'}
            ];

            StatisticsService.updateStatistics(); //create the worker
            expect(workerWrapper.terminate).not.toHaveBeenCalled();

            //when
            StatisticsService.reset();

            //then
            expect(workerWrapper.terminate).toHaveBeenCalled();
        }));

        it('should NOT reset charts', inject(function (StatisticsService) {
            //given
            StatisticsService.boxPlot = {};
            StatisticsService.histogram = {};
            StatisticsService.stateDistribution = {};

            //when
            StatisticsService.reset(false, true, true);

            //then
            expect(StatisticsService.boxPlot).toBeTruthy();
            expect(StatisticsService.histogram).toBeTruthy();
            expect(StatisticsService.stateDistribution).toBeTruthy();
        }));

        it('should NOT reset statistics', inject(function (StatisticsService) {
            //given
            StatisticsService.statistics = {};

            //when
            StatisticsService.reset(true, false, true);

            //then
            expect(StatisticsService.statistics).toBeTruthy();
        }));

        it('should NOT reset cache', inject(function (StatisticsRestService, StatisticsService) {
            //when
            StatisticsService.reset(true, true, false);

            //then
            expect(StatisticsRestService.resetCache).not.toHaveBeenCalled();
        }));

        it('should get numeric columns (as aggregation columns) from lookup-datagrid service', inject(function (StatisticsService, DatagridService) {
            //given
            var selectedcolumn = {id: '0001'};
            stateMock.playground.grid.selectedColumn = selectedcolumn;

            var datagridNumericColumns = [
                {id: '0002'},
                {id: '0003'}
            ];
            spyOn(DatagridService, 'getNumericColumns').and.returnValue(datagridNumericColumns);

            //when
            var aggregationColumns = StatisticsService.getAggregationColumns();

            //then
            expect(aggregationColumns).toBe(datagridNumericColumns);
            expect(DatagridService.getNumericColumns).toHaveBeenCalledWith(selectedcolumn);
        }));
    });

});
