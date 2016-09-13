var request = require('request-promise');
var path = require('path');
var config = require(path.resolve('plugins/read-config.js'));
var form = require(path.resolve('plugins/form.js'));

module.exports = function (argvs) {
    this.captcha = function () {
        var options = {
            method: 'get',
            timeout:3000,
            uri: config()['rurl']+'/captcha',
            headers: {
                /* 'content-type': 'application/x-www-form-urlencoded' */ // Set automatically
            }
        };
        return request(options);
    }
    this.mverify = function (argvs) {
        var rep = null;
        var options = {
            method: 'POST',
            timeout:3000,
            uri: config()['rurl']+'/register/valid',
            form: argvs,
            headers: {
                /* 'content-type': 'application/x-www-form-urlencoded' */ // Set automatically
            }
        };
        return request(options);
    }
    this.reciveVerify = function (argvs) {
        var options = {
            method: 'POST',
            timeout:3000,
            uri: config()['rurl']+'/register/activate',
            form: argvs,
            headers: {
                /* 'content-type': 'application/x-www-form-urlencoded' */ // Set automatically
            }
        };
        return request(options);
    }
    this.submitReg3 = function (argvs) {
        var data = argvs;
        var options = {
            method: 'POST',
            timeout:3000,
            preambleCRLF: true,
            postambleCRLF: true,
            uri: config()['rurl']+'/register/perfect',
            form: form(argvs),
            headers: {
                'content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            }
        };
        return request(options);
    }

    return this;
}