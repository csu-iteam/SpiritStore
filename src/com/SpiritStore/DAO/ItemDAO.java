package com.SpiritStore.DAO;

import com.SpiritStore.Domain.ComInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/9/19.
 */
public class ItemDAO extends DataBaseDAO {

    //判读库中是否存在该商品
    public boolean existItem(String comName) {
        String sql = "SELECT * FROM cominfo WHERE comname=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, comName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //新增类别商品返回1 已有商品增加返回0 没有增加返回-1
    public int addItem(String comName, int count) {
        String sql1 = "INSERT INTO cominfo (comName,stocknum) VALUES (?,?)";
        String sql0 = "UPDATE cominfo SET stocknum=stocknum+? WHERE comname=?";

        try {
            if (existItem(comName)) {
                ps = conn.prepareStatement(sql0);
                ps.setInt(1, count);
                ps.setString(2, comName);
                ps.executeUpdate();
                CloseAll();
                return 0;
            } else {
                ps = conn.prepareStatement(sql1);
                ps.setString(1, comName);
                ps.setInt(2, count);
                ps.executeUpdate();
                CloseAll();
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //移除一种物品
    public boolean removeItem(String comName) {
        String sql = "DELETE FROM cominfo WHERE comname=?";
        try {
            if (existItem(comName)) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, comName);
                ps.executeUpdate();
                CloseAll();
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //从库存中取出物品，若返回false 代表库存不足
    public boolean fetchItem(String comName, int count) {

        String getStockNumSql = "SELECT * FROM cominfo WHERE comname=?";
        String fetchItemSql = "UPDATE cominfo SET stocknum=stocknum-? WHERE comname=?";
        try {
            ps = conn.prepareStatement(getStockNumSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int stockNum = rs.getInt(6);
                if ((stockNum - count) > 0) {
                    ps = conn.prepareStatement(fetchItemSql);
                    ps.setInt(1, count);
                    ps.setString(2, comName);
                    ps.executeUpdate();
                    CloseAll();
                    return true;
                } else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //得到全部物品的列表
    public List<ComInfo> getAllItem() {
        String sql = "SELECT * FROM cominfo";
        try {
            ComInfo comInfo = null;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ComInfo> list = new ArrayList<>();
            while (rs.next()) {
                comInfo = new ComInfo();
                comInfo.setComName(rs.getString(2));
                comInfo.setClassify(rs.getInt(3));
                comInfo.setPrice(rs.getInt(4));
                comInfo.setStockNum(rs.getInt(5));
                list.add(comInfo);
            }
            CloseAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //为商品分配类别
    public boolean allotClass(String comName, int classId) {
        String sql = "UPDATE cominfo SET classid=? WHERE comname=?";
        try {
            ClassifyDAO classifyDAO = new ClassifyDAO();
            if (classifyDAO.existClassify(classId) > 0) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, classId);
                ps.setString(2, comName);
                ps.executeUpdate();
                CloseAll();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //更改商品的价格
    public boolean changePrice(String comName, int price) {
        String sql = "UPDATE cominfo SET price=? WHERE comname=?";
        try {
            if (existItem(comName)) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, price);
                ps.setString(2, comName);
                ps.executeUpdate();
                CloseAll();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//At 2017/9/20 22:10 by Dell 已经拆分的获取父元素下全部物品的代码
//    public List<ComInfo> searchItemByClassId(int classid) throws SQLException {
//        String sql = "SELECT * FROM cominfo WHERE classid=?";
//        try {
//            ClassifyDAO classifyDAO = new ClassifyDAO();
//            List<Integer> listInt = new ArrayList<>();
//            List<ComInfo> comInfoList = new ArrayList<>();
//
//            listInt.add(classid);
//            listInt = classifyDAO.getChildren(0, listInt);
//            ps = conn.prepareStatement(sql);
//            ComInfo comInfo = null;
//            for (int i = 0; i < listInt.size(); i++) {
//                ps.setInt(1, listInt.get(i));
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    comInfo = new ComInfo();
//                    comInfo.setComName(rs.getString(2));
//                    comInfo.setClassify(rs.getInt(3));
//                    comInfo.setPrice(rs.getInt(4));
//                    comInfo.setStockNum(rs.getInt(5));
//                    comInfoList.add(comInfo);
//                }
//            }
//            return comInfoList;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //输入id数组，返回商品对象
    public List<ComInfo> searchItemByClassId(List<Integer> listInt) {
        String sql = "SELECT * FROM cominfo WHERE classid=?";
        List<ComInfo> comInfoList = new ArrayList<>();
        ComInfo comInfo = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < listInt.size(); i++) {
                ps.setInt(1, listInt.get(i));
                rs = ps.executeQuery();
                while (rs.next()) {
                    comInfo = new ComInfo();
                    comInfo.setComName(rs.getString(2));
                    comInfo.setClassify(rs.getInt(3));
                    comInfo.setPrice(rs.getInt(4));
                    comInfo.setStockNum(rs.getInt(5));
                    comInfoList.add(comInfo);
                }
            }
            return comInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //返回出类下面的所有物品 不包括大类中的小类下的物体
    public List<ComInfo> getItemByClassId(int classId) {
        ClassifyDAO classifyDAO = new ClassifyDAO();
        List<Integer> listInt = classifyDAO.getChild(classId);
        return searchItemByClassId(listInt);
    }

    //返回出类下面的所有物品 包括大类中的小类下的物体
    public List<ComInfo> getAllItemByClassId(int classId) {
        ClassifyDAO classifyDAO = new ClassifyDAO();
        List<Integer> listInt = new ArrayList<>();
        listInt.add(classId);
        listInt = classifyDAO.getChildren(0, listInt);
        return searchItemByClassId(listInt);
    }

    //模糊查询
    public List<ComInfo> searchItemByName(String name) {
        String sql = "SELECT * FROM cominfo WHERE comname LIKE '%" + name + "%'";
        List<ComInfo> comInfoList = new ArrayList<>();
        ComInfo comInfo = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                comInfo = new ComInfo();
                comInfo.setComName(rs.getString(2));
                comInfo.setClassify(rs.getInt(3));
                comInfo.setPrice(rs.getInt(4));
                comInfo.setStockNum(rs.getInt(5));
                comInfoList.add(comInfo);
            }
            return comInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}