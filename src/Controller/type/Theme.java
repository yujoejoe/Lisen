package Controller.type;

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

/**
 * Created by user on 2019/12/12.
 */
@WebServlet(name = "Theme", urlPatterns = "/Theme")
public class Theme extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");


        // 因为语句简短所以暂时不使用MVC模式，如以后有需求再做更改

        Connection conn = DBUtil.getConnection();
        try{
            String sql = "select theme.name  as theme from theme";
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql);
            ArrayList<String> result = new ArrayList<>();
            while(rs.next()){
                result.add(rs.getString("theme"));
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
