package Controller.admin;

import POJO.JsonData;
import POJO.User;
import ServiceDAO.users.UserServiceDAOImp;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 2019/12/21.
 */
@WebServlet(name = "UserDelete", urlPatterns = "/admin/userDelete")
public class UserDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String[] id = request.getParameterValues("id");

        int counts = 0;
        String msg = "删除失败！";
        boolean success = false;

        if(id != null){
            int size = id.length;
            StringBuilder str = new StringBuilder();
            for (String ele:id) {
                str.append(ele);
                str.append(',');
            }
            str.setLength(str.length() - 1);
//            System.out.println(str.toString());
            String sql = "delete from user where user.id in (" + str.toString() + ")";
//            System.out.println("User Delete: " + sql);
            Connection conn = DBUtil.getConnection();

            try{
                Statement smt = conn.createStatement();
//                System.out.println(smt.toString());
                counts = smt.executeUpdate(sql);
                success = counts > 0;
                msg = counts > 0 ? "删除成功！" : "删除失败！";
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



//        UserServiceDAOImp userSDI = new UserServiceDAOImp();
//        User user = new User();
//
//        if(id != null && !id.equals("")){
//            user.setId(Integer.parseInt(id));
//        }else{
//            user.setId(-1);
//        }
//
//        int count = userSDI.delete(user);
//        boolean success = count > 0;
//        String msg = count > 0 ? "删除成功！" : "删除失败, 用户不存在！";
//
        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
