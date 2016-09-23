define(['angular','services'], function(angular) {
    "use strict";

    var app = angular.module(
        "app.controllers.register",//定义的模块名称
        [
            "app.services.register",
            "ngCookies"
        ]);

    app.controller('register',['$scope','$rootScope','$http','$location','$cookies',ctl]);
    app.controller('mailverify',['$scope','$rootScope','$http','$location','$cookies',sendMailInfo]);

    function ctl($scope, $rootScope,$http,$location,$cookies) {
        var vm = $scope;

        vm.submitReg1 = function () {
            var data = {
                email:vm.email,
                password:vm.password,
                captchaVal:vm.captchaVal
            };
            vm.msg = null;//清空原错误信息
            $http.post("/register/mailVerify",data).then(function successCallback(response) {
                if(response.data['errno']==0){
                    $rootScope.$state.go("sendMailInfo",{
                        emailName:response.data['data']['emailName'],
                        openUrl:response.data['data']['openUrl']
                    });
                }else{
                    vm.captchaVal = null;
                    vm.change_captcha_url();
                    if(response.data.msg!='请输入正确的验证码!'){
                        vm.password = null;
                        vm.msg = response.data.msg;
                    }
                }
            }, function errorCallback(response) {
                vm.msg = response.data.msg;
            });
        }

        vm.change_captcha_url = function () {
            vm.captcha_url='/captcha?t='+(new Date().getTime());
        }



        vm.submitReg3 = function(){
            var data ={
                details:{
                    company:vm.company,
                    address:vm.address,
                    postcodes:vm.postcodes,
                    contact:vm.contact,
                    telephone:vm.telephone,
                    fax:vm.fax
                },
                id:$rootScope.$stateParams.uid
            }
            vm.msg = null;//清空原错误信息
            $http.post("/register/accountInfo",data).then(function successCallback(response){
                if(response.data['errno']==0){
                    $rootScope.$state.go("finish");
                } else {
                    vm.msg = response.data.msg;
                }
            },function errorCallback(response){
                vm.msg = response.data.msg;
            })
        }
    }

    function sendMailInfo($scope, $rootScope,$http,$location,$cookies){
        var vm = $scope;
        vm.emailName = $rootScope.$stateParams.emailName;
        $scope.reciveMail = function(){
            window.open($rootScope.$stateParams.openUrl);
        }
    }


});

