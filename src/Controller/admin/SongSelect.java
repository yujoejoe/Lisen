package Controller.admin;

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
 * Created by user on 2019/12/18.
 */
@WebServlet(name = "SongSelect", urlPatterns = "/admin/songSelect")
public class SongSelect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        SongServiceDAOImp songSDI = new SongServiceDAOImp();
        Song song = new Song();

        if(page != null && !page.equals("") && limit != null && !limit.equals("")){
            song.setLimit("limit " + page + ',' + limit);
        }


        ArrayList<Song> result = songSDI.select(song);

        boolean success = result.size() != 0;

        String msg = result.size() != 0 ? "查询成功！" : "查询失败！";

        int counts = result.size();

        JsonData jsonData = new JsonData(success, msg, counts, result);

        request.setAttribute("jsonData", jsonData);

        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }
}