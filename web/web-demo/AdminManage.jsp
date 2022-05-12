<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/9/21
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入超级管理员系统</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.4.1/vue.js"></script>
    <script src="https://unpkg.com/axios@0.12.0/dist/axios.min.js"></script>
    <script src="https://unpkg.com/lodash@4.13.1/lodash.min.js"></script>
</head>
<body>
<%--<c:if test="${sessionScope.access==1}">--%>
<form action="admin/createAdmin" method="post" id="addAdmin">
    <label>添加管理员</label>
    <label>用户名</label><input type="text" name="userName">
    <label>密码</label><input type="password" id="password" name="password">
    <label>重复密码</label><input type="password" id="confirm">
    <button onclick="submitAddAdmin()">
        添加
    </button>
</form>
<%--</c:if>--%>

</body>
<script>
    function submitAddAdmin() {
        var password = document.getElementById("password");
        var password1 = password.value();
        var confirm = document.getElementById("confirm");
        var password2 = confirm.value();
        if (password1 == password2) {
            document.getElementById("addAdmin").submit();
        } else alert("两次输入密码不一致")
    }

    function isAdmin(userName) {
        $.ajax({
            url:"admin/isAdmin",
            type:"GET",
            data:{
                userName:userName
            },
            dataType:"json",
            success:function (data) {

            }
        })
    }
</script>
</html>

