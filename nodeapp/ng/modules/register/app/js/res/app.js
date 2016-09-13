define(['angular', 'controllers'], function(angular) {
	'use strict';

	var app = angular.module("app", ["app.controllers.register", "angular-loading-bar"]);
	app.run(['$cookies','$rootScope',appRun]);

	function appRun($cookies, $rootScope) {
		$rootScope.ctx = document.body.getAttribute("ctx"); //项目根路径
	}

	app.config(['$httpProvider','$stateProvider','$urlRouterProvider',appConfig]);

	function appConfig($httpProvider, $stateProvider, $urlRouterProvider) {
		$httpProvider.defaults.headers.post["Accept"] = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		$httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$httpProvider.defaults.transformRequest = function(data) {
			if(data === undefined) {
				return data;
			}
			var datas = "";
			for(var name in data){
				if((typeof data[name])=="object"){
					for(var _n in data[name]){
						var val = data[name][_n];
						datas +=((name+"."+_n)+'='+(val?val:'')+'&');
					}
				}else{
					datas += (name+'='+data[name]+'&');
				}
			}
			//return $.param(data);
			return datas;
		}
	}
	return app;
});


