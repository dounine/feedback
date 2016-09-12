define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.register",//定义的模块名称
        [
            "app.services.register"
        ]);

    app.controller('register',['$scope','$rootScope','config','$http','$location','mailService',ctl]);
    app.controller('mailverify',['$scope','$rootScope','config','$http','$location','mailService',mailverify]);

    function ctl($scope, $rootScope,config,$http,$location,mailService) {
        var vm = $scope;

        vm.submitReg1 = function () {
            var data = {
                email:vm.email,
                password:vm.password
            };
            vm.msg = null;//清空原错误信息
            $http.post(config.lurl+"/register/mverify",data).then(function successCallback(response) {
                if(response.data){
                    mailService.setOpenUrl(response.data['data']);
                    $rootScope.$state.go("mailverify");
                }else{
                    vm.msg = response.data.msg;
                }
            }, function errorCallback(response) {
                vm.msg = response.data.msg;
            });
        }



        vm.submitReg3 = function(){
            var data ={
                details:{
                    company:vm.company,
                    address:vm.address,
                    postcodes:vm.postcodes,
                    contact:vm.username,
                    telephone:vm.tel,
                    fax:vm.fax
                }
            }
            console.info(data)
            vm.msg = null;//清空原错误信息
            $http.post(config.lurl+"/register/accountInfo",data).then(function successCallback(response){
                if(response.data){
                    vm.msg = null;
                    $rootScope.$state.go("finish");
                } else {
                    vm.msg = response.data.msg;
                }
            },function errorCallback(response){
                vm.msg = response.data.msg;
            })

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

    function mailverify($scope, $rootScope,config,$http,$location,mailService){
        var vm = $scope;
        $scope.reciveMail = function(){
            window.open(mailService.getOpenUrl());
        }
    }


});

