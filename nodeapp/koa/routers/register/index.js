var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var fs = require('fs');
var stream = require('koa-stream');
var loginSer = require(path.resolve('koa/services/' + path.basename(__dirname) + '/index.js'));
var config = require(path.resolve('plugins/read-config.js'));
var fetch = require('node-fetch');//url转发

module.exports = function(config){
    var router = new Router();
    router.get('/register', function *(next){//转到注册页面
        var stats = yield (sendfile(this, path.resolve('ng/modules/' + path.basename(__dirname) + '/app/tpls/rev') + '/index.html'));
        if(!this.status){
            this.throw(404);
        }
    }).get('/captcha', function *(next){
        var $self = this;
        yield (fetch(config['rurl']+'/captcha?key='+($self.request.ip+$self.request.get("User-Agent")))
            .then(function(res) {
                return res.buffer();
            }).then(function(buffer) {
                $self.set('content-type','image/png');
                $self.body = buffer;
            }));
    }).post('/register/mailVerify', function *(next){//验证邮箱地扯
        var user = this.request.body;
        var $self = this;
        user.activePath = config['lurl']+"/register/reciveVerify";
        user.captchaKey = this.request.ip+this.request.get("User-Agent");
        yield (loginSer().mverify(user)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                if(responseText['errno'] == 0){
                    $self.body = {'data':{
                        openUrl:responseText['data'],
                        emailName:user.email
                    },errno:0,};
                } else {
                    $self.body = responseText;
                }

            }).catch(function(error){
                if(error.error && error.error.code && error.error.code == 'ETIMEDOUT'){//登录超时
                    $self.body = {'msg' : '注册超时,请尝试重新注册.', errno : 3};
                    $self.status = 408;
                }
            }));
    }).get('/register/reciveVerify', function *(next){//接收从邮箱过来的注册链接地扯
        var $self = this;
        var query = this.request.query;
        yield (loginSer().reciveVerify(query)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                if(responseText['errno'] == 0){
                    $self.redirect('/register#/accountInfo?uid='+query.uid);
                    $self.status = 301;
                    $self.body = {errno : 0};
                } else {
                    $self.redirect('/register#/index');
                    $self.status = 301;
                    $self.body = {msg : 'active failed', errno : responseText['errno']};
                }
            }));
    }).post('/register/accountInfo', function *(next){//提交客户信息
        var user = this.request.body;
        var $self = this;
        yield (loginSer().submitReg3(user)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                console.info(responseText);
                if(responseText['errno'] == 0){
                    $self.body = responseText;
                } else {
                    $self.body = responseText;
                }
            }).catch(function(error){
                console.info(error);
                if(error.error && error.error.code && error.error.code == 'ETIMEDOUT'){//登录超时
                    $self.body = {'msg' : '注册超时,请尝试重新注册.', errno : 3};
                    $self.status = 408;
                }
            }));
    })

    return router;
};