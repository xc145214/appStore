/**
 * Created by Administrator on 2016/7/8.
 */
(function () {
    angular.module("store", [])
        .controller("appListCtrl", ['$scope', '$http', function ($scope, $http) {
            var vm = $scope;

            vm.list = function () {
                $http({
                    method: "GET",
                    url: "app/list",
                    params: {start: 0, limit: 10}
                }).then(function successCallback(response) {
                    vm.appList = response.data.rows;
                    console.log(vm.appList);
                }, function errorCallback(response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                });
            };


        }]);
})()