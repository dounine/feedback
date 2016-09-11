var request = require('request-promise');
var path = require('path');
var config = require(path.resolve('plugins/read-config.js'));

module.exports = function (argvs) {
    this.mverify = function (argvs) {
        var rep = null;
        var options = {
            method: 'POST',
            timeout:3000,
            uri: config()['rurl']+'/register',
            form: argvs,
            headers: {
                /* 'content-type': 'application/x-www-form-urlencoded' */ // Set automatically
            }
        };
        return request(options);
    }

    return this;
}