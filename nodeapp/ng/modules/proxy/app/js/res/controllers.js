define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.proxy",//定义的模块名称
        [
            "app.services.proxy"
        ]);

    app.controller('proxy',['$scope','$rootScope','config','$http','$location',ctl]);

    function ctl($scope, $rootScope,config,$http,$location) {
        $scope.addmenu = false;
        $scope.addcla = false;
        $scope.flex1 = function () {
            $scope.addmenu = !$scope.addmenu;
            $scope.addcla =  !$scope.addcla
        }

        $scope.information = false;
        $scope.infocla = false;
        $scope.flex2 = function () {
            $scope.information=!$scope.information;
            $scope.infocla = !$scope.infocla
        }

        $scope.tiji = {
            'title': "确定提交信息",
            'yes' : "确定",
            'no' : "取消，继续编辑"
        }
        $scope.queding = function () {
            $scope.tiji = {
                'title': "提交成功！",
                'yes' : "继续添加信息",
                'no' : "查看状态",
            };
            $('#tijiBtn').click(function () {
                window.location.href="index.html"

                count ++
            });
        }
    }

});

