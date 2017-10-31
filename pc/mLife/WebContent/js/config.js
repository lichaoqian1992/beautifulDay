/**
 * Created with of666.
 * Date: 13-6-3
 * Time: 下午3:57
 */

seajs.config({
    // 别名配置
    alias: {
        jquery: 'lib/jquery-1.9.1.js',
        handlebars: "base/handlebars.js",
        amplify: 'base/amplify.js',
        form: 'lib/jquery.form.js',
        datatable: 'lib/datatables/jquery.dataTables.js',
        saledatatable: 'lib/datatables/saledatatable.js',
        gavincity:'online/themes/common/region/China_Region_Last.js'
    },

    // 路径配置
    paths: {
        'online': 'http://pic.qianmi.com',
        'staticpath':'http://pic.qianmi.com/themes/sales9.9'
    },
    preload: [
        "jquery",
        "seajs/seajs-text.js",
        "lib/jquery.placeholder.js",
        "lib/frame-fixed.js",
        "lib/jquery_select.lib.js",
        "lib/bootstrap.js",
        "lib/jquery.cookie.js",
        "lib/jquery-validate.js",
        "base/of-order-validator.js",
        "lib/jqueryui/jquery.ui.js",
        "lib/jquery.qtip.min.js",
        "base/base-concat.js"
    ],
    // 文件编码
    charset: 'utf-8',
    map: [
        ['.js','.js?66666']
    ]
    // 调试模式debug: true
});