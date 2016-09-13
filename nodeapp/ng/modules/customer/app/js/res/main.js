/*jshint unused: vars */
require.config({
	baseUrl: './customer',
	paths: {
		'app': 'app/js/rev/app',
		'routers': 'app/js/rev/routers',
		'dataservices': 'app/js/rev/dataservices',
		'controllers': 'app/js/rev/controllers',
		'directives': 'app/js/rev/directives',
		'filters': 'app/js/rev/filters',
		'services': 'app/js/rev/services',
		'config': 'app/js/rev/__config-ng',
		'tetherWrapper': 'app/js/rev/require-wrapper/tetherWrapper',
		'jquery': 'app/lib/jquery/dist/jquery.min',
		'bootstrap': 'app/lib/bootstrap/dist/js/bootstrap.min',
		'angular': 'app/lib/angular/angular.min',
		'css': 'app/lib/require-css/css',
		'tether': 'app/lib/tether/dist/js/tether.min',
		'normalize': 'app/lib/require-css/normalize',
		'angular-ui-route': 'app/lib/angular-ui-router/release/angular-ui-router.min',
		'angular-animate': 'app/lib/angular-animate/angular-animate.min',
		'angular-file-upload': 'app/lib/angular-file-upload/dist/angular-file-upload.min',
		'angular-loading-bar': 'app/lib/angular-loading-bar/build/loading-bar.min',
		'angular-cookies': 'app/lib/angular-cookies/angular-cookies.min',
		'tm.pagination':'app/js/rev/tm.pagination',
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