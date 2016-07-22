<%--
  Created by IntelliJ IDEA.
  Author: 冯奕骅
  Date: 14-9-4
  Time: 23:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common.jsp" %>
<html>
<head>
    <title>jquery顶部固定导航下拉菜单</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/index/topanv.css" rel="stylesheet"/>
    <script type="text/javascript">
        $(function () {
            var $anvlfteb = $('#anvlfteb'),
                    $posbox = $anvlfteb.find('div.posbox'),
                    $seledbox = $("#seledbox"),
                    anvjson = {
                        bbs: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>',
                        news: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico4.png">抚州教育</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico34.gif">江西快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>',
                        post: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico4.png">抚州教育</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>',
                        youhui: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico4.png">抚州教育</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico34.gif">江西快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>',
                        other: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico4.png">抚州教育</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>',
                        store: '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico7.gif">抚州快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico4.png">抚州教育</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico34.gif">江西快讯</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico17.gif">国内新闻</a>' +
                                '<a href="#"><img src="${pageContext.request.contextPath}/css/index/images/ico3.gif">军事快讯</a>'
                    };


            $posbox.mouseover(function () {
                var i = $(this).index();
                $(this).addClass("anvh").siblings().removeClass("anvh");
                var selec = $(this).attr("selec");
                if ($seledbox.is(":hidden")) {
                    $seledbox.show().css("left", 64 * i + 1).html("<div>" + anvjson[selec] + "</div>")
                } else {
                    $seledbox.stop().animate({left: 64 * i + 1}, 200, function () {
                        $("#seledbox").html("<div>" + anvjson[selec] + "</div>")
                    })
                }
            });

            $anvlfteb.mouseleave(function () {
                $seledbox.hide();
                $posbox.removeClass("anvh");
            })
        });

        function testDWR() {
            common.call({
                async: false,
                className: "com.duelsol.basicspringmvc.framework.Test",
                method: "test",
                param: {id: 123},
                callBack: function (result) {
                    alert(result);
                }
            });
        }
    </script>
</head>
<body style="height:2000px">
    <div id="topnavbar" style="display: block;">
        <div id="topnanv" style="width: 980px;">
            <div class="defu">
                <a href="#" target="_self">首页</a>
            </div>
            <div id="anvlfteb">
                <div selec="bbs" class="posbox"> <a href="#">新闻</a> <i></i></div>
                <div selec="news" class="posbox"><a href="#">信息</a> <i></i></div>
                <div selec="post" class="posbox"><a href="#">商家</a> <i></i></div>
                <div selec="youhui" class="posbox"><a href="#">社区</a> <i></i></div>
                <div selec="other" class="posbox"><a href="#">团购</a> <i></i></div>
                <div selec="store" class="posbox"><a href="#">房产</a> <i></i></div>
                <div id="seledbox" class="posiabox" style="display: none; left: 1px;"></div>
            </div>
            <div id="loginbtm">
                <a style="color:#FFF" target="_blank" href="javascript:testDWR()">免费发布信息</a>
            </div>
        </div>
    </div>
    <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
        <p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</p>
    </div>
</body>
</html>