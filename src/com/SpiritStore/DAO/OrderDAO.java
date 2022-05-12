package com.SpiritStore.DAO;

import com.SpiritStore.Domain.OrderInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//import com.SpiritStore.Domain.UserInfo;

/**
 * Created by Dell on 2017/9/20.
 */
public class OrderDAO extends DataBaseDAO {
    //创建订单
//    public boolean createOrderInfo(int orderNum,UserInfo userInfo,String address,String consignee,int money,String contel,String shipMethod){
//        String sql="INSERT INTO orderinfo (orderName,userId,orderId,money,zipcode,address,consignee,contel,shipmethod,orderStatus) VALUES (?,?,?,?,?,?,?,?,?,?)";
//        try{
//            UserDAO userDAO=new UserDAO();
//            ps=conn.prepareStatement(sql);
//            ps.setInt(1,orderNum);
//            //ps.setInt(2,userDAO.getUserId(userInfo.getUsername()));
//            ps.setInt(3,money);
//            ps.setString(4,userInfo.getZipcode());
//            ps.setString(5,address);
//            ps.setString(6,consignee);
//            ps.setString(7,contel);
//            ps.setString(8,shipMethod);
//            ps.setString(9,"下订单");
//            ps.executeUpdate();
//            CloseAll();
//            return true;
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
    //修改订单

    //设置订单状态
    public boolean setOrderStatus(int orderId, String status) {
        String sql = "UPDATE orderinfo SET orderstatus=? WHERE ordernum=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            CloseAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //获取全部订单
    public List<OrderInfo> getAllOrder() {
        String sql = "SELECT * FROM orderinfo";
        List<OrderInfo> list = new ArrayList<>();
        OrderInfo temp = new OrderInfo();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                temp.setOrderNum(rs.getInt(2));
                temp.setUserId(rs.getInt(3));
                temp.setMoney(rs.getInt(4));
                temp.setZipCode(rs.getString(5));
                temp.setAddress(rs.getString(6));
                temp.setConsignee(rs.getString(7));
                temp.setConTel(rs.getString(8));
                temp.setShipMethod(rs.getString(9));
                temp.setOrderStatus(rs.getString(10));
                list.add(temp);
            }
            CloseAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取订单金额
    public int orderSum(String status) {
        String sql = "SELECT money FROM orderinfo WHERE orderstatus=?";
        try {
            int sum = 0;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sum += rs.getInt(1);
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //根据状态获得订单
    public List<OrderInfo> getOrderByStatus(String status) {
        String sql = "SELECT * FROM orderinfo WHERE orderstatus=?";
        try {
            List<OrderInfo> list = new ArrayList<>();
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            OrderInfo temp = null;
            while (rs.next()) {
                temp = new OrderInfo();
                temp.setOrderNum(rs.getInt(2));
                temp.setUserId(rs.getInt(3));
                temp.setMoney(rs.getInt(4));
                temp.setZipCode(rs.getString(5));
                temp.setAddress(rs.getString(6));
                temp.setConsignee(rs.getString(7));
                temp.setConTel(rs.getString(8));
                temp.setShipMethod(rs.getString(9));
                temp.setOrderStatus(rs.getString(10));
                list.add(temp);
            }
            CloseAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return null;
    }

    //根据订单号查询订单
    public List<OrderInfo> getOrderBy(int orderId) {
        String sql = "SELECT * FROM orderinfo WHERE ordernumber=?";
        try {
            List<OrderInfo> list = new ArrayList<>();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            OrderInfo temp = null;
            while (rs.next()) {
                temp = new OrderInfo();
                temp.setOrderNum(rs.getInt(2));
                temp.setUserId(rs.getInt(3));
                temp.setMoney(rs.getInt(4));
                temp.setZipCode(rs.getString(5));
                temp.setAddress(rs.getString(6));
                temp.setConsignee(rs.getString(7));
                temp.setConTel(rs.getString(8));
                temp.setShipMethod(rs.getString(9));
                temp.setOrderStatus(rs.getString(10));
                list.add(temp);
            }
            CloseAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return null;
    }
}
