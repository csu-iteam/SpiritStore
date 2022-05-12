package com.SpiritStore.DAO;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;

public class DataBaseDAO {
    public Connection conn=null;
    public Context ctx=null;
    public PreparedStatement ps=null;
    public ResultSet rs=null;

    public DataBaseDAO(){
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/mysqlDataSource");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CloseAll(){
        try {
            if(ctx!=null)ctx.close();
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
            if(rs!=null)rs.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
