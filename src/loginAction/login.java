package loginAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 *
 */
//@WebServlet(name ="loginAction" ,urlPatterns ="/loginAction/login" )
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        //1、获取登录页面输入的用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.1.125:3306/test?useUnicode=true&characterEncoding=utf8";
            String user = "root";
            String pw = "1234";
            Connection conn = DriverManager.getConnection(url,user,pw);
            //2、根据用户名与密码查找用户
            String sql = "select * from user where name=? and pswd=?";
            //预编译
            PreparedStatement pst = conn.prepareStatement(sql);
            //设置值
            pst.setString(1,username);
            pst.setString(2,password);
            //执行sql语句
            ResultSet rs = pst.executeQuery();
            String str = "";
            if(rs.next()){
                HttpSession se = request.getSession();
                se.setAttribute("name", rs.getString("name"));
                se.setAttribute("phone", rs.getString("phone"));
                se.setAttribute("sex", rs.getString("sex"));
                se.setAttribute("email", rs.getString("email"));
                str ="{\"success\":true,\"msg\":\"查询成功\",\"rows\":[{\"name\":\""+se.getAttribute("name")+"\",\"phone\":\""+se.getAttribute("phone")+"\",\"sex\":\""+se.getAttribute("sex")+"\",\"email\":\""+se.getAttribute("email")+"\"}]}";
            }else{
                str = "{\"success\":true,\"msg\":\"账号或密码错误\"}";
            }
            out.print(str);
        }
        catch(Exception e){
            e.printStackTrace();
            out.print("{\"success\":false,\"msg\":\"查询失败\"}");
        }
            /*Users users=null;
            if (rs.next()){
                users = new Users();
                //从数据库中获取值设置到实体类的setter方法中
                users.setId(rs.getInt("id"));
                users.setName(rs.getString("name"));
                users.setPswd(rs.getString("pswd"));
                users.setPhone(rs.getString("phone"));
                users.setSex(rs.getString("sex"));
                users.setEmail(rs.getString("email"));
            }*/
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
