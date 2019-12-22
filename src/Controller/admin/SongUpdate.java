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

/**
 * Created by user on 2019/12/22.
 */
@WebServlet(name = "SongUpdate", urlPatterns = "/admin/songUpdate")
public class SongUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        /*================ 前端参数 ================*/
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String name = request.getParameter("song");
        String style = request.getParameter("style");
        String album = request.getParameter("album");
        String status = request.getParameter("status");
        String singer = request.getParameter("singer");
        String format = request.getParameter("format");
        String duration = request.getParameter("duration");

        Song song = new Song();

        // 1
        if(id != null && !id.equals("")){
            song.setId(Integer.parseInt(id));
        }else{
            song.setId(-1);
        }
        // 2
        if(status != null && !status.equals("")){
            song.setStatus(Integer.parseInt(status));
        }else{
            song.setStatus(-1);
        }
        // 3
        if(name != null && !name.equals("")){
            song.setSong(name);
        }else{
            song.setSong("");
        }
        // 4
        if(date != null && !date.equals("")){
            song.setDate(date);
        }else{
            song.setDate("");
        }
        // 5
        if(style != null && !style.equals("")){
            song.setStyle(style);
        }else{
            song.setStyle("");
        }
        // 6
        if(album != null && !album.equals("")){
            song.setAlbum(album);
        }else{
            song.setAlbum("");
        }
        // 7
        if(singer != null && !singer.equals("")){
            song.setSinger(singer);
        }else{
            song.setSinger("");
        }
        // 8
        if(format != null && !format.equals("")){
            song.setFormat(format);
        }else{
            song.setFormat("");
        }
        // 9
        if(duration != null && !duration.equals("")){
            song.setDuration(duration);
        }else{
            song.setDuration("");
        }


        SongServiceDAOImp userSDI = new SongServiceDAOImp();
        int counts = userSDI.update(song);
        boolean success = counts > 0;
        String msg = counts > 0 ? "修改成功！" : "修改失败！";

        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
