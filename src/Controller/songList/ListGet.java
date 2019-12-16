package Controller.songList;

import POJO.JsonData;
import POJO.songList.Song;
import ServiceDAO.songList.SongServiceDAOImp;

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
 * Created by user on 2019/12/15.
 */
@WebServlet(name = "ListGet", urlPatterns = "/ListGet")
public class ListGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String listId = request.getParameter("listId");
        String page = request.getParameter("page");

        Song song = new Song();
        SongServiceDAOImp sDI = new SongServiceDAOImp();
        ArrayList<Song> result = null;
        boolean success = false;
        String msg = "";
        int counts = 0;

        String condition = "";
        if(listId != null && !listId.equals("")){
            condition += " and songlist.id = " + listId;
        }
        song.setCondition(condition);

        if(page != null && !page.equals("")){
            int p = Integer.parseInt(page);
            int size = 10;
            song.setLimit(" limit " + (p - 1) + "," + size);
        }

        result = sDI.selectList(song);
        success = result.size() != 0;
        msg = result.size() != 0 ? "查询成功！" : "查询失败！";
        counts = result.size();

        JsonData jsonData = new JsonData(success, msg, counts, result);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }
}
