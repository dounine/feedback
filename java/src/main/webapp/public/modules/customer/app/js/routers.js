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
        $stateProvider
            .state('index',{
                url:'/index',
                views:{
                    '':{
                        templateUrl:'/customer/wrapbody'
                    },
                    'menu@index':{
                        templateUrl:'/customer/menu',
                        controller:'customer'
                    },
                    'content@index':{
                        templateUrl:'/customer/content',
                        controller:'customer'
                    }

                }

            })
            .state('unpro',{
                url:'/unpro',
                views:{
                    '':{
                        templateUrl:'/customer/wrapbody'
                    },
                    'menu@unpro':{
                        templateUrl:'/customer/menu',
                        controller:'customer'
                    },
                    'content@unpro':{
                        templateUrl:'/customer/unpro',
                    }

                }

            })
            .state('proing',{
                url:'/proing',
                views:{
                    '':{
                        templateUrl:'/customer/wrapbody'
                    },
                    'menu@proing':{
                        templateUrl:'/customer/menu',
                        controller:'customer'
                    },
                    'content@proing':{
                        templateUrl:'/customer/proing'
                    }
                }
            })
            .state('proed',{
                url:'/proed',
                views:{
                    '':{
                        templateUrl:'/customer/wrapbody'
                    },
                    'menu@proed':{
                        templateUrl:'/customer/menu',
                        controller:'customer'
                    },
                    'content@proed':{
                        templateUrl:'/customer/proed'
                    }

                }

            })
            .state('userinfo',{
                url:'/userinfo',
                views:{
                    '':{
                        templateUrl:'/customer/wrapbody'
                    },
                    'menu@userinfo':{
                        templateUrl:'/customer/menu',
                        controller:'customer'
                    },
                    'content@userinfo':{
                        templateUrl:'/customer/userinfo'
                    }
                }
            })
    }])
});