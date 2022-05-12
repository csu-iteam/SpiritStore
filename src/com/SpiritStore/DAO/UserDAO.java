package com.SpiritStore.DAO;

import com.SpiritStore.Domain.UserInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DataBaseDAO{
    public boolean UserLogin(String usn, String psw){
        String sql = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,usn);
            ps.setString(2,psw);
            rs = ps.executeQuery();
            if(rs.next()) {
                CloseAll();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return false;
    }

    public void LogintimeUpdate(String usn,String logintime){
        String sql = "UPDATE userinfo SET logintime = ? WHERE username = ?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,logintime);
            ps.setString(2,usn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
    }

    public boolean UserRegister(String usn, String psw,String email,String tel,String realname,String icn,String logintime,String zipcode) {
        String sql = "INSERT INTO userinfo (username,password,email,usertel,realname,icn,logintime,zipcode) VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,usn);
            ps.setString(2,psw);
            ps.setString(3,email);
            ps.setString(4,tel);
            ps.setString(5,realname);
            ps.setString(6,icn);
            ps.setString(7,logintime);
            ps.setString(8,zipcode);
            int r=ps.executeUpdate();
            CloseAll();
            if(r>0)return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return false;
    }

    public UserInfo GetUserInfo(String username){
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        UserInfo ui=new UserInfo();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            rs.next();
            ui.setUsername(rs.getString(2));
            ui.setEmail(rs.getString(4));
            ui.setUsertel(rs.getString(5));
            ui.setRealname(rs.getString(6));
            ui.setIcn(rs.getString(7));
            ui.setLogintime(rs.getString(8));
            ui.setZipcode(rs.getString(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return ui;
    }

    public int AlterPwd(String usn,String oldpwd,String newpwd){
        String sql = "SELECT * FROM userinfo WHERE username=? AND password = ?";
        int i=0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usn);
            ps.setString(2, oldpwd);
            rs = ps.executeQuery();
            if(rs.next()){
                sql = "UPDATE userinfo SET password = ? WHERE username = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, newpwd);
                ps.setString(2, usn);
                if(ps.executeUpdate()==1)i=1;//修改成功
            }
            else i=2;//旧密码错误
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return i;//未知错误
    }

    public void AlterInfo(String usn,String email,String tel,String realname,String icn,String zipcode){
        String sql = "UPDATE userinfo SET email = ?, usertel=?, realname=?, icn=?, zipcode=? WHERE username = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, tel);
            ps.setString(3, realname);
            ps.setString(4, icn);
            ps.setString(5, zipcode);
            ps.setString(6, usn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
    }

    public int getUserId(String usn){
        String sql="SELECT id FROM userinfo WHERE username=?";
        int id=0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usn);
            rs = ps.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return id;
    }

    public boolean infoIsTrue(String usn,String email,String realname,String icn){
        String sql="SELECT * FROM userinfo WHERE username=? AND email=? AND realname=? AND icn=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usn);
            ps.setString(2, email);
            ps.setString(3, realname);
            ps.setString(4, icn);
            rs = ps.executeQuery();
            if(rs.next()){
                CloseAll();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
        return false;
    }

    public void EmailPwd(String usn,String newpwd){
        String sql = "UPDATE userinfo SET password = ? WHERE username = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, newpwd);
            ps.setString(2, usn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseAll();
    }
}
