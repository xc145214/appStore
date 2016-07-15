/**
 * Created by xiachuan on 2016/7/15.
 */
(function () {
    "use strict";

    angular
        .module("store")
        .controller("AppDetailController", appDetailCtrl);

    appDetailCtrl.$inject = ['$scope', '$stateParams', 'appService', '$log'];

    function appDetailCtrl($scope, $stateParams, appService, $log) {
        var vm = $scope;
        vm.disabled = true;

        activate();


        function activate() {
            return getAppInfo().then(function () {
                $log.info('Activated appDetail View');
            });
        }

        function getAppInfo() {
            return appService.getAppInfo($stateParams)
                .then(function (data) {
                    vm.app = data;
                    console.log(vm.app);
                    return vm.app;
                });
        }


    }
})();