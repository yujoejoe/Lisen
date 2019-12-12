package Controller.song;

import POJO.Song;
import POJO.JsonData;
import ServiceDAO.song.SongServiceDAOImp;

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
 * Created by user on 2019/12/11
 */
@WebServlet(name ="SongGet" ,urlPatterns ="/song/get" )
public class SongGet extends HttpServlet{
    private static final long serialVersion = 1L;

    private SongServiceDAOImp songDI = new SongServiceDAOImp();
    private Song song = new Song();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();


        // （参）1、获取参数值保存到POJO对象中
        // 设置查询条件
        /*String search = request.getParameter("search");
        if(search!=null && search.length()!=0){
            song.setCondition(" song.name ='"+search+"' or song.date='"+search+"'");
        }else{
            song.setCondition("");
        }
        // 设置分页
        String page = request.getParameter("page");     // 页数
        String size = request.getParameter("size");     // 每页显示的数据大小
        if(page!=null && page.length()!=0 && size!=null && size.length()!=0){
            int p = Integer.parseInt(page);
            int r = Integer.parseInt(size);
            song.setLimit(" limit " + (p-1)*r + "," + r);
        }else{
            song.setLimit("");
        }*/

        // 设置排序方式
        String field = request.getParameter("field");   // 排序字段
        String order = request.getParameter("order");   // 排序方式 升序 或 降序
        if(field!=null && field.length()!=0 && order!=null && order.length()!=0){
            song.setOrderBy(" order by " + field + " " + order);
        }else{
            song.setOrderBy("");
        }

        // （调）2、调用ServiceDAO方法，完成业务
        /**
         * 查询业务
         * 1、调用DAO层的select方法，返回查询到的记录集
         * 2、调用DAO层的count方法，返回查询到的记录数
         */
        ArrayList<Song> result = songDI.select(song);
        int count = songDI.count(song);

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

        // 校验数据
        System.out.println(jsonData);

        // （转）4、将业务转发给View
        RequestDispatcher rd = request.getRequestDispatcher("/view/ToJSON");
        rd.forward(request,response);
    }
}
