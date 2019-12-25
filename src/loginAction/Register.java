package loginAction;

import POJO.JsonData;
import POJO.User;
import ServiceDAO.users.UserServiceDAOImp;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
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
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name ="register" ,urlPatterns ="/loginAction/register" )
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1、获取注册页面输入信息
        String name = request.getParameter("name");
        String pswd = request.getParameter("pswd");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        System.out.println(" 用户名："+name+" 密码："+pswd+" 电话："+phone+" 性别："+sex+" 邮箱："+email);


        UserServiceDAOImp userSDI = new UserServiceDAOImp();
        ArrayList<User> result = null;
        User user = new User();
        boolean success = false;
        String msg = "";

        if(name == null || name.equals("")){
            msg = "用户名不能为空！";
            success = false;
        } else if (pswd == null || pswd.equals("")){
            msg = "密码不能为空！";
            success = false;
        } else {
            // 设置条件
            user.setCondition(" user.name = '" + name + "'");
            // 查询
            result = userSDI.select(user);
            // 当查询结果不为0时表明用户已存在，否则为新用户
            if (result.size() != 0) {
                msg = "用户已存在！";
                success = false;
            } else {
                user.setSex(sex);
                user.setName(name);
                user.setPswd(pswd);
                user.setPhone(phone);
                user.setEmail(email);
                user.setType(0);
                // 添加新用户
                if(userSDI.insert(user) != -1){
                    // 注册成功
                    success = true;
                    msg = "用户注册成功！";
                    // 创建会话保存用户登录状态
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                }else{
                    success = false;
                    msg = "服务器异常！";
                }
            }
        }
        // 业务转发
        JsonData jsonData = new JsonData(success,msg);
        request.setAttribute("jsonData", jsonData);
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request,response);

//        try {
//            //2、查询是否为已注册用户
//            String sql = "select * from user where name=" + name;
//            Statement smt = conn.createStatement();
//            ResultSet rs = smt.executeQuery(sql);
//
//            String str = "";
//            if(rs.next()){
//                HttpSession se1 = request.getSession();
//                se1.setAttribute("name", rs1.getString("name"));
//                str = "{\"success\":true,\"msg\":\"用户已存在\"}";
//            }
//            else {
//                //3、根据信息插入用户
//                HttpSession se = request.getSession();
//                String sql = "insert into user (`name`,pswd,phone,sex,email) value('"
//                           +name
//                           +"','"
//                           +pswd
//                           +"','"
//                           +phone
//                           +"','"
//                           +sex
//                           +"','"
//                           +email
//                           +"')";
//                System.out.println(sql);
//                PreparedStatement pst = conn.prepareStatement(sql);
//                /*跟数据库user表产生交互，并获得其中的数据，获得该数据的结果集*/
//                int rs = pst.executeUpdate();
//                conn.commit();
//                if (rs==1) {
//
//                    System.out.println(str);
//                    str = "{\"success\":false,\"msg\":\"注册成功\",\"rows\":[{\"name\":\"" + se.getAttribute("name") + "\",\"password\":\"" + se.getAttribute("password") + "\"}]}";
//                } else {
//                    System.out.println(str);
//                    str = "{\"success\":false,\"msg\":\"数据库没有数据\"}";
//                }
//            }
//            out.print(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//            out.print("{\"success\":false,\"msg\":\"注册失败\"}");
//        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
