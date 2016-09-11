var request = require('request-promise');
var path = require('path');
var config = require(path.resolve('plugins/read-config.js'));

module.exports = function (argvs) {
    this.mverify = function (argvs) {
        console.info(argvs);
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
        var rep = null;
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
        var rep = null;
        var options = {
            method: 'POST',
            timeout:3000,
            uri: config()['rurl']+'/register/perfect',
            data: argvs,
            headers: {
                //'content-type': 'application/x-www-form-urlencoded'
            }
        };
        return request(options);
    }

    return this;
}