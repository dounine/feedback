define(['angular'], function(angular) {
    "use strict";

    var app = angular.module(
        "app.services.register",//定义的模块名称
        [
            "ui.router",//依赖的模块
            "ngCookies"
        ]);
    app.factory("mailService",['$http','$cookies', mailService]);

    app.constant("imgTypeIcos",
        [{suffixs: ["folder"]}
            , {suffixs: ["file"]}
            , {suffixs: ["png","jpg","gif","bmp","jpeg"]}
            , {suffixs: ["xls", "xlsx", "excel"]}
            , {suffixs: ["txt"]}]);

    function mailService($http,$cookies) {
        var $self = this;
        var service = {
            getOpenUrl: getOpenUrl,
            setOpenUrl: setOpenUrl,
            getMailName:getMailName
        };
        return service;

        function getOpenUrl() {
            return  $self.openUrl;
        }
        function setOpenUrl(openUrl) {
            $self.openUrl = openUrl;
        }
        function getMailName() {
            return $cookies.mailName;
        }
    }
});