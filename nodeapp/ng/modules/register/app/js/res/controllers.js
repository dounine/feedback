define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.register",//定义的模块名称
        [
            "app.services.register"
        ]);

    app.controller('register',['$scope','$rootScope','config','$http','$location',ctl]);

    function ctl($scope, $rootScope,config,$http,$location) {
        var vm = $scope;
        $scope.user ={};


        vm.submitReg3 = function(){
            console.log($scope.username)

            
        }






        vm.submitReg1 = function () {
            var data = {
                email:vm.email
            };
            console.log($scope.user);

            vm.msg = null;//清空原错误信息
            $http.post(config.lurl+"/register/mverify",data).then(function successCallback(response) {
                console.info(response.data);
                $rootScope.$state.go("mailverify");
                if(response.data){
                    vm.msg = null;
                }else{
                    vm.msg = response.data.msg;
                }
            }, function errorCallback(response) {
                vm.msg = response.data.msg;
            });
        }


        //vm.submitReg3 = function(){
        //    var data = {
        //        email:vm.email
        //    };
        //    $http.post(config.lurl+"/register/accountInfo",data).then(function successCallback(response) {
        //        console.info(response.data);
        //        $rootScope.$state.go("finish");
        //        if(response.data){
        //            vm.msg = null;
        //        }else{
        //            vm.msg = response.data.msg;
        //        }
        //    }, function errorCallback(response) {
        //        vm.msg = response.data.msg;
        //    });
        //}
    }
    app.controller('reg3',function($scope,$http){
        //$scope.username = {}

    })

});

