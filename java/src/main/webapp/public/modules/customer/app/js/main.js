/*jshint unused: vars */
require.config({
	baseUrl: '/public/modules/customer',
	paths: {
		'app': 'app/js/app',
		'routers': 'app/js/routers',
		'dataservices': 'app/js/dataservices',
		'controllers': 'app/js/controllers',
		'directives': 'app/js/directives',
		'filters': 'app/js/filters',
		'services': 'app/js/services',
		'tetherWrapper': 'app/js/require-wrapper/tetherWrapper',
		'jquery': '/public/lib/jquery/dist/jquery.min',
		'bootstrap': '/public/lib/bootstrap/dist/js/bootstrap.min',
		'angular': '/public/lib/angular/angular.min',
		'css': '/public/lib/require-css/css',
		'tether': '/public/lib/tether/dist/js/tether.min',
		'normalize': '/public/lib/require-css/normalize',
		'angular-ui-route': '/public/lib/angular-ui-router/release/angular-ui-router.min',
		'angular-animate': '/public/lib/angular-animate/angular-animate.min',
		'angular-file-upload': '/public/lib/angular-file-upload/dist/angular-file-upload.min',
		'angular-loading-bar': '/public/lib/angular-loading-bar/build/loading-bar.min',
		'angular-cookies': '/public/lib/angular-cookies/angular-cookies.min',
		'tm.pagination':'/public/js/rev/tm.pagination',
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
		'tm.pagination': {
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
	'app',
	'dataservices',
	'directives',
	'filters',
	'routers',
	'bootstrap',
	'angular-ui-route',
	'angular-cookies',
	'angular-animate',
	'angular-loading-bar',
	//'tm.pagination',
], function(angular, app) {
	'use strict';
	/* jshint ignore:start */
	var $html = angular.element(document.getElementsByTagName('html')[0]);
	/* jshint ignore:end */

	angular.element().ready(function() {
		angular.resumeBootstrap([app.name]);
	});

});