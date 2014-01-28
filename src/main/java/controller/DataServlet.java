package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import svc.TodoBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loadData")
public class DataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(DataServlet.class);

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("loadData");
////        String receiveData = request.getParameter("controller");
//
////        System.out.println("receiveData: " + receiveData);
//        //[{"text":"todo3"},{"text":"todo2"},{"text":"todo1"}]
//        DbUtil.getInstance().updateToDoData(receiveData);

        String resultStr = TodoBiz.getTodoContent("ykoh");
        log.info("resultStr" + resultStr);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

//        String jsonObject = "{ \"output\": \"" + receiveData +"\"} ";
//        out.print(receiveData);
        out.print(resultStr);
        out.flush();

    }

}
