var orderTable=new Vue({
    el:'#orderTable',
    data:{
        orderList:[]
    }
});

function getAllOrder() {
    $.ajax({
        type:"GET",
        url:"order/getAllOrder",
        data:{
            
        },
        dataType:"json",
        success:function (data) {
            orderTable.orderList=data;
        }
    })
};

function getOrderByStatus(status){
    $.ajax({
        url:"order/getOrderByStatus",
        type:"GET",
        data:{
            status:status
        },
        dataType:"json",
        success:function (data) {
            orderTable.orderList=data;
        }
    })
}

function getOrderById(orderNum) {
    $.ajax({
        url:"order/getOrderById",
        type:"GET",
        data:{
            orderNum:orderNum
        },
        dataType:"json",
        success:function (data) {
            orderTable.orderList=data;
        }
    })
}
