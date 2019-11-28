package MVC.view;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class toJson extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public toJson() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Object JsonData = request.getAttribute("JsonData");
        JSONObject msg = JSONObject.fromObject(JsonData);//转换成JSON对象
        //JSONArray msg = JSONArray.fromObject(JsonData);//转换成JSON数组
        //Student student = JSON.parseObject(jsonString,Student.class);
        out.print(msg);
    }
}
