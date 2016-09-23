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
                    templateUrl : "/user/loginT",
                    controller : "login"
                }
            }
        })
    }])
});