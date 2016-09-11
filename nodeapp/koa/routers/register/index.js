var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var loginSer = require(path.resolve('koa/services/' + path.basename(__dirname) + '/index.js'));
var config = require('../../../plugins/read-config.js');

module.exports = function (config) {
    var router = new Router();
    router.get('/register', function *() {
        var stats = yield (sendfile(this, path.resolve('ng/modules/' + path.basename(__dirname) + '/app/tpls/rev') + '/index.html'));
        if (!this.status) {
            this.throw(404);
        }
    }).post('/register/mverify', function *() {
        var user = this.request.body;
        var $self = this;
        $self.body = {'verifyUrl':'http://mail.163.com?uasdfkaslfdjk'};
        //yield (loginSer().mverify(user)
        //    .then(function (parsedBody) {
        //        var responseText = JSON.parse(parsedBody);
        //        console.info(responseText);
        //        if (responseText['errno'] == 0) {
        //            var token = JSON.parse(responseText['data'])['token'];
        //            $self.body = responseText;
        //            $self.cookies.set('token', token);
        //        } else {
        //            $self.body = responseText;
        //        }
        //
        //    }).catch(function (error) {
        //        if (error.error && error.error.code && error.error.code =='ETIMEDOUT') {//登录超时
        //            $self.body = {'msg':'登录超时,请尝试重新登录.',errno:3};
        //            $self.status = 408;
        //        }
        //    }));


    }).post('/register/accountInfo',function *(){
        var user = this.request.body;
        var $self = this;
        $self.body = {'verifyUrl':'http://mail.163.com?accountInfo'};

        yield (loginSer().submitReg3(user)
            .then(function (parsedBody) {
                var responseText = JSON.parse(parsedBody);
                console.info(responseText);
                if (responseText['errno'] == 0) {
                    var token = JSON.parse(responseText['data'])['token'];
                    $self.body = responseText;
                    $self.cookies.set('token', token);
                } else {
                    $self.body = responseText;
                }

            }));
    })

    return router;
};