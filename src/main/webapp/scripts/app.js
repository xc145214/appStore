/**
 * Created by Administrator on 2016/7/8.
 */
(function () {
    angular.module("store", [
        'ui.router'
    ])
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/app");

            $stateProvider
                .state('app', {
                    url: "/app",
                    templateUrl: 'scripts/partials/app.html',
                    // controller: function ($scope) {
                    //     $scope.routers = [
                    //         {
                    //             name: "list",
                    //             url: "app.list"
                    //         }
                    //     ]
                    // }
                });
        })
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