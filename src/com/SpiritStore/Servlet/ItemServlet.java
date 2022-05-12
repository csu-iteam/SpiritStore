package com.SpiritStore.Servlet;

import com.SpiritStore.DAO.ItemDAO;
import com.SpiritStore.Domain.ComInfo;
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
@WebServlet(name = "ItemServlet")
public class ItemServlet extends HttpServlet {
    private ItemDAO itemDAO = new ItemDAO();
    private String comName;
    private int count;
    private int classId;
    private int price;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String[] temp = uri.split("/");
        switch (temp[2]) {
            case "searchItemByName": //模糊查询物品
                comName = request.getParameter("comName");
                List<ComInfo> comInfoList = itemDAO.searchItemByName(comName);
                String result = gson.toJson(comInfoList);
                out.print(result);
                break;
            case "addItem": {//增加商品
                comName = request.getParameter("comName");
                count = Integer.parseInt(request.getParameter("count"));
                int sign = itemDAO.addItem(comName, count);
                out.print(sign);
                break;
            }
            case "removeItem": {//删除物品
                comName = request.getParameter("comName");
                boolean sign = itemDAO.removeItem(comName);
                out.print(sign);
                break;
            }
            case "fetchItem": {//取出物品
                comName = request.getParameter("comName");
                count = Integer.parseInt(request.getParameter("count"));
                boolean sign = itemDAO.fetchItem(comName, count);
                out.print(sign);
                break;
            }
            case "allotClass": {//为物品分配类别
                comName = request.getParameter("comName");
                classId = Integer.parseInt(request.getParameter("classId"));
                boolean sign = itemDAO.allotClass(comName, classId);
                out.print(sign);
                break;
            }
            case "changePrice": {//修改物品的价格
                comName = request.getParameter("comName");
                price = Integer.parseInt(request.getParameter("price"));
                boolean sign = itemDAO.changePrice(comName, price);
                out.print(sign);
                break;
            }
            case "getItemByClassId": //得到某一列下的所有物品
                classId = Integer.parseInt(request.getParameter("classId"));
                List<ComInfo> list = itemDAO.getItemByClassId(classId);
                out.print(gson.toJson(list));
                break;
            case "getAllItem": //获取全部商品
                List<ComInfo> itemList = itemDAO.getAllItem();
                out.print(gson.toJson(itemList));
                break;
        }
    }
}
