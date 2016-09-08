define(['angular','services'], function(angular,config) {
    "use strict";
    var moduleName = config.moduleName;
    var app = angular.module(
        "app.controllers."+moduleName,
        [
            "app.services."+moduleName
        ]);

    app.controller(moduleName, ctl);

    function ctl($scope, $rootScope,config,$http) {
        var vm = $scope;

    }

});

