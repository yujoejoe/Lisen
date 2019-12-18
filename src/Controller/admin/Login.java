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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/18.
 */
@WebServlet(name = "Login", urlPatterns = "/admin/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1、获取登录页面输入的用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        type = type == null ? "1" : type;

        UserServiceDAOImp userSDI = new UserServiceDAOImp();
        ArrayList<User> result = null;
        boolean success = false;
        User user = new User();
        String msg = "";

        if(username == null ||username.equals("")){
            success = false;
            msg = "用户名不能为空！ ";
        }else if(password == null || password.equals("")){
            success = false;
            msg = "密码不能为空！";
        }else{
            // 添加条件
            String condition = "";
            condition = " user.name = '" + username + "'" + " and user.pswd = '" + password + "'" + " and user.type = " + type;
            user.setCondition(condition);
            // 查询记录
            result = userSDI.select(user);
            System.out.println(result);
            if(result == null || result.size() == 0){
                success = false;
                msg = "账号或密码错误！";
            }else{
                success = true;
                msg = "登录成功！";
//                // 保存user信息
//                user = result.get(0);
//                user.setCondition(" user.name = '" + user.getName() + "'");
//                HttpSession session = request.getSession(true);
//                session.setAttribute("user", user);
            }
        }

        // 业务转发
        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
