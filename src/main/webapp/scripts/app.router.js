/**
 * Created by Administrator on 2016/7/10.
 */
(function () {
    "use strict";

    angular
        .module('store')
        .config(routerConfig);

    routerConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider'];


    function routerConfig($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
        //懒加载配置：
        $ocLazyLoadProvider.config({
            debug: true,
            events: true,
            modules: [{
                name: "root",
                files: [
                    'scripts/components/root.controller.js'
                ]
            }, {
                name: "app",
                files: [
                    'scripts/components/app.controller.js',
                    'scripts/components/app.template.html',
                    'scripts/components/logger.service.js',
                    'scripts/components/app.service.js'
                ]
            }, {
                name: "appList",
                files: [
                    'scripts/components/appList.controller.js',
                    'scripts/components/appList.template.html'
                ]
            }]
        });

        //默认路由
        $urlRouterProvider.otherwise("/app");

        $stateProvider
            .state('app', {
                url: "/app",
                templateUrl: 'scripts/components/app.template.html',
                controller: 'AppController',
                resolve: {
                    load: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('app');
                    }]
                }
            })
            .state('app.list', {
                url: "/list",
                templateUrl: "scripts/components/appList.template.html",
                controller: 'AppListController',
                resolve: {
                    load: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('appList');
                    }]
                }
            });
    }
})()