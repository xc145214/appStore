/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";

    angular
        .module('store')
        .controller('AppListController', appListCtrl);

    appListCtrl.$inject = ['$scope', '$http','logger','appService'];

    function appListCtrl($scope, $http,logger,appService) {
        var vm = $scope;
        vm.appList = [];
        var params = {start: 0, limit: 10};

        activate();

        function activate() {
            return getApps().then(function() {
                logger.info('Activated appList View');
            });
        }

        function getApps() {
            return appService.getAppList(params)
                .then(function(data) {
                    vm.appList = data.rows;
                    return vm.appList;
                });
        }
        
        vm.selectApp = function (app) {
            console.log(app);
        }
    }
})()