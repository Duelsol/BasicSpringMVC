var DebugUtils = {};

DebugUtils.toString = function (data, level, depth) {
    function _isArray(data) {
        return (data && data.join) ? true : false;
    }

    function _detailedTypeOf(x) {
        var reply = typeof x;
        if (reply == "object") {
            reply = Object.prototype.toString.apply(x); // Returns "[object class]"
            reply = reply.substring(8, reply.length - 1);  // Just get the class bit
        }
        return reply;
    }

    function _indent(level, depth) {
        var reply = "";
        if (level != 0) {
            for (var j = 0; j < depth; j++) {
                reply += "\u00A0\u00A0";
            }
            reply += " ";
        }
        return reply;
    }

    function _isDate(data) {
        return (data && data.toUTCString) ? true : false;
    }

    function _isHTMLElement(ele, nodeName) {
        if (ele == null || typeof ele != "object" || ele.nodeName == null) {
            return false;
        }
        if (nodeName != null) {
            var test = ele.nodeName.toLowerCase();
            if (typeof nodeName == "string") {
                return test == nodeName.toLowerCase();
            }
            if (_isArray(nodeName)) {
                var match = false;
                for (var i = 0; i < nodeName.length && !match; i++) {
                    if (test == nodeName[i].toLowerCase()) {
                        match = true;
                    }
                }
                return match;
            }
            return false;
        }
        return true;
    }

    var reply = "";
    var i = 0;
    var value;
    var obj;
    if (level == null) level = 2;
    if (depth == null) depth = 0;
    if (data == null) return "null";
    if (_isArray(data)) {
        if (data.length == 0) reply += "[]";
        else {
            if (level != 0) reply += "[\n";
            else reply = "[";
            for (i = 0; i < data.length; i++) {
                try {
                    obj = data[i];
                    if (obj == null || typeof obj == "function") {
                        continue;
                    }
                    else if (typeof obj == "object") {
                        if (level > 0) value = DebugUtils.toString(obj, level - 1, depth + 1);
                        else value = _detailedTypeOf(obj);
                    }
                    else {
                        value = "" + obj;
                        value = value.replace(/\/n/g, "\\n");
                        value = value.replace(/\/t/g, "\\t");
                    }
                }
                catch (ex) {
                    value = "" + ex;
                }
                if (level != 0) {
                    reply += _indent(level, depth + 2) + value + ", \n";
                }
                else {
                    if (value.length > 13) value = value.substring(0, 10) + "...";
                    reply += value + ", ";
                    if (i > 5) {
                        reply += "...";
                        break;
                    }
                }
            }
            if (level != 0) reply += _indent(level, depth) + "]";
            else reply += "]";
        }
        return reply;
    }
    if (typeof data == "string" || typeof data == "number" || _isDate(data)) {
        return data.toString();
    }
    if (typeof data == "object") {
        var typename = _detailedTypeOf(data);
        if (typename != "Object")  reply = typename + " ";
        if (level != 0) reply += "{\n";
        else reply = "{";
        var isHtml = _isHTMLElement(data);
        for (var prop in data) {
            if (isHtml) {
                // HTML nodes have far too much stuff. Chop out the constants
                if (prop.toUpperCase() == prop || prop == "title" ||
                    prop == "lang" || prop == "dir" || prop == "className" ||
                    prop == "form" || prop == "name" || prop == "prefix" ||
                    prop == "namespaceURI" || prop == "nodeType" ||
                    prop == "firstChild" || prop == "lastChild" ||
                    prop.match(/^offset/)) {
                    continue;
                }
            }
            value = "";
            try {
                obj = data[prop];
                if (obj == null || typeof obj == "function") {
                    continue;
                }
                else if (typeof obj == "object") {
                    if (level > 0) {
                        value = "\n";
                        value += _indent(level, depth + 2);
                        value = DebugUtils.toString(obj, level - 1, depth + 1);
                    }
                    else {
                        value = _detailedTypeOf(obj);
                    }
                }
                else {
                    value = "" + obj;
                    value = value.replace(/\/n/g, "\\n");
                    value = value.replace(/\/t/g, "\\t");
                }
            }
            catch (ex) {
                value = "" + ex;
            }
            if (level == 0 && value.length > 13) value = value.substring(0, 10) + "...";
            var propStr = prop;
            if (propStr.length > 30) propStr = propStr.substring(0, 27) + "...";
            if (level != 0) reply += _indent(level, depth + 1);
            reply += prop + ":" + value + ", ";
            if (level != 0) reply += "\n";
            i++;
            if (level == 0 && i > 5) {
                reply += "...";
                break;
            }
        }
        reply += _indent(level, depth);
        reply += "}";
        return reply;
    }
    return data.toString();
};