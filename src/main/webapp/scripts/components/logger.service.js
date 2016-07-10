/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";

    angular
        .module('store')
        .factory('logger', logger);

    function logger() {
        return {
            log: log,
            error: error,
            info: info
        };

        function log(value) {
            console.log(value);
        }

        function error(value) {
            console.error(value);
        }

        function info(value) {
            console.info(value)
        }
    }
})()