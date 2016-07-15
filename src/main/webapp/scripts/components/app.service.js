/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";
    angular
        .module('store')
        .factory('appService', appService);

    appService.$inject = ['$http', 'logger'];

    function appService($http, logger) {
        return {
            getAppList: getAppList,
            getAppInfo: getAppInfo
        };

        /**
         * 获取app列表。
         * @param params 分页参数。
         * @returns {*}
         */
        function getAppList(start) {
            var params = {start: start, limit: 10};
            return $http.get('app/list', {params: params})
                .then(getDataSuccess)
                .catch(getDataFailed);
        }

        /**
         * 获取App详情。
         * @param params
         * @returns {*}
         */
        function getAppInfo(params) {
            return $http.get('app/getAppInfo', {params: params})
                .then(getDataSuccess)
                .catch(getDataFailed);
        }

        function getDataSuccess(response) {
            return response.data;
        }

        function getDataFailed(error) {
            logger.error('XHR Failed for getAppList.' + error.data);
        }


    }
})()