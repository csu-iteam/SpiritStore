package com.SpiritStore.DAO;

import com.SpiritStore.Domain.OrderItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/9/20.
 */
public class OrderItemDAO extends DataBaseDAO{
    //添加订单细节到订单中
//    public boolean addItemToOrder(int comId,int num,int orderId){
//        String sql="INSERT INTO orderitem (comid,num,orderid) VALUES(?,?,?)";
//        try{
//            ps=conn.prepareStatement(sql);
//            ps.setInt(1,comId);
//            ps.setInt(2,num);
//            ps.setInt(3,orderId);
//            ps.executeUpdate();
//            CloseAll();
//            return true;
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //从订单中删除商品
//    public boolean removeItem(int comId){
//        String sql="DELETE FROM orderitem WHERE comid=?";
//        try {
//            ps=conn.prepareStatement(sql);
//            ps.setInt(1,comId);
//            ps.executeUpdate();
//            CloseAll();
//            return true;
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //增加订单中的商品数量
//    public boolean addItemNum(int comId,int num){
//        String sql="UPDATE orderitem SET num=num+? WHERE comid=?";
//        try{
//            ps=conn.prepareStatement(sql);
//            ps.setInt(1,num);
//            ps.setInt(2,comId);
//            ps.executeUpdate();
//            CloseAll();
//            return true;
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //减少订单中的商品数量
//    public boolean fetchItemNum(int comId,int num){
//        String sqlGetNum="SELECT num FROM orderitem WHERE comid=?";
//        String sqlSetNum="UPDATE orderitem SET num=num-? WHERE comid=?";
//        try{
//            ps=conn.prepareStatement(sqlGetNum);
//            ps.setInt(1,comId);
//            rs=ps.executeQuery();
//            if(rs.next()){
//                int number=rs.getInt(1);
//                if(number>num){
//                    ps=conn.prepareStatement(sqlSetNum);
//                    ps.setInt(1,num);
//                    ps.setInt(2,comId);
//                    ps.executeUpdate();
//                    CloseAll();
//                    return true;
//                }else if(number==num){
//                    removeItem(comId);
//                }else return false;
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

    public List<OrderItem> getAllItem(int orderId){
        String sql="SELECT * FROM orderitem WHERE orderid=?";
        List<OrderItem> list =new ArrayList<>();
        OrderItem temp=new OrderItem();
        try{
            ps=conn.prepareStatement(sql);
            ps.setInt(1,orderId);
            rs=ps.executeQuery();
            while (rs.next()){
                temp.setComid(rs.getInt(2));
                temp.setNum(rs.getInt(3));
                list.add(temp);
            }
            CloseAll();
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
