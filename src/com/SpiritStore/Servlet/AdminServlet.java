package com.SpiritStore.Servlet;

import com.SpiritStore.DAO.AdminDAO;
import com.SpiritStore.Domain.AdminInfo;
import com.SpiritStore.Domain.UserInfo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dell on 2017/9/22.
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    private String userName;
    private String password;
    private String icn;
    private static final String adminManage = "adminManage.jsp";
    private AdminDAO adminDAO = new AdminDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        //获取下级管理员的表
        AdminDAO adminDAO = new AdminDAO();
        List<AdminInfo> adminList = adminDAO.getAllAdmin();
        session.setAttribute("adminList", adminList);
        String uri = request.getRequestURI();
        String[] temp = uri.split("/");
        switch (temp[2]) {
            case "isAdmin": {                //判断管理员姓名是否存在
                userName = request.getParameter("userName");
                boolean sign = adminDAO.isAdmin(userName);
                out.print(sign);
                break;
            }
            case "getAllUsers":      //获得所有用户对象
                List<UserInfo> list = adminDAO.getAllUsers();
                out.print(gson.toJson(list));
                break;
            case "createAdmin":      //创建管理员
                userName = request.getParameter("userName");
                password = request.getParameter("password");
                adminDAO.createAdmin(userName, password);
                out.print(true);
                break;
            case "deleteAdmin":      //删除管理员
                userName = request.getParameter("userName");
                adminDAO.deleteAdmin(userName);
                out.print(true);
                break;
            case "getAllAdmin":      //获取所有管理员
                out.print(gson.toJson(adminDAO.getAllAdmin()));
                break;
            case "retrieveAdmin":    //根据用户名查询管理员
                userName = request.getParameter("userName");
                AdminInfo adminInfo = adminDAO.retrieveAdmin(userName);
                out.print(gson.toJson(adminInfo));
                break;
            case "deleteUser": {         //删除用户
                userName = request.getParameter("userName");
                boolean sign = adminDAO.deleteUser(userName);
                out.print(sign);
                break;
            }
            case "searchUserByName":    //通过用户名查询用户
                userName = request.getParameter("userName");
                out.print(gson.toJson(adminDAO.getUserByName(userName)));
                break;
            case "searchUserByIcn":     //通过身份证查询用户
                icn = request.getParameter("icn");
                out.print(gson.toJson(adminDAO.getUserByIcn(icn)));
                break;
        }
    }
}
