package Controller.mv;

import POJO.MV;
import ServiceDAO.mv.MvServiceDAOImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/6.
 */
@WebServlet(name = "videoGet",urlPatterns = "/videoGet")
public class videoGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String mId = request.getParameter("mId");
        ArrayList<MV> videoInfo = null;
        boolean success = true;

        if(mId == null || mId.length() == 0){
            System.out.println("mId is null!");
            return;
        }else {

            MvServiceDAOImp mvSDI = new MvServiceDAOImp();
            MV mv = new MV();

            // 设置条件
            mv.setCondition(" and mv.id = " + mId);
            videoInfo = mvSDI.select(mv);
        }

        String msg = "{" + "\"videoInfo\":" + videoInfo + "}";

        out.print(msg);


    }
}
