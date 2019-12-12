package Controller.user;

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

@WebServlet(name = "UserGet", urlPatterns = "/userGet")
public class UserGet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServiceDAOImp usersSD = new UserServiceDAOImp();
    private User user = new User();

    public UserGet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf8");
        PrintWriter out = response.getWriter();


        // 返回到前端的数据
        ArrayList<User> result = null;
        boolean success = false;
        String msg = "";

        // 获取session
        HttpSession session = request.getSession(false);      // 若没有获取到session,返回null
        if (session == null) {
            success = false;
            msg = "用户未登录！";
        } else {
            User user = (User) session.getAttribute("user");
            result = usersSD.select(user);
            System.out.println("User: " + user);
            success = true;
            msg = "用户已登录！";
        }

        JsonData JsonData = success ? new JsonData(success, msg, result) : new JsonData(success, msg);
        request.setAttribute("jsonData", JsonData);
        //4.(转)将业务转发到View
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);

    }
}
