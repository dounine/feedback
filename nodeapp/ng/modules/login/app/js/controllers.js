define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.login",//定义的模块名称
        [
            "app.services.login"
        ]);

    app.controller('login',['$scope','$rootScope','config','$http','$location',ctl]);

    function ctl($scope, $rootScope,config,$http,$location) {
        var vm = $scope;
        //vm.sso = sso;
        console.debug("全局环境变量：");
        console.debug(config);
        ///vm.verify = vm.signin.$invalid;
        //$rootScope.$state.go("fileList", {path: "/"});
        vm.sso = function () {
            var data = {
                username:vm.username,
                password:vm.password
            };
            $http.post(config.lurl+"/login",data).then(function successCallback(response) {
                if(response.data){
                    vm.msg = null;
                    window.location = "/admin";
                }else{
                    vm.msg = response.data.msg;
                }
            }, function errorCallback(response) {
                vm.msg = response.data.msg;
            });
        }
    }

});

