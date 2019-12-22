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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String songname = request.getParameter("songname");
        String singer = request.getParameter("singer");
        String album = request.getParameter("album");

        SongServiceDAOImp songSDI = new SongServiceDAOImp();
        Song song = new Song();

        // 添加条件
        String condition = "";
        if(songname != null && !songname.equals("")){
            condition += " and song.name like '%" + songname + "%'";
        }

        if(singer != null && !singer.equals("")){
            condition += " and singer.name like '%" + singer + "%'";
        }

        if(album != null && !album.equals("")){
            condition += " and album.name like '%" + album + "%'";
        }


        song.setCondition(condition);

        if(page != null && !page.equals("") && limit != null && !limit.equals("")){
            int p = Integer.parseInt(page);
            int l = Integer.parseInt(limit);
            song.setLimit("limit " + (p - 1) * l + ',' + l);
        }

        // 返回给前端的数据
        ArrayList<Song> result = songSDI.select(song);
        boolean success = result.size() != 0;
        String msg = result.size() != 0 ? "查询成功！" : "查询失败！";
        int counts = (songname != null && !songname.equals("")) ? result.size() : songSDI.count(song);

        JsonData jsonData = new JsonData(success, msg, counts, result);
        request.setAttribute("jsonData", jsonData);

        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }
}
