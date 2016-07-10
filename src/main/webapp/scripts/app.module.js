/**
 * Created by Administrator on 2016/7/8.
 */
(function () {
    angular.module("store", [
        'ui.router',
        'oc.lazyLoad'
    ]).config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider',
        function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
            //懒加载配置：
            $ocLazyLoadProvider.config({
                debug: true,
                events: true,
                modules: [{
                    name:"app",
                    files:['scripts/partials/app.html']
                }]
            });

            //默认路由
            $urlRouterProvider.otherwise("/app");

            $stateProvider
                .state('app', {
                    url: "/app",
                    templateUrl: 'scripts/partials/app.html',
                    controller: function ($scope) {
                        $scope.routers = [
                            {
                                name: "list",
                                state: "app.list"
                            }
                        ]
                    }
                }).state('app.list', {
                url: "/list",
                templateUrl: "scripts/partials/app.list.html",
                controller: function ($scope, $http) {
                    var vm = $scope;
                    vm.list = function () {
                        $http({
                            method: "GET",
                            url: "app/list",
                            params: {start: 0, limit: 10}
                        }).then(function successCallback(response) {
                            vm.appList = response.data.rows;
                        }, function errorCallback(response) {
                            $scope.data = response.data || "Request failed";
                            $scope.status = response.status;
                        });
                    };
                    vm.list();
                    vm.selectApp = function (app) {
                        console.log(app);
                    }

                }
            });
        }])
        .controller('RootCtrl', ['$scope', function ($scope) {

        }])
        .controller('AppCtrl', ['$scope', function ($scope) {

        }])
        .controller("appListCtrl", ['$scope', '$http', function ($scope, $http) {
            var vm = $scope;
            vm.list = function () {
                $http({
                    method: "GET",
                    url: "app/list",
                    params: {start: 0, limit: 10}
                }).then(function successCallback(response) {
                    vm.appList = response.data.rows;
                }, function errorCallback(response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                });
            };
            vm.list();
            vm.selectApp = function (app) {
                console.log(app);
            }

        }]);

})()