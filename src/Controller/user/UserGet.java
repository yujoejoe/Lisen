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

@WebServlet(name ="UserGet" ,urlPatterns ="/userGet" )
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


      // （参）1、获取参数值保存到POJO对象中
      // 设置查询条件
      String search = request.getParameter("search");
      if(search!=null && search.length()!=0){
          // 模糊查找teacher的name、sequence
          user.setCondition(" user.name like '%"+search+"%'");
      }else{
          user.setCondition("");
      }


      ArrayList<User> result = usersSD.select(user);
    // 返回到前端的数据
    boolean success = false;
    String msg = "";

    // 获取session
    HttpSession session = request.getSession(false);      // 若没有获取到session,返回null
    if (session == null) {
      success = false;
      msg = "用户未登录！";
    } else {
      User user = (User) session.getAttribute("user");
      System.out.println("User: " + user);
      success = true;
      msg = "用户已登录！";
    }

    JsonData JsonData = new JsonData(success, msg,result);
    request.setAttribute("jsonData", JsonData);
    //4.(转)将业务转发到View
    RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
    rd.forward(request, response);

  }
}
