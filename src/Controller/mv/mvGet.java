package Controller.mv;

import POJO.Area;
import POJO.MV;
import POJO.Version;
import ServiceDAO.mv.mvServiceDAOImp;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/3.
 */
@WebServlet(name = "mvGet", urlPatterns = "/mvGet")
public class mvGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();


        String area = request.getParameter("area");
        String version = request.getParameter("version");
        String page = request.getParameter("page");
        String order = request.getParameter("order");

        ArrayList<Area> areaList = new ArrayList<>();
        ArrayList<Version> versionList = new ArrayList<>();

        MV mv = new MV();
        mvServiceDAOImp mvSDI = new mvServiceDAOImp();

        if(area == null || version == null || page ==null){
            System.out.println("参数为空!");
            return;
        }



        if(area.equals("全部")) {
            Connection conn = DBUtil.getConnection();
            try {
                String sql = "select area.name from area";
                Statement smt = conn.createStatement();
                ResultSet rs = smt.executeQuery(sql);

                while(rs.next()){
                   Area tmp = new Area();
                   tmp.setName(rs.getString("name"));
                   areaList.add(tmp);
                }

//                System.out.println(areaList);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            }finally {
                if(conn != null){
                    DBUtil.closeConnection(conn);
                }
            }
        }

        if(version.equals("全部")){
            Connection conn = DBUtil.getConnection();
            try {
                String sql = "select version.name from version";
                Statement smt = conn.createStatement();
                ResultSet rs = smt.executeQuery(sql);

                while(rs.next()){
                    Version tmp = new Version();
                    tmp.setName(rs.getString("name"));
                   versionList.add(tmp);
                }

//                System.out.println(versionList);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            }finally {
                if(conn != null){
                    DBUtil.closeConnection(conn);
                }
            }
        }

        String condition = "";
        if(area.length() != 0 && !area.equals("全部")){

            condition += " and area.name = '" + area + "'";
//            System.out.println("area: " + mv.getCondition());
        }
        if(version.length() != 0 && !version.equals("全部")){
            condition += " and version.name = '" + version + "'";
        }

        mv.setCondition(condition);

        System.out.println("condition:" + mv.getCondition());


        // 分页
        if(page!=null && !page.equals("0")){
            int p = Integer.parseInt(page);
            int size = 8;
            mv.setLimit(" limit " + (p - 1) * size + "," + size);
            System.out.println("limit: " + mv.getLimit());
        }else{
            mv.setLimit("");
        }

        if(order != null && order.equals("0")){
            mv.setOrderBy(" order by mv.date desc");
        }else if(order != null && order.equals("1")){
            mv.setOrderBy(" order by mv.play desc");
        }


        ArrayList<MV> result = mvSDI.select(mv);

        boolean success = result.size() != 0;
        int counts = result.size();

        String msg = "{"+ "\"success\":" + success + ", "
                        + "\"area\":" + areaList + ", "
                        + "\"version\":" + versionList + ", "
                        + "\"result\":" + result + ", "
                        + "\"counts\":" + counts +
                    "}";

        out.print(msg);
    }
}
