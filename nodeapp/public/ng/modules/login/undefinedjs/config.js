
define(["require", "exports"], function(require, exports) {
  return angular.module("app.login.config", ["ngAnimate"])
.constant("development", {"url":"http://localhost","rootFold":"app/"})
.constant("production", {"url":"http://localhost","rootFold":"dist/"})
.constant("myPropCnt", "hola!");

});

