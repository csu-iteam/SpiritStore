package com.SpiritStore.Servlet;

import com.SpiritStore.DAO.OrderDAO;
import com.SpiritStore.DAO.OrderItemDAO;
import com.SpiritStore.Domain.OrderInfo;
import com.SpiritStore.Domain.OrderItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dell on 2017/9/22.
 */
@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    private int orderId;
    private String status;
    Gson gson=new Gson();
    private static final String orderManage="OrderManage.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri=request.getRequestURI();
        String[] temp=uri.split("/");
        if(temp[2].equals("setOrderStatus")){
            OrderDAO orderDAO=new OrderDAO();
            orderId=Integer.parseInt(request.getParameter("orderId"));
            status=request.getParameter("status");
            orderDAO.setOrderStatus(orderId,status);
            response.sendRedirect(orderManage);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String[] temp = uri.split("/");
        switch (temp[2]) {
            case "getAllItem": {//获取一张订单上的所有物品
                OrderItemDAO orderItemDAO = new OrderItemDAO();
                List<OrderItem> list = orderItemDAO.getAllItem(Integer.parseInt(request.getParameter("orderId")));
                Gson gson = new Gson();
                String result = gson.toJson(list);
                //System.out.println(result);
                out.print(result);
                break;
            }
            case "getAllOrder": {//获取所有的订单
                OrderDAO orderDAO = new OrderDAO();
                List<OrderInfo> list = orderDAO.getAllOrder();
                Gson gson = new Gson();
                String result = gson.toJson(list);
                //System.out.print(result);
                out.print(result);
                break;
            }
            case "orderSum": {
                OrderDAO orderDAO = new OrderDAO();
                status = request.getParameter("status");
                int sum = orderDAO.orderSum(status);
                out.print(sum);
                break;
            }
            case "getOrderByStatus":{
                OrderDAO orderDAO=new OrderDAO();
                status=request.getParameter("status");
                List<OrderInfo>list =orderDAO.getOrderByStatus(status);
                String result=gson.toJson(list);
                out.print(result);
            }case "getOrderById":{
                OrderDAO orderDAO=new OrderDAO();
                orderId=Integer.parseInt(request.getParameter("orderId"));
                List<OrderInfo>list =orderDAO.getOrderBy(orderId);
                String result=gson.toJson(list);
                out.print(result);
            }
        }
    }
}
