package loginAction;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


@WebServlet(name ="loginAction" ,urlPatterns ="/loginAction/login" )
public class login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1、获取登录页面输入的用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

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
            condition = " user.name = '" + username + "'" + " and user.pswd = '" + password + "'";
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
                // 保存user信息
                user = result.get(0);
                user.setCondition(" user.name = '" + user.getName() + "'");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
            }
        }

        // 业务转发
        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
