/**
 * Created by Dell on 2017/9/24.
 */
var userQueryTable = new Vue({
    el:'#userQueryTable',
    data:{
        userList:[]
    }
});

function searchUserByName(userName) {
    $.ajax({
        url:"admin/searchUserByName",
        type:"GET",
        data:{
            "userName":userName
        },
        dataType:"json",
        success:function (data) {
            userQueryTable.userList=data
        }
    })
}

function searchUserByIcn(icn){
    $.ajax({
        url:"admin/searchUserByIcn",
        type:"GET",
        data:{
            "icn":icn
        },
        dataType:"json",
        success:function (data) {
            userQueryTable.userList=data
        }
    })
}


