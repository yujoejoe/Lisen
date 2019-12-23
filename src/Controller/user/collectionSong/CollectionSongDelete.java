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
import java.sql.PreparedStatement;

/**
 *
 */
@WebServlet(name ="Collection/song/delete" ,urlPatterns ="/collection/song/delete" )
public class CollectionSongDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");



        Connection conn = DBUtil.getConnection();
        int  userId   =Integer.parseInt( request.getParameter("userId"));
        int  songId   =Integer.parseInt( request.getParameter("songId"));
        try{

//            String sql = "insert into collectionSong set userId="+userId+",songId="+songId;
            String sql = "delete  from  CollectionSong  where userId="+userId+" and songId="+songId;
            PreparedStatement pst = conn.prepareStatement(sql);
            System.out.println("song delete:"+sql);
            int i= pst.executeUpdate(sql);
            conn.commit();
            boolean success;//操作成功与否
            String msg;//返回的结果信息
            if(i<1){success = false; msg = "刪除失败";}
            else{success = true; msg = "成功刪除["+i+"]条记录";}

            // 业务转发
            JsonData jsonData = new JsonData(success, msg);
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
