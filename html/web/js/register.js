var regApp = angular.module('regApp',['ui.router']);

regApp.run(function($rootScope, $state, $stateParams) {
	    $rootScope.$state = $state;
	    $rootScope.$stateParams = $stateParams;
	});
	
regApp.config(function ($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.otherwise('/index');
		$stateProvider
	        .state('index', {
	            url: '/index',
	            views: {
	                '': {
	                    templateUrl: './register/regindex.html'
	                },
	                'main@index': {
	                    templateUrl: './register/register1.html'
	                }
	            }
	        })
	        .state('register2',{
	            url:'/register2',
	            templateUrl:'./register/register2.html'
	            
	        })
	        .state('register3',{
	            url:'/register3',
	            templateUrl:'./register/register3.html'
	            
	        })
	         .state('register4',{
	            url:'/register4',
	            templateUrl:'./register/register4.html'
	            
	        })
})