<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/9/22
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage The Orders</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.4.1/vue.js"></script>
    <script src="https://unpkg.com/axios@0.12.0/dist/axios.min.js"></script>
    <script src="https://unpkg.com/lodash@4.13.1/lodash.min.js"></script>
</head>
<body>
    <form action="order/setOrderStatus" method="post">
        <input name="status" type="text">
        <button type="submit">修改</button>
    </form>
</body>
<script src="jquery-3.1.0.min.js" rel="script"></script>
<script>
    function getOrderItem() {
        $.ajax({
            url:"order/getAllItem?orderId=",
            data: 0,
            type:"GET",
            dataType:"json",
            success:function (data) {
                //data为json格式的对象列表
            }
        })
    }

    function getOrder() {
        $.ajax({
            url:"order/getAllOrder",
            data:0,
            type:"GET",
            dataType:"json",
            success:function(data){
                //data为json格式的对象列表
            }
        })
    }
</script>
</html>
