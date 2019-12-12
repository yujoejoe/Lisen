package Controller.songList;

import POJO.JsonData;
import POJO.SongList;
import ServiceDAO.songList.SongListServiceDAOImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/12.
 */
@WebServlet(name = "SongListGet", urlPatterns = "/SongListGet")
public class SongListGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 前端参数
        String name = request.getParameter("name");         // 歌单名字
        String style = request.getParameter("style");
        String theme = request.getParameter("theme");
        String order = request.getParameter("order");       // 排序依据
        String author = request.getParameter("author");


        SongList sList = new SongList();
        SongListServiceDAOImp slSDI = new SongListServiceDAOImp();
        ArrayList<SongList> result = null;

        // 添加条件
        String condition = "";
        if(name != null && !name.equals("")){
            condition += " songlist.name = '" + name + "'";
        }
        if(style != null && !style.equals("")){
            condition += " and style.name = '" + style + "'";
        }
        if(theme != null && !theme.equals("")){
            condition += " and theme.name = '" + theme + "'";
        }
        if(author != null && !author.equals("")){
            condition += " and user.name = '" + author + "'";
        }
        sList.setCondition(condition);

        // 排序
        if(order != null && order.equals("1")){
            sList.setOrderBy(" order by songlist.play desc");
        }else{
            sList.setOrderBy(" order by songlist.date desc");
        }

        // 分页
        sList.setLimit(" limit 0, 10");

        // 查询
        result = slSDI.select(sList);

        // 返回前端的数据
        boolean success = result.size() != 0;
        String msg = result.size() != 0 ? "查询成功！" : "查询失败！";
        int counts = result.size();

        JsonData jsonData = new JsonData(success, msg, counts, result);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }
}
