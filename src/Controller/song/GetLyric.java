package Controller.song;

import POJO.Lyric;
import POJO.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by user on 2019/12/1.
 */
@WebServlet(name = "GetLyric", urlPatterns = "/GetLyric")
public class GetLyric extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        Lyric source = new Lyric();

//        String singer = request.getParameter("singer");
//        String song = request.getParameter("song");
//        String path = singer + " - " + song + ".lrc";       // 歌词文件路径为：歌手名 - 歌曲名.lrc

        String path = request.getParameter("path");
        path += ".lrc";


        source.setPath(path);
        String lyric = source.getLyric();

//        Song song = new Song();
//        path += ".mp3";
//        song.setPath(path);
//        String url = song.getUrl();

        out.print(lyric);

//        out.print(url);
    }
}
