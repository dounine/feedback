define(['angular', 'controllers'], function(angular) {
	'use strict';

	var app = angular.module("app", ["app.controllers.login", "angular-loading-bar"]);
	app.run(appRun);

	function appRun($cookies, $rootScope) {
		$cookies.clouddisk_account = "102535481@qq.com";
		$rootScope.ctx = document.body.getAttribute("ctx"); //项目根路径
	}

	app.config(appConfig);

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