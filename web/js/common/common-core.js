var common = {version: "20150102"};

common.call = function (config) {
    common.debug(config)
    var className = config.className;
    var method = config.method;
    var async = true;
    if (config.async != undefined) async = config.async;
    if (!method) method = "call";
    if (!className) {
        alert("类名不可为空!");
    }
    var param = config.param;
    if (!param) param = {};
    var callBack = config.callBack;
    var exceptionHandler = config.exceptionHandler;
    DWRMessenger.call(className, method, param, {
        callback: function (result) {
            if ($.isFunction(callBack)) callBack(result);
        },
        exceptionHandler: function (message, e) {
            if ($.isFunction(exceptionHandler)) {
                exceptionHandler(message, e);
            } else {
                if (message) {
                    alert("error：" + message);
                } else if (e && e.cause && e.cause.message) {
                    alert("error：" + e.cause.message);
                } else {
                    alert("error")
                }
            }
        },
        async: async
    });
};

common.debug = function (data, level, depth) {
    var text = DebugUtils.toString(data, level, depth).replace("\n", "<br>");
    if ($("#__debugDiv").length == 0) {
        $("body").append("<div style='display:none' id='__debugDiv'></div>");
    }
    var alertDiv = $("#__debugDiv");
    $(alertDiv).html(text);
    $(alertDiv).dialog({
        width: 500,
        title: "Debug",
        modal: true,
        resizable: false,
        zIndex: 99999
    });
};