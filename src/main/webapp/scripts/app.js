/**
 * Created by Administrator on 2016/7/8.
 */
(function () {
    angular.module("store", [])
        .controller("appList", ['$scope', '$http', function ($scope, $http) {


            $scope.list = function () {
                console.log("hello app list");

                $http({method:"GET",url: "/app/list", param: {start: 0, limit: 10}}).then(function successCallback(response) {

                    console.log(response.data);
                    console.log(response.status);
                }, function errorCallback(response) {
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                });
            };


        }]);
})()