define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.login",//定义的模块名称
        [
            "app.services.login"//依赖的模块
        ]);

    app.controller("login", login);
    
    function login($scope, $rootScope,config) {
        var vm = $scope;
        console.debug("全局环境变量：");
        console.debug(config);
        ///vm.verify = vm.signin.$invalid;
        //$rootScope.$state.go("fileList", {path: "/"});
    }

});

