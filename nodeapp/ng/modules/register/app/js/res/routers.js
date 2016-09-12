/**
 * Created by huanghuanlai on 16/4/7.
 */
define(["app"], function(app){
    "use strict";

    app.run([
        '$rootScope',
        '$state',
        '$stateParams',
        function($rootScope, $state, $stateParams){
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams
        }
    ]);
    app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/index");
        $stateProvider
            .state('index', {
                url : '/index',
                views : {
                    '' : {
                        templateUrl : "/register/app/tpls/rev/register1.html",
                        controller:'register'
                    }
                }
            })
            .state('mailverify', {
                url : '/mailverify',
                templateUrl : '/register/app/tpls/rev/register2.html',
                controller:'mailverify'

            })
            .state('accountInfo', {
                url : '/accountInfo',
                templateUrl : '/register/app/tpls/rev/register3.html',
                controller:'register'
            })
            .state('finish', {
                url : '/finish',
                templateUrl : '/register/app/tpls/rev/register4.html'

            })

    }])
});