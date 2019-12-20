package Controller.admin;

import POJO.JsonData;
import POJO.User;
import ServiceDAO.users.UserServiceDAOImp;

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
@WebServlet(name = "UserSelect", urlPatterns = "/admin/userSelect")
public class UserSelect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        type = type == null ? "0" : type;

        UserServiceDAOImp userSDI = new UserServiceDAOImp();
        User user = new User();

        String condition = " user.type = " + type;

        if(username != null && !username.equals("")){
            condition += " and user.name = " + username;
        }

        user.setCondition(condition);

        if(page != null && !page.equals("") && limit != null && !limit.equals("")){
            int p = Integer.parseInt(page);
            int l = Integer.parseInt(limit);
            user.setLimit(" limit " + (p - 1) * l + ',' + l);
        }

        // 返回给前端的数据
        ArrayList<User> result = userSDI.select(user);
        boolean success = result.size() != 0;
        String msg = result.size() != 0 ? "查询成功！" : "查询失败！";
        int counts = userSDI.count(user, Integer.parseInt(type));

        JsonData jsonData = new JsonData(success, msg, counts, result);
        request.setAttribute("jsonData", jsonData);

        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }
}
