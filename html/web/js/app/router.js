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
					'content@index':{
						templateUrl:'tpls/content.html'
					}
				}
				
			})
   })
    
})
