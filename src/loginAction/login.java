package loginAction;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
        try{

            Connection conn = DBUtil.getConnection();
            //2、根据用户名与密码查找用户
            String sql = "select * from user where name=? and pswd=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            //跟数据库user表产生交互，并获得其中的数据，获得该数据的结果集
            ResultSet rs = pst.executeQuery();
            String str = "";
            if(rs.next()){
                HttpSession se = request.getSession();
                se.setAttribute("name", rs.getString("name"));
                se.setAttribute("pswd", rs.getString("pswd"));
                /*se.setAttribute("phone", rs.getString("phone"));
                se.setAttribute("sex", rs.getString("sex"));
                se.setAttribute("email", rs.getString("email"));*/
                System.out.println(str);
                str ="{\"success\":true,\"msg\":\"查询成功\",\"rows\":[{\"name\":\""+se.getAttribute("name")+"\",\"pswd\":\""+se.getAttribute("pswd")+"\"}]}";
            }else{
                System.out.println(str);
                str = "{\"success\":true,\"msg\":\"账号或密码错误\"}";
            }
            out.print(str);
        }
        catch(Exception e){
            e.printStackTrace();
            out.print("{\"success\":false,\"msg\":\"查询失败\"}");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
