/*jshint unused: vars */
require.config({
	baseUrl: 'admin',
	paths: {
		'app': 'app',
		'routers': 'app/js/routers',
		'config': 'dist/js/config',
		'dataservices': 'app/js/dataservices',
		'controllers': 'app/js/controllers',
		'directives': 'app/js/directives',
		'filters': 'app/js/filters',
		'services': 'app/js/services',
		'jquery': 'dist/lib/jquery/dist/jquery.min',
		'bootstrap': 'dist/lib/bootstrap/dist/js/bootstrap.min',
		'angular': 'dist/lib/angular/angular.min',
		'css': 'dist/lib/require-css/css',
		'tetherWrapper': 'dist/js/require-wrapper/tetherWrapper.min',
		'tether': 'dist/lib/tether/dist/js/tether.min',
		'normalize': 'dist/lib/require-css/normalize',
		'angular-ui-route': 'dist/lib/angular-ui-router/release/angular-ui-router.min',
		'angular-animate': 'dist/lib/angular-animate/angular-animate.min',
		'angular-file-upload': 'dist/lib/angular-file-upload/dist/angular-file-upload.min',
		'angular-loading-bar': 'dist/lib/angular-loading-bar/build/loading-bar.min',
		'angular-cookies': 'dist/lib/angular-cookies/angular-cookies.min'
	},
	shim: {
		'jquery': {
			'exports': '$'
		},
		'angular': {
			'deps': ['jquery'],
			'exports': 'angular'
		},
		'angular-animate': {
			'deps': ['angular']
		},
		'bootstrap': {
			'deps': [
				'tetherWrapper',
				'jquery'
			]
		},
		'angular-ui-route': {
			'deps': ['angular']
		},
		'angular-loading-bar': {
			'deps': [
				'angular'
			]
		},
		'angular-file-upload': ['angular'],
		'angular-cookies': ['angular']
	},
	priority: [
		'angular'
	],
	packages: [

	],
	urlArgs: "_t=" + new Date().getTime() //防止读取缓存，调试用
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = 'NG_DEFER_BOOTSTRAP!';

require([
	'angular',
	'config',
	'app',
	'dataservices',
	'directives',
	'filters',
	'routers',
	'bootstrap',
	'angular-ui-route',
	'angular-cookies',
	'angular-animate',
	'angular-loading-bar'
], function(angular, app) {
	'use strict';
	/* jshint ignore:start */
	var $html = angular.element(document.getElementsByTagName('html')[0]);
	/* jshint ignore:end */

	angular.element().ready(function() {
		angular.resumeBootstrap([app.name]);
	});

});