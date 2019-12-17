package Controller.indexData;

import POJO.JsonData;
import POJO.indexData.Japan;
import ServiceDAO.indexData.Japan.JapanServiceDAOImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/12
 */
@WebServlet(name ="Japan" ,urlPatterns ="/Japan/get" )
public class JapanGet extends HttpServlet{
    private static final long serialVersion = 1L;

    private JapanServiceDAOImp JapanDI = new JapanServiceDAOImp();
    private Japan Japan = new Japan();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 设置排序方式
        String areaId1 = request.getParameter("areaId1");   // 区域1
        String areaId2 = request.getParameter("areaId2");   // 区域2
        if(areaId1!=null && areaId1.length()!=0 && areaId2!=null && areaId2.length()!=0){
            Japan.setOrderBy(" WHERE singer.areaId=" + areaId1 + " OR singer.areaId=" + areaId2 + " "+"ORDER BY date DESC");
        }else{
            Japan.setOrderBy("");
        }

        // （调）2、调用ServiceDAO方法，完成业务
        /**
         * 查询业务
         * 1、调用DAO层的select方法，返回查询到的记录集
         * 2、调用DAO层的count方法，返回查询到的记录数
         */
        ArrayList<Japan> result = JapanDI.select(Japan);
        int count = JapanDI.count(Japan);

        // （存）3、将数据对象存储到request中
        boolean success;    // 操作成功与否
        String msg;         // 返回结果信息
        if(result.size()==0 || count==-1){
            success = false;
            msg = "查询失败！";
        }else{
            success = true;
            msg = "查询成功！";
        }
        JsonData jsonData = new JsonData(success,msg,count,result);
        request.setAttribute("jsonData",jsonData);

        // （转）4、将业务转发给View
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request,response);
    }
}
