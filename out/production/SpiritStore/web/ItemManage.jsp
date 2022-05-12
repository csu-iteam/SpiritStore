<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/9/22
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Your Items</title>
</head>
<body>

</body>
<script src="jquery-3.1.0.min.js" rel="script"></script>
<script>
function searchItemByName(comName) {
    $.ajax({
        url:"item/searchItemByName",
        type:"GET",
        date:{comName:comName},
        dataType:"json",
        success:function (data) {
            
        }
    })
}
function addItem(comName,count) {
    $.ajax({
        url:"item/addItem",
        type:"GET",
        date:{comName:comName,count:count},
        dataType:"json",
        success:function (data) {

        }
    })
}
function removeItem(comName) {
    $.ajax({
        url:"item/removeItem",
        type:"GET",
        date:{comName:comName},
        dataType:"json",
        success:function (data) {

        }
    })
}
function fetchItem(comName,count) {
    $.ajax({
        url:"item/fetchItem",
        type:"GET",
        date:{comName:comName,count:count},
        dataType:"json",
        success:function (data) {

        }
    })
}
function allotClass(comName,classId) {
    $.ajax({
        url:"item/allotClass",
        type:"GET",
        date:{comName:comName,classId:classId},
        dataType:"json",
        success:function (data) {

        }
    })
}
function changePrice(comName,price) {
    $.ajax({
        url:"item/changePrice",
        type:"GET",
        date:{comName:comName,price:price},
        dataType:"json",
        success:function (data) {

        }
    })
}
</script>
</html>
