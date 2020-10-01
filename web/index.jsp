<%--
  Created by IntelliJ IDEA.
  User: XBDELL V7570
  Date: 2020/9/29
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <title>省市联动</title>
</head>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    function a(){
        $.ajax({
            url:"query",
            dataType:"json",
            success:function (resp) {
                $("#provice").empty()
                $.each(resp,function (i,n) {
                    //获取selectdom对象

                    //0: {id: 1, name: "河北", jiancheng: "冀", shenghui: "石家庄"}
                    //   1: {id: 2, name: "山西", jiancheng: "晋", shenghui: "太原市"}
                    //   2: {id: 3, name: "内蒙古", jiancheng: "蒙", shenghui: "呼和浩特市 "}
                    //   3: {id: 4, name: "辽宁", jiancheng: "辽", shenghui: "沈阳"}
                    //   4: {id: 5, name: "江苏", jiancheng: "苏", shenghui: "南京"}
                    //   5: {id: 6, name: "浙江", jiancheng: "浙", shenghui: "杭州"}
                    //   6: {id: 7, name: "安徽", jiancheng: "皖", shenghui: "合肥"}
                    //   7: {id: 8, name: "福建", jiancheng: "闽", shenghui: "福州"}
                    //   8: {id: 9, name: "江西", jiancheng: "赣", shenghui: "南昌"}
                    $("#provice").append( "<option value="+n.id+">"+n.name+"</option>")
                })
            }

        })
    }
    $(function () {
        // $(function () 在页面dom对象加载成功后执行的函数
        a()
        //绑定事件
        $("#btnid").click(function (a) {
                //做ajax请求，使用jquery的$.ajax
            a()
            })
        //给省份的select绑定一个change事件，当select内容发生变化时，触发事件
        $("#provice").change(function () {
                //获取选中的列表框值
            var obj = $("#provice>option:selected");
                // alert("select 的change事件"+obj.val() +"========"+ obj.text())
            var pid = obj.val();//1,2.3
            //做一个ajax请求\

           $.get("citys",{pid:pid},function (resp) {
               $("#city").empty()
               $.each(resp,function (i,n) {
                   $("#city").append("<option value="+n.id+">"+n.name+"</option>")

               })
           },"json")
        })
    })
</script>
<script>

</script>
<body>
<div>
    <h1>省市联动效果</h1>
    <table border="1">
        <tr>
            <td>
                省份
                <select id="provice">

                    <option value="0">
                        请选择
                    </option>
                </select>
                <button value="button" id="btnid">加载数据</button>
            </td>
        </tr>
        <tr>
            <td>
                城市
                <select id="city">
                    城市
                    <option value="0">
                        请选择
                    </option>
                </select>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
