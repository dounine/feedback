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
                        templateUrl:'/proxy/app/tpls/rev/wrapbody.html'
                    },
                    'menu@index':{
                        templateUrl:'/proxy/app/tpls/rev/menu.html'
                    },
                    'content@index':{
                        templateUrl:'/proxy/app/tpls/rev/content.html'
                    }

                }

            })
            .state('unpro',{
                url:'/unpro',
                views:{
                    '':{
                        templateUrl:'/proxy/app/tpls/rev/wrapbody.html'
                    },
                    'menu@unpro':{
                        templateUrl:'/proxy/app/tpls/rev/menu.html'
                    },
                    'content@unpro':{
                        templateUrl:'/proxy/app/tpls/rev/unpro.html'
                    }

                }

            })
            .state('proing',{
                url:'/proing',
                views:{
                    '':{
                        templateUrl:'/proxy/app/tpls/rev/wrapbody.html'
                    },
                    'menu@proing':{
                        templateUrl:'/proxy/app/tpls/rev/menu.html'
                    },
                    'content@proing':{
                        templateUrl:'/proxy/app/tpls/rev/proing.html'
                    }

                }

            })
            .state('proed',{
                url:'/proed',
                views:{
                    '':{
                        templateUrl:'/proxy/app/tpls/rev/wrapbody.html'
                    },
                    'menu@proed':{
                        templateUrl:'/proxy/app/tpls/rev/menu.html'
                    },
                    'content@proed':{
                        templateUrl:'/proxy/app/tpls/rev/proed.html'
                    }

                }

            })
            .state('userinfo',{
                url:'/userinfo',
                views:{
                    '':{
                        templateUrl:'/proxy/app/tpls/rev/wrapbody.html'
                    },
                    'menu@userinfo':{
                        templateUrl:'/proxy/app/tpls/rev/menu.html'
                    },
                    'content@userinfo':{
                        templateUrl:'/proxy/app/tpls/rev/userinfo.html'
                    }

                }

            })
    }])
});