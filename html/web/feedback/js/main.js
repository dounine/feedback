/*jshint unused: vars */
require.config({
    baseUrl: '/html/web/js',
    paths: {
    	'jquery': 'lib/jquery/jquery',
    	'angular': 'lib/angular/angular',
    	'bootstrap':'lib/bootstrap/bootstrap',
        'angular-route': 'lib/angular-ui-router/angular-ui-router',
        'angular-loading-bar':'lib/angular-loading-bar/loading-bar',
        'app': '/html/web/feedback/js/app',
        'router':'/html/web/feedback/js/app/feedbackRoute',
        'controler':'/html/web/feedback/js/app/feedbackController',
        'menuController':'/html/web/feedback/js/app/menu/menuController',
        'menuDirective':'/html/web/feedback/js/app/menu/menuDirective',
        'formDirective':'/html/web/feedback/js/app/form/formDirective'
    },
    shim: {
        'jquery':{
            'exports':'$'
        },
        'angular' : {
            'deps':['jquery'],
            'exports' : 'angular'
        },
        'bootstrap':{
            'deps':['jquery']
        },
        'angular-route': {
            'deps':['angular'],
            exports : 'angular-route'
        },
        'angular-loading-bar':{
            'deps':['angular']
        }

    },
  priority: [
      'angular'
  ],
  packages: [
		 
  ],
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = 'NG_DEFER_BOOTSTRAP!';

require([
	'app',
    'angular',
    'jquery',
    'angular-route',
    'angular-loading-bar',
    'router',
    'controler',
    'bootstrap',
    'menuController',
    'menuDirective',
    'formDirective'
], function(app,angular,$,angularRoute,angularAnimate,angularLoading) {


 'use strict';

      var $html = angular.element(document.getElementsByTagName('html')[0]);
  angular.element().ready(function() {
            angular.resumeBootstrap([app.name]);

  });

});
