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
        String pswd = request.getParameter("password");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("optionsRadios");
        String email = request.getParameter("email");
        System.out.println(name + pswd + phone + sex + email);
        try {
            Connection conn = DBUtil.getConnection();
            //2、查询是否为已注册用户
            String sqlc = "select * from user where name=?";
            PreparedStatement pstc = conn.prepareStatement(sqlc);
            pstc.setString(1,name);
            //跟数据库user表产生交互，并获得其中的数据，获得该数据的结果集
            ResultSet rs = pstc.executeQuery();
            String str = "";
            if(rs.next()){
                HttpSession se = request.getSession();
                se.setAttribute("name", rs.getString("name"));
                se.setAttribute("phone", rs.getString("phone"));
                //3、根据信息插入用户
                String sql = "insert into user(`name`,pswd,phone,sex,email) values(?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, pswd);
                pst.setString(3, phone);
                pst.setString(4, sex);
                pst.setString(5, email);
                if (rs.next()) {
                    se.setAttribute("email", rs.getString("email"));
                    System.out.println(str);
                    str = "{\"success\":true,\"msg\":\"注册成功\",\"rows\":[{\"name\":\"" + se.getAttribute("name") + "\",\"phone\":\"" + se.getAttribute("phone") + "\",\"email\":\"" + se.getAttribute("email") + "\"}]}";
                } else {
                    System.out.println(str);
                    str = "{\"success\":true,\"msg\":\"信息错误\"}";
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
