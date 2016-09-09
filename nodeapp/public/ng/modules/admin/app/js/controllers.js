define(['angular','services'], function(angular,config) {
    var app = angular.module(
        "app.controllers.admin",//定义的模块名称
        [
            "app.services.admin"
        ]);

    app.controller("admin", ctl);

    function ctl($scope, $rootScope,config,$http) {
        var vm = $scope;

    }

});

