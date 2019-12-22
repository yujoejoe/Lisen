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
 * Created by user on 2019/12/20.
 */
@WebServlet(name = "UserUpdate", urlPatterns = "/admin/userUpdate")
public class UserUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String sex = request.getParameter("sex");
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        String password = request.getParameter("password");

        User user = new User();

        if(id != null && !id.equals("")){
            user.setId(Integer.parseInt(id));
        }else{
            user.setId(-1);
        }
        if(type != null && !type.equals("")){
            user.setType(Integer.parseInt(type));
        }else{
            user.setType(-1);
        }
        if(status != null && !status.equals("")){
            user.setStatus(Integer.parseInt(status));
        }else{
            user.setStatus(-1);
        }
        if(sex != null && !sex.equals("")){
            user.setSex(sex);
        }else{
            user.setSex("");
        }
        if(name != null && !name.equals("")){
            user.setName(name);
        }else{
            user.setName("");
        }
        if(email != null && !email.equals("")){
            user.setEmail(email);
        }else{
            user.setEmail("");
        }
        if(phone != null && !phone.equals("")){
            user.setPhone(phone);
        }else{
            user.setPhone("");
        }
        if(password != null && !password.equals("")){
            user.setPswd(password);
        }else{
            user.setPswd("");
        }

        UserServiceDAOImp userSDI = new UserServiceDAOImp();
        int counts = userSDI.update(user);
        boolean success = counts >= 0;
        String msg = counts >= 0 ? "修改成功！" : "修改失败！";

        JsonData jsonData = new JsonData(success, msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
