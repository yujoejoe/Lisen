package Controller.song;

import POJO.Lyric;

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
@WebServlet(name = "lyricGet", urlPatterns = "/lyricGet")
public class lyricGet extends HttpServlet {
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
//        String timeDown = request.getParameter("timeDown");
//        String path = singer + "/" + timeDown + ".lrc";       // 歌词文件路径为：歌手名 - 歌曲名.lrc

        String path = request.getParameter("path");
        path += ".lrc";


        source.setPath(path);
        String lyric = source.getLyric();

//        Song timeDown = new Song();
//        path += ".mp3";
//        timeDown.setPath(path);
//        String url = timeDown.getUrl();

        out.print(lyric);

//        out.print(url);
    }
}
