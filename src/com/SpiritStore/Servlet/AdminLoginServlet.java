package com.SpiritStore.Servlet;

import com.SpiritStore.DAO.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dell on 2017/9/21.
 */
@WebServlet(name = "AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private String userName;
    private String password;
    private static final String adminManage="AdminManage.jsp";
    private static final String adminLogin="AdminLogin.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userName=request.getParameter("userName");
        password=request.getParameter("password");
        AdminDAO adminDAO=new AdminDAO();


        //判断管理员的权限类别
        if(adminDAO.adminLogin(userName,password)==0){
            HttpSession session=request.getSession();
            session.setAttribute("access",0);
            response.sendRedirect(adminManage);
        }else if(adminDAO.adminLogin(userName,password)==1){
            HttpSession session=request.getSession();
            session.setAttribute("access",1);
            response.sendRedirect(adminManage);
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("access",-1);
            response.sendRedirect(adminLogin);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
