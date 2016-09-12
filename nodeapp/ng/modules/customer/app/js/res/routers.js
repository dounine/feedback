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
                        templateUrl:'/customer/app/tpls/rev/wrapbody.html'
                    },
                    'menu@index':{
                        templateUrl:'/customer/app/tpls/rev/menu.html'
                    },
                    'content@index':{
                        templateUrl:'/customer/app/tpls/rev/content.html',
                        controller:'customer'
                    }

                }

            })
            .state('unpro',{
                url:'/unpro',
                views:{
                    '':{
                        templateUrl:'/customer/app/tpls/rev/wrapbody.html'
                    },
                    'menu@unpro':{
                        templateUrl:'/customer/app/tpls/rev/menu.html'
                    },
                    'content@unpro':{
                        templateUrl:'/customer/app/tpls/rev/unpro.html'
                    }

                }

            })
            .state('proing',{
                url:'/proing',
                views:{
                    '':{
                        templateUrl:'/customer/app/tpls/rev/wrapbody.html'
                    },
                    'menu@proing':{
                        templateUrl:'/customer/app/tpls/rev/menu.html'
                    },
                    'content@proing':{
                        templateUrl:'/customer/app/tpls/rev/proing.html'
                    }

                }

            })
            .state('proed',{
                url:'/proed',
                views:{
                    '':{
                        templateUrl:'/customer/app/tpls/rev/wrapbody.html'
                    },
                    'menu@proed':{
                        templateUrl:'/customer/app/tpls/rev/menu.html'
                    },
                    'content@proed':{
                        templateUrl:'/customer/app/tpls/rev/proed.html'
                    }

                }

            })
            .state('userinfo',{
                url:'/userinfo',
                views:{
                    '':{
                        templateUrl:'/customer/app/tpls/rev/wrapbody.html'
                    },
                    'menu@userinfo':{
                        templateUrl:'/customer/app/tpls/rev/menu.html'
                    },
                    'content@userinfo':{
                        templateUrl:'/customer/app/tpls/rev/userinfo.html'
                    }

                }

            })
    }])
});