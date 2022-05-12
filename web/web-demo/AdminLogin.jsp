<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/9/21
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入管理员系统</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.4.1/vue.js"></script>
    <script src="https://unpkg.com/axios@0.12.0/dist/axios.min.js"></script>
    <script src="https://unpkg.com/lodash@4.13.1/lodash.min.js"></script>
</head>
<body>
<form action="login" method="posts"></form>
<label>用户名</label>
<input type="text" id="userName" name="userName">
<label>密码</label>
<input type="password" id="password" name="password">
<button type="submit">
    登录
</button>

</body>
</html>
