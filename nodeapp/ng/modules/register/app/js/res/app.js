define(['angular', 'controllers'], function(angular) {
	'use strict';

	var app = angular.module("app", ["app.controllers.register", "angular-loading-bar"]);
	app.run(['$cookies','$rootScope',appRun]);

	function appRun($cookies, $rootScope) {
		$rootScope.ctx = document.body.getAttribute("ctx"); //项目根路径
	}

	app.config(['$httpProvider','$stateProvider','$urlRouterProvider',appConfig]);

	function appConfig($httpProvider, $stateProvider, $urlRouterProvider) {
		$httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";
		$httpProvider.defaults.transformRequest = function(data) {
			if(data === undefined) {
				return data;
			}
			return $.param(data);
		}
	}
	return app;
});


