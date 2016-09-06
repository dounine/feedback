define(['angular',"app/js/services"], function(angular) {
    "use strict";

    var app = angular.module(
        "app.controllers.login",//定义的模块名称
        [
            "app.services.login"//依赖的模块
        ]);

    app.controller("login", login);

    function login($scope, $rootScope) {
        var vm = $scope;
        ///vm.verify = vm.signin.$invalid;
        //$rootScope.$state.go("fileList", {path: "/"});
    }

});

