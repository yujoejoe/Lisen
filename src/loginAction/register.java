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
import java.sql.ResultSetMetaData;

@WebServlet(name ="register" ,urlPatterns ="/loginAction/register" )
public class register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1、获取注册页面输入信息
        String name = request.getParameter("name");
        String pswd = request.getParameter("pswd");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        System.out.println(" 用户名："+name+" 密码："+pswd+" 电话："+phone+" 性别："+sex+" 邮箱："+email);
        try {
            Connection conn = DBUtil.getConnection();
            //2、查询是否为已注册用户
            String sqlc = "select * from user where name=?";
            PreparedStatement pstc = conn.prepareStatement(sqlc);
            pstc.setString(1,name);
            //跟数据库user表产生交互，并获得其中的数据，获得该数据的结果集
            ResultSet rs1 = pstc.executeQuery();
            String str = "";
            if(rs1.next()){
                HttpSession se1 = request.getSession();
                se1.setAttribute("name", rs1.getString("name"));
                str = "{\"success\":true,\"msg\":\"用户已存在\"}";
            }
            else {
                //3、根据信息插入用户
                HttpSession se = request.getSession();
                String sql = "insert into user (`name`,pswd,phone,sex,email) value('"
                           +name
                           +"','"
                           +pswd
                           +"','"
                           +phone
                           +"','"
                           +sex
                           +"','"
                           +email
                           +"')";
                System.out.println(sql);
                PreparedStatement pst = conn.prepareStatement(sql);
                /*跟数据库user表产生交互，并获得其中的数据，获得该数据的结果集*/
                int rs = pst.executeUpdate();
                conn.commit(); //提交
                if (rs==1) {
                    System.out.println(str);
                    str = "{\"success\":false,\"msg\":\"注册成功\",\"rows\":[{\"name\":\"" + se.getAttribute("name") + "\",\"password\":\"" + se.getAttribute("password") + "\"}]}";
                } else {
                    System.out.println(str);
                    str = "{\"success\":false,\"msg\":\"数据库没有数据\"}";
                }
            }
            out.print(str);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\":false,\"msg\":\"注册失败\"}");
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
