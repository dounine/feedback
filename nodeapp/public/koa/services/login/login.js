var request = require('request-promise');
var path = require('path');
var configKoa = require(path.resolve('public/ng/modules/login/dist/js/config-koa.js'));

module.exports = function (argvs) {
    this.sso = function (argvs) {
        var rep = null;
        var options = {
            method: 'POST',
            uri: configKoa()['rurl']+'/login',
            form: argvs,
            headers: {
                /* 'content-type': 'application/x-www-form-urlencoded' */ // Set automatically
            }
        };
        return request(options);
    }

    return this;
}