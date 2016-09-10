var config = require('../../config.json');

module.exports = function () {
    var def = config.default || 'dev';
    if (def !== 'dev' && def !== 'pro') {
        console.error('/config.json 配置文件[default]属性错误(只能配置dev|pro)!!!');
        def = 'dev';
    }
    return config[config.default];
}