var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var loginSer = require(path.resolve('koa/services/' + path.basename(__dirname) + '/index.js'));
var config = require('../../../plugins/read-config.js');

module.exports = function(config){
    var router = new Router();
    router.get('/register', function *(){
        var stats = yield (sendfile(this, path.resolve('ng/modules/' + path.basename(__dirname) + '/app/tpls/rev') + '/index.html'));
        if(!this.status){
            this.throw(404);
        }
    }).post('/register/mverify', function *(){
        var user = this.request.body;
        var $self = this;
        user.activePath = "http://192.168.0.115:8888/register/reciveVerify";
        yield (loginSer().mverify(user)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                if(responseText['errno'] == 0){
                    $self.body = responseText;
                } else {
                    $self.body = responseText;
                }

            }).catch(function(error){
                if(error.error && error.error.code && error.error.code == 'ETIMEDOUT'){//登录超时
                    $self.body = {'msg' : '注册超时,请尝试重新注册.', errno : 3};
                    $self.status = 408;
                }
            }));


    }).get('/register/reciveVerify', function *(){
        var $self = this;
        var query = this.request.query;
        yield (loginSer().reciveVerify(query)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                if(responseText['errno'] == 0){
                    $self.redirect('/register#/accountInfo');
                    $self.cookies.set('uid',query.uid);
                    $self.status = 301;
                    $self.body = {errno : 0};
                } else {
                    $self.redirect('/register#/mailverify');
                    $self.status = 301;
                    $self.body = {msg : 'active failed', errno : responseText['errno']};
                }

            }));
    }).post('/register/accountInfo', function *(){
        var user = this.request.body;
        var $self = this;
        user.uid = $self.cookies.get('uid');
        yield (loginSer().submitReg3(user)
            .then(function(parsedBody){
                var responseText = JSON.parse(parsedBody);
                console.info(responseText);
                if(responseText['errno'] == 0){
                    $self.body = responseText;
                } else {
                    $self.body = responseText;
                }

            }));
    }).post('/register/finish', function *(){
        var stats = yield (sendfile(this, path.resolve('ng/modules/' + path.basename(__dirname) + '/app/tpls/rev') + '/register4.html'));
        if(!this.status){
            this.throw(404);
        }
    })

    return router;
};