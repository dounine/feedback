define(['angular','services'], function(angular) {
    "use strict";

    var app = angular.module(
        "app.controllers.login",//定义的模块名称
        [
            "app.services.login"
        ]);

    app.controller('login',['$scope','$rootScope','$http','$location',ctl]);

    function ctl($scope, $rootScope,$http,$location) {
        var vm = $scope;
        vm.sso = function () {
            var data = {
                username:vm.username,
                password:vm.password
            };

            vm.msg = null;//清空原错误信息
            $http.post("/login",data).then(function successCallback(response) {
                if(response.data['errno']==0){
                    vm.msg = null;
                    if(JSON.parse(response.data['data'])['userType']=='CUSTOM'){
                        window.location.href='/customer';
                    }else if(JSON.parse(response.data['data'])['userType']=='MANAGER'){
                        window.location.href='/admin';
                    }
                }else{
                    vm.msg = response.data.msg;
                    vm.password = null;
                }
            }, function errorCallback(response) {
                vm.msg = response.data.msg;
            });
        }
    }

});

