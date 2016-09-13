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
                        templateUrl : "/register/app/tpls/rev/mail.html",
                        controller:'register'
                    }
                }
            })
            .state('sendMailInfo', {
                url : '/mailverify?{emailName}&{openUrl}',
                templateUrl : '/register/app/tpls/rev/sendMailInfo.html',
                controller:'mailverify'

            })
            .state('accountInfo', {
                url : '/accountInfo?{uid}',
                templateUrl : '/register/app/tpls/rev/accountInfo.html',
                controller:'register'
            })
            .state('finish', {
                url : '/finish',
                templateUrl : '/register/app/tpls/rev/finish.html'

            })

    }])
});