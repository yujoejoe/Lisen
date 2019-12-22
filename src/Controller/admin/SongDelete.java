package Controller.admin;

import POJO.JsonData;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 2019/12/22.
 */
@WebServlet(name = "SongDelete", urlPatterns = "/admin/songDelete")
public class SongDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String[] songId = request.getParameterValues("songId");

        int counts = 0;
        String msg = "删除失败！";
        boolean success = false;

        if(songId != null && songId.length != 0){
            StringBuilder str = new StringBuilder();
            for (String ele:songId) {
                str.append(ele);
                str.append(',');
            }
            str.setLength(str.length() - 1);        // 删除最后的逗号

            String sql = "delete from song where song.id in (" + str.toString() + ")";

            Connection conn = DBUtil.getConnection();

            try{
                Statement smt = conn.createStatement();
//                System.out.println(smt.toString());
                counts = smt.executeUpdate(sql);
                success = counts > 0;
                msg = counts > 0 ? "删除成功！" : "服务器异常, 删除失败！";
                conn.commit();
            }catch(SQLException e){
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                if(conn != null){
                    DBUtil.closeConnection(conn);
                }
            }

        }

        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
