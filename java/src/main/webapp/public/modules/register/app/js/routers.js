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
                        templateUrl : "/register/mail",
                        controller:'register'
                    }
                }
            })
            .state('sendMailInfo', {
                url : '/mailverify?{emailName}&{openUrl}',
                templateUrl : '/register/sendMailInfo',
                controller:'mailverify'

            })
            .state('accountInfo', {
                url : '/accountInfo?{uid}',
                templateUrl : '/register/accountInfo',
                controller:'register'
            })
            .state('finish', {
                url : '/finish',
                templateUrl : '/register/finish'

            })

    }])
});