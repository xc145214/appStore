/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";

    angular
        .module('store')
        .controller('AppListController', appListCtrl);

    appListCtrl.$inject = ['$scope',  'logger', 'appService'];

    function appListCtrl($scope,  logger, appService) {
        var vm = $scope;
        vm.appList = [];
        $scope.bigCurrentPage = 1;
        vm.maxSize = 5;

        activate();

        //reload data
        vm.pageChanged = function () {
            activate();
        };


        function activate() {
            return getApps().then(function () {
                logger.info('Activated appList View');
            });
        }

        function getApps() {
            return appService.getAppList(vm.bigCurrentPage - 1)
                .then(function (data) {
                    vm.appList = data.rows;
                    vm.bigTotalItems = data.total;
                    return vm.appList;
                });
        }

    }
})();