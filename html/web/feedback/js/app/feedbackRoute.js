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
            .state('index.unpro',{
                url:'/unpro',
                views:{
                    'content@index':{
                        templateUrl:'/html/web/feedback/tpls/unpro.html'
                    }
                }
            })
            .state('index.proing',{
                url:'/proing',
                views:{
                    'content@index':{
                        templateUrl:'/html/web/feedback/tpls/proing.html'
                    }
                }
            })
            .state('index.proed',{
                url:'/proed',
                views:{
                    'content@index':{
                        templateUrl:'/html/web/feedback/tpls/proed.html'
                    }
                }
            })

    })

})
