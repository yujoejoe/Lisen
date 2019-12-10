package Controller.users;

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
import java.util.ArrayList;

@WebServlet(name ="UsersGet" ,urlPatterns ="/UsersGet/get" )
public class UsersGet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UserServiceDAOImp usersSD = new UserServiceDAOImp();
  User user = new User();

  public UsersGet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //初始化
    request.setCharacterEncoding("utf-8");
    //1.(参)获取参数值，并保存到POJO对象
    //查询条件
    String search = request.getParameter("search");
    if (search != null && search.length() > 0){
      user.setCondition(" user.name like '%"+search+"%'");
    }else{
      user.setCondition("");
    }
    //分页条件
    String page = request.getParameter("page");//开始
    String size = request.getParameter("size");
    if (page != null && page.length() > 0 && size != null && size.length() > 0){
      int p = Integer.parseInt(page);
      int s = Integer.parseInt(size);
      user.setLimit(" limit "+(p-1)*s+","+s);
    }else{
      user.setLimit("");
    }
    //排序条件
    String sort = request.getParameter("sort");//排序字段
    String order = request.getParameter("order");//排序方式
    if (sort != null && sort.length() > 0){
      user.setOrderBy(" order by "+sort+" "+order);
    }else{
      user.setOrderBy("");
    }
    //2.(调)调用ServiceDAO的方法，完成对应业务
    /**
     * 查询业务
     * 1、调用DAO层的select方法，返回查询到的记录集
     * 2、调用DAO层的count方法，返回查询到的记录数
     */
    ArrayList<User> rows = usersSD.select(user);
    int total = usersSD.count(user);
    //3.(存)将数据对象存储到request作用范围变量
    boolean success;//操作成功与否
    String msg;//返回的结果信息
    if(rows.size() == 0 || total == -1){success = false; msg = "查询失败";}
    else{success = true; msg = "查询成功";}
    JsonData JsonData = new JsonData(success,msg,total,rows);
    request.setAttribute("JsonData", JsonData);
    //4.(转)将业务转发到View
    //利用JsonData将记录数total与记录集studentList拼接成“{"success":true,"msg":"查询成功","total":50,"rows":[{},{}...{}]}”格式
    RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
    rd.forward(request, response);
  }

}
