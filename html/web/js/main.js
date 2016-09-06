/*jshint unused: vars */
require.config({
    baseUrl: 'js',
    paths: {
    	'jquery': 'lib/jquery/jquery',
    	'angular': 'lib/angular/angular',
    	'bootstrap':'lib/bootstrap/bootstrap',
        'angular-route': 'lib/angular-ui-router/angular-ui-router',
        'angular-loading-bar':'lib/angular-loading-bar/loading-bar',
        'app': 'app',
        'router':'app/router',
        'controler':'app/controller',
        'directives':'app/directives',
        'feedback':'app/feedback'
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
        },
        'feedback':{
        	'deps':['jquery']
        }

    },
  priority: [
      'angular'
  ],
  packages: [

  ]
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
    'directives',
    'bootstrap',
    'feedback'
], function(app,angular,$,angularRoute,angularAnimate,angularLoading) {


 'use strict';
    /* jshint ignore:start */
//  var $html = angular.element(document.getElementsByTagName('html')[0]);
    /* jshint ignore:end */
   
  angular.element().ready(function() {
            angular.resumeBootstrap([app.name]);

  });

});
