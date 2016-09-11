/**
 * Created by huanghuanlai on 16/4/7.
 */
define(["app"], function(app) {
    "use strict";

    app.run([
            '$rootScope',
            '$state',
            '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams
            }
        ]);
    app.config(['$stateProvider','$urlRouterProvider',function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/index");
        $stateProvider.state("index", {
            url : "/index",
            views : {
                "" : {
                    templateUrl : "login/app/tpls/rev/login.html",
                    controller : "login"
                }
            }
        })
        // .state("clouddisk",{
        //     url : "/clouddisk",
        //     views : {
        //         "":{
        //             templateUrl : "admin/clouddisk/content",
        //             controller : "controller"
        //         }
        //     }
        // }).state("fileList", {
        //     url : "/fileList?{path}",
        //     views : {
        //         "" : {
        //             templateUrl : "admin/clouddisk/file/content"
        //         },
        //         "list@fileList" : {
        //             templateUrl : "admin/clouddisk/file/list/content",
        //             controller : "cddFileController"
        //         },
        //         "captcha@fileList" : {
        //             templateUrl :"admin/clouddisk/captcha/content",
        //             controller : "cddCaptchaController"
        //         }
        //     }
        // }).state("fileSearch", {
        //     url : "/fileSearch?{path}",
        //     views : {
        //         "" : {
        //             templateUrl : "admin/clouddisk/file/content",
        //             controller : "cddFileSearchController"
        //         },
        //         "list@fileSearch" : {
        //             templateUrl : "admin/clouddisk/file/list/content",
        //             controller : "cddFileController"
        //         },
        //         "captcha@fileSearch" : {
        //             templateUrl :"admin/clouddisk/captcha/content"
        //         }
        //     }
        // })
    }])
});