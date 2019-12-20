package Controller.user.collectionSong;

import POJO.JsonData;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name ="Collection/song/get" ,urlPatterns ="/collection/song/get" )
public class CollectionSongGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");




        Connection conn = DBUtil.getConnection();
        String  name  =  request.getParameter("name");
        try{
            String sql = "select song.id  as id from song  where song.name='"+name+"'";
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql);
            ArrayList<String> result = new ArrayList<>();
            while(rs.next()){
                result.add(rs.getString("id"));
            }
            // 返回参数
            String msg = result.size() != 0 ? "查询成功！" : "查询失败！";
            boolean success = result.size() != 0;
            int counts = result.size();

            // 业务转发
            JsonData jsonData = new JsonData(success, msg, counts, result);
            request.setAttribute("jsonData", jsonData);
            RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
            rd.forward(request, response);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }
}
