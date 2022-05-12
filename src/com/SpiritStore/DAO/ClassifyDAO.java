package com.SpiritStore.DAO;

import com.SpiritStore.Domain.Classify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/9/20.
 */
public class ClassifyDAO extends DataBaseDAO {

    //判断分类是否存在 返回大于0的数为类型id 返回-1为没有找到
    public int existClassify(int id) {
        String sql = "SELECT id FROM classify WHERE id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return id;
            } else return -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //增加一个子类到父类中
    public boolean addClass(int parentId, String name) {
        String sql = "INSERT INTO classify (name,parentid) VALUES (?,?)";
        try {
            if (existClassify(parentId) > 0) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, parentId);
                ps.executeUpdate();
                CloseAll();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //获取一个父分类的所有子类
    public List<Integer> getChildren(int point, List<Integer> list) {
        String sql = "SELECT id FROM classify WHERE parentid=?";
        try {
            ps = conn.prepareStatement(sql);
            while (point < list.size()) {
                ps.setInt(1, list.get(point));
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }
                point++;
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取一个父分类的所有一级子类id
    public List<Integer> getChild(int parentId) {
        String sqlGetChildren = "SELECT * FROM classify WHERE parentid=?";
        try {
            List<Integer> list = new ArrayList<>();
            ps = conn.prepareStatement(sqlGetChildren);
            ps.setInt(1, parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取一个父分类的所有一级子类对象
    public List<Classify> getChildInfo(int parentId) {
        String sqlGetChildren = "SELECT * FROM classify WHERE parentid=?";
        try {
            List<Classify> list = new ArrayList<>();
            Classify classify = new Classify();
            ps = conn.prepareStatement(sqlGetChildren);
            ps.setInt(1, parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                classify.setId(rs.getInt(1));
                classify.setName(rs.getString(2));
                classify.setParentId(parentId);
                list.add(classify);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //更新节点的父节点
    public boolean updateParent(int parentId, int childId) {
        String sql = "UPDATE classify SET parentid=? WHERE id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, parentId);
            ps.setInt(2, childId);
            ps.executeUpdate();
            CloseAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //得到节点的父节点id -1代表没有获得父节点 根节点的父节点仍为根节点
    public int getParentId(int childId) {
        if (childId == 0) return 0;
        String sqlGetParent = "SELECT parentid FROM classify WHERE id =?";
        try {
            ps = conn.prepareStatement(sqlGetParent);
            ps.setInt(1, childId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //通过分类节点id删除一个分类节点，将子节点保留接入上一级父节点上
    public boolean deleteClassById(int classId) {
        String sqlDeleteSelf = "DELETE FROM classify WHERE id=?";
        try {
            List<Integer> list = getChild(classId);
            int parentId = getParentId(classId);
            for (int i = 0; i < list.size(); i++) {
                updateParent(parentId, list.get(i));
            }
            ps = conn.prepareStatement(sqlDeleteSelf);
            ps.setInt(1, classId);
            ps.executeUpdate();
            CloseAll();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询所有父类
    public List<Classify> getAllMajor() {
        String sql = "SELECT * FROM classify WHERE parentid=0";
        List<Classify> list = new ArrayList<>();
        Classify classify = new Classify();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                classify.setId(rs.getInt(1));
                classify.setName(rs.getString(2));
                list.add(classify);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过父节点id和名称模糊查询
    public List<Classify> searchClassify(int parentId, String name) {
        String sql = "SELECT * FROM classify WHERE parentid=? and name LIKE '%" + name + "%'";
        List<Classify> list = new ArrayList<>();
        Classify classify = new Classify();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, parentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                classify.setId(rs.getInt(1));
                classify.setName(rs.getString(2));
                classify.setParentId(parentId);
                list.add(classify);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
