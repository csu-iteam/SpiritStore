<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/9/22
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
<script src="jquery-3.1.0.min.js" rel="script"></script>
<script>
    function getChild(parentId) {
        $.ajax({
            url:"classify/getChildInfo",
            data:{parentId: parentId},
            type:"GET",
            dataType:"json",
            success:function (data) {

            }
        })
    }

    function addClass(parentId,name) {
        $.ajax({
            url:"classify/addClass",
            data:{parentId:parentId,name:name},
            type:"GET",
            dataType:"json",
            success:function(data){

            }
        })
    }
    
    function deleteClassById(classId) {
        $.ajax({
            url:"classify/deleteClassById",
            data:{
                classId:classId
            },
            type:"GET",
            dataType:"json",
            success:function (data) {
                
            }
        })
    }

    function updateParent(parentId,childId) {
        $.ajax({
            url:"classify/updateParent",
            data:{
                parentId:parentId,
                childId:childId
            },
            type:"GET",
            dataType:"json",
            success:function (data) {

            }
        })
    }
</script>
</html>
