package Controller.mv;

import POJO.Area;
import POJO.JsonData;
import POJO.MV;
import POJO.Version;
import ServiceDAO.mv.mvServiceDAOImp;
import net.sf.json.JSONObject;
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
        String page = request.getParameter("page");
        String song = request.getParameter("song");
        String title = request.getParameter("title");
        String order = request.getParameter("order");
        String singer = request.getParameter("singer");
        String version = request.getParameter("version");

        // 获取mv表数据
        MV mv = new MV();
        mvServiceDAOImp mvSDI = new mvServiceDAOImp();

        // 添加条件
        String condition = "";
        if(area != null && !area.equals("")){
            condition += " and area.name = '" + area + "'";
        }
        if(version != null && !version.equals("")){
            condition += " and version.name = '" + version + "'";
        }
        if(title != null && !title.equals("")){
            condition += " and mv.title like'%" + title + "%'";
        }
        if(singer != null && !singer.equals("")){
            condition += " and singer.name like'%" + singer + "%'";
        }
        if(song != null && !song.equals("")){
            condition += " and song.name = '" + song + "'";
        }
        mv.setCondition(condition);
//        System.out.println("condition:" + mv.getCondition());


        // 设置排序方式 默认按时间降序排序
        if(order != null){
            String field = order.equals("0") ? "mv.date" : "mv.play";
            mv.setOrderBy(" order by " + field + " desc");
        }else{
            mv.setOrderBy("");
        }


        // 设置分页  默认每页8条记录
        if(page!=null && !page.equals("0")){
            int p = Integer.parseInt(page);
            int size = 8;
            mv.setLimit(" limit " + (p - 1) * size + "," + size);
//            System.out.println("limit: " + mv.getLimit());
        }else{
            mv.setLimit("");
        }


        // 查询结果
        ArrayList<MV> result = mvSDI.select(mv);
        boolean success = result.size() != 0;
        int counts = result.size();
        String msg = success ? "查询成功！" : "查询失败！";

        // 业务转发
        /**
         * 返回的json格式：
         * success: true | false 查询成功与否
         * msg: 提示信息
         * counts: 记录数  (可选参数)
         * result: 结果集(对象数组) (可选参数)
         */
        JsonData JsonData = new JsonData(success,msg, counts, result);
        request.setAttribute("jsonData", JsonData);
        //4.(转)将业务转发到View
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request, response);

    }
}
