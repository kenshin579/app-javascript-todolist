package todo;

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
@WebServlet("/todo/LoginController")
public class ToDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public ToDo() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String receiveData = request.getParameter("todo");

        System.out.println("receiveData: " + receiveData);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

//        String jsonObject = "{ \"output\": \"" + receiveData +"\"} ";
        out.print(receiveData);
        out.flush();
    }

}
