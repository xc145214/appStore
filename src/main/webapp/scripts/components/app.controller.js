/**
 * Created by Administrator on 2016/7/10.
 */

(function () {
    "use strict";

    angular
        .module('store')
        .controller('AppController', appCtrl);

    //注入
    appCtrl.$inject = ['$scope'];

    function appCtrl($scope) {
        $scope.routers = [
            {
                name: "list",
                state: "app.list"
            }
        ];
    }
})()


