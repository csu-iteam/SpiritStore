package com.SpiritStore.DAO;

import com.SpiritStore.Domain.AdminInfo;
import com.SpiritStore.Domain.UserInfo;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/9/19.
 */
public class AdminDAO extends DataBaseDAO {
    //判断是否存在管理员
    public boolean isAdmin(String adminName) {
        String sql = "SELECT * FROM admininfo WHERE username='" + adminName + "'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                CloseAll();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //管理员登陆
    //系统管理员成功返回1 一般管理员成功返回0 失败返回-1
    public int adminLogin(String userName, String password) {
        String sql = "SELECT * FROM admininfo WHERE username=? AND password=?";
        String sql1 = "SELECT * FROM systemadmin WHERE username=? AND password=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) return 0;
            else {
                ps = conn.prepareStatement(sql1);
                ps.setString(1, userName);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) return 1;
                else return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    //系统管理员权限操作
    //创建一般管理员
    public void createAdmin(String userName, String password) {
        String sql = "INSERT INTO admininfo (username,password) VALUES(?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.executeUpdate();
            CloseAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除管理员
    public void deleteAdmin(String userName) {
        String sql = "DELETE FROM admininfo WHERE username=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.executeUpdate();
            CloseAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void updateAdmin(String username, int level) throws SQLException {
//        String sql = "UPDATE admininfo SET level=? WHERE username=?";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, level);
//            ps.setString(2, username);
//            ps.executeUpdate();
//            CloseAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    //查询管理员信息
    public AdminInfo retrieveAdmin(String userName) {
        String sql = "SELECT * FROM admininfo WHERE username=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            AdminInfo adminInfo;
            if (rs.next()) {
                adminInfo = new AdminInfo(userName, "Invisible");
                CloseAll();
                return adminInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取所有管理员的信息列表
    public List<AdminInfo> getAllAdmin() {
        String sql = "SELECT * FROM admininfo";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<AdminInfo> list = new ArrayList<>();
            AdminInfo adminInfo = null;
            while (rs.next()) {
                adminInfo = new AdminInfo();
                adminInfo.setUserName(rs.getString(2));
                adminInfo.setPassword("Invisible");
                list.add(adminInfo);
            }
            CloseAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取所有用户的信息
    public List<UserInfo> getAllUsers() {
        String sql = "SELECT * FROM userinfo";
        List<UserInfo> list = new ArrayList<>();
        UserInfo ui = new UserInfo();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ui.setUsername(rs.getString(2));
                ui.setEmail(rs.getString(4));
                ui.setUsertel(rs.getString(5));
                ui.setRealname(rs.getString(6));
                ui.setIcn(rs.getString(7));
                ui.setLogintime(rs.getString(8));
                ui.setZipcode(rs.getString(9));
                list.add(ui);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //删除一个用户
    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM userinfo WHERE username=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //模糊查询
    public List<UserInfo> getUserByName(String userName) {
        String sql = "SELECT * FROM userinfo WHERE username LIKE '%" + userName + "%'";
        List<UserInfo> list = new ArrayList<>();
        UserInfo ui = new UserInfo();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ui.setUsername(rs.getString(2));
                ui.setEmail(rs.getString(4));
                ui.setUsertel(rs.getString(5));
                ui.setRealname(rs.getString(6));
                ui.setIcn(rs.getString(7));
                ui.setLogintime(rs.getString(8));
                ui.setZipcode(rs.getString(9));
                list.add(ui);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据身份证号获得用户
    public List<UserInfo> getUserByIcn(String icn) {
        String sql = "SELECT * FROM userinfo WHERE icn=?";
        List<UserInfo> list = new ArrayList<>();
        UserInfo ui = new UserInfo();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ui.setUsername(rs.getString(2));
                ui.setEmail(rs.getString(4));
                ui.setUsertel(rs.getString(5));
                ui.setRealname(rs.getString(6));
                ui.setIcn(rs.getString(7));
                ui.setLogintime(rs.getString(8));
                ui.setZipcode(rs.getString(9));
                list.add(ui);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
