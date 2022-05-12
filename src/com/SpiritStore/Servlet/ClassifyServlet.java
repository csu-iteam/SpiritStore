package com.SpiritStore.Servlet;

import com.SpiritStore.DAO.ClassifyDAO;
import com.SpiritStore.Domain.Classify;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/9/22.
 */
@WebServlet(name = "ClassifyServlet")
public class ClassifyServlet extends HttpServlet {
    private int parentId;
    private String name;
    private int classId;
    ClassifyDAO classifyDAO = new ClassifyDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        String[] temp = uri.split("/");
        switch (temp[2]) {
            case "addClass": {//增加分类节点
                parentId = Integer.parseInt(request.getParameter("parentId"));
                name = request.getParameter("name");
                boolean sign = classifyDAO.addClass(parentId, name);
                out.print(sign);
                break;
            }
            case "getChildInfo": {//获取父节点下的一级子节点信息
                parentId = Integer.parseInt(request.getParameter("parentId"));
                List<Classify> list = classifyDAO.getChildInfo(parentId);
                String result = gson.toJson(list);
                out.print(result);
                break;
            }
            case "deleteClassById": {//删除某一节点将子节点加入父节点中
                classId = Integer.parseInt(request.getParameter("classId"));
                boolean sign = classifyDAO.deleteClassById(classId);
                out.print(sign);
                break;
            }
            case "updateParent": {//更新父节点
                parentId = Integer.parseInt(request.getParameter("parentId"));
                classId = Integer.parseInt(request.getParameter("childId"));
                boolean sign = classifyDAO.updateParent(parentId, classId);
                out.print(sign);
                break;
            }
            case "getAllMajor": {//获得所有的父节点
                List<Classify> list = classifyDAO.getAllMajor();
                out.print(gson.toJson(list));
                break;
            }
            case "searchClassify": {
                name = request.getParameter("name");
                parentId = Integer.parseInt(request.getParameter("parentId"));
                List<Classify> list=classifyDAO.searchClassify(parentId,name);
                out.print(gson.toJson(list));
                break;
            }
        }
    }
}
