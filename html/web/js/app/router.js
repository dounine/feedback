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
						templateUrl:'tpls/wrapbody.html'
					},
					'menu@index':{
						templateUrl:'tpls/pro/menu.html'
					},
					'content@index':{
						templateUrl:'tpls/pro/content.html'
					}
					
				}
				
			})
			.state('index.unpro',{
				url:'/unpro',
				views:{
					'content@index':{
						templateUrl:'tpls/pro/unpro.html'
					}
				}
			})
			.state('index.proing',{
				url:'/proing',
				views:{
					'content@index':{
						templateUrl:'tpls/pro/proing.html'
					}
				}
			})
			.state('index.proed',{
				url:'/proed',
				views:{
					'content@index':{
						templateUrl:'tpls/pro/proed.html'
					}
				}
			})
			
   })
    
})
