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
                        templateUrl : "/register/app/tpls/rev/regindex.html"
                    },
                    'main@index' : {
                        templateUrl : "/register/app/tpls/rev/register1.html"
                    }
                }
            })
            .state('mailverify', {
                url : '/mailverify',
                templateUrl : '/register/app/tpls/rev/register2.html'

            })
            .state('register3', {
                url : '/accountInfo',
                templateUrl : '/register/app/tpls/rev/register3.html',
                controller:'register'
            })
            .state('register4', {
                url : '/finish',
                templateUrl : '/register/app/tpls/rev/register4.html'

            })

    }])
});