package controller;

import svc.TodoBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class MemRegServlet
 */
@WebServlet("/Sync")
public class SyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public SyncServlet() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todoContent = request.getParameter("todo");

        System.out.println("receiveData: " + todoContent);
        boolean result = TodoBiz.updateTodoContent("ykoh", todoContent);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

//        String jsonObject = "{ \"output\": \"" + receiveData +"\"} ";
        out.print("{result: " + result + "}");
        out.flush();
    }


}
