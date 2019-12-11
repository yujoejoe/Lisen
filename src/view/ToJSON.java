package view;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by user on 2019/11/26.
 */
//@WebServlet(name = "ToJSON", urlPatterns = "/view/ToJSON")
public class ToJSON extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ToJSON() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 格式转换
        Object jsonData = request.getAttribute("jsonData");
        JSONObject json = JSONObject.fromObject(jsonData);
        out.print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
