/**
 * Created by Bjike on 2016/9/7.
 */
define(['app'],function (app) {
    app.run(['$rootScope','$state','$stateParams',function ($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams
    }]);
    app.config(function ($stateProvider,$urlRouterProvider){
        $stateProvider.includeSpinner = true;
        $urlRouterProvider.otherwise('/index');
        $stateProvider
            .state('index',{
                url:'/index',
                views:{
                    '':{
                        templateUrl:'/html/web/feedback/tpls/wrapbody.html'
                    },
                    'menu@index':{
                        templateUrl:'/html/web/feedback/tpls/menu.html'
                    },
                    'content@index':{
                        templateUrl:'/html/web/feedback/tpls/content.html'
                    }

                }

            })
            .state('unpro',{
                url:'/unpro',
                views:{
                    '':{
                        templateUrl:'/html/web/feedback/tpls/wrapbody.html'
                    },
                    'menu@unpro':{
                        templateUrl:'/html/web/feedback/tpls/menu.html'
                    },
                    'content@unpro':{
                        templateUrl:'/html/web/feedback/tpls/unpro.html'
                    }

                }

            })
             .state('proing',{
                url:'/proing',
                views:{
                    '':{
                        templateUrl:'/html/web/feedback/tpls/wrapbody.html'
                    },
                    'menu@proing':{
                        templateUrl:'/html/web/feedback/tpls/menu.html'
                    },
                    'content@proing':{
                        templateUrl:'/html/web/feedback/tpls/proing.html'
                    }

                }

            })
            .state('proed',{
                url:'/proed',
                views:{
                    '':{
                        templateUrl:'/html/web/feedback/tpls/wrapbody.html'
                    },
                    'menu@proed':{
                        templateUrl:'/html/web/feedback/tpls/menu.html'
                    },
                    'content@proed':{
                        templateUrl:'/html/web/feedback/tpls/proed.html'
                    }

                }

            })
            .state('userinfo',{
                url:'/userinfo',
                views:{
                    '':{
                        templateUrl:'/html/web/feedback/tpls/wrapbody.html'
                    },
                    'menu@userinfo':{
                        templateUrl:'/html/web/feedback/tpls/menu.html'
                    },
                    'content@userinfo':{
                        templateUrl:'/html/web/feedback/tpls/userinfo.html'
                    }

                }

            })

    })

})
