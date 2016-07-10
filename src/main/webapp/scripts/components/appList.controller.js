/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";

    angular
        .module('store')
        .controller('AppListController',appListCtrl);

    appListCtrl.$inject = ['$scope','$http'];

    function appListCtrl($scope,$http) {
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
})()