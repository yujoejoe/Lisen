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

/**
 * Created by user on 2019/12/18.
 */
@WebServlet(name = "UserInsert", urlPatterns = "/admin/userInsert")
public class UserInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String type = request.getParameter("type");
        type = type == null ? "0" : type;

        User user = new User();
        UserServiceDAOImp userSDI = new UserServiceDAOImp();

        user.setType(Integer.parseInt(type));
        if(username != null && !username.equals("")){
            user.setName(username);
        }else{
            user.setName("");
        }
        if(password != null && !password.equals("")){
            user.setPswd(password);
        }else{
            user.setPswd("");
        }
        if(phone != null && !phone.equals("")){
            user.setPhone(phone);
        }else{
            user.setPhone("");
        }
        if(email != null && !email.equals("")){
            user.setEmail(email);
        }else{
            user.setEmail("");
        }

        int counts = userSDI.insert(user);
        boolean success = counts > 0;
        String msg = counts > 0 ? "添加成功！" : "服务器异常，添加失败！";

        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
