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

    // 返回到前端的数据
    boolean success = false;
    String msg = "";

    // 获取session
    HttpSession session = request.getSession(false);      // 若没有获取到session,返回null
    if(session == null){
      success = false;
      msg = "用户未登录！";
    }else{
      User user = (User)session.getAttribute("user");
      System.out.println("User: " + user);
      success = true;
      msg = "用户已登录！";
    }

    JsonData JsonData = new JsonData(success,msg);
    request.setAttribute("jsonData", JsonData);
    //4.(转)将业务转发到View
    //利用JsonData将记录数total与记录集studentList拼接成“{"success":true,"msg":"查询成功","total":50,"rows":[{},{}...{}]}”格式
    RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
    rd.forward(request, response);


    //分页条件
//    String page = request.getParameter("page");//开始
//    String size = request.getParameter("size");
//    if (page != null && page.length() > 0 && size != null && size.length() > 0){
//      int p = Integer.parseInt(page);
//      int s = Integer.parseInt(size);
//      user.setLimit(" limit "+(p-1)*s+","+s);
//    }else{
//      user.setLimit("");
//    }
//    //排序条件
//    String sort = request.getParameter("sort");//排序字段
//    String order = request.getParameter("order");//排序方式
//    if (sort != null && sort.length() > 0){
//      user.setOrderBy(" order by "+sort+" "+order);
//    }else{
//      user.setOrderBy("");
//    }
//    //2.(调)调用ServiceDAO的方法，完成对应业务
//    /**
//     * 查询业务
//     * 1、调用DAO层的select方法，返回查询到的记录集
//     * 2、调用DAO层的count方法，返回查询到的记录数
//     */
//    ArrayList<User> rows = usersSD.select(user);
//    int total = usersSD.count(user);
//    //3.(存)将数据对象存储到request作用范围变量
//    boolean success;//操作成功与否
//    String msg;//返回的结果信息
//    if(rows.size() == 0 || total == -1){success = false; msg = "查询失败";}
//    else{success = true; msg = "查询成功";}

  }

}
