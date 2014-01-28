package controller;

import dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
        String userid = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String confirmpasswd = request.getParameter("confirmpasswd");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Connection con = DbUtil.getInstance().getConnection();

        User newUser = new User(userid, passwd, name, email);
        MemberDao memberDao = new MemberDao(con);
        boolean result = memberDao.createMember(newUser);
        DbUtil.getInstance().close(con);

        if (result == true) {
            response.sendRedirect("index.html");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
//            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
//            dispatcher.forward(request, response);
        }

//        TodoBiz loginBiz = new TodoBiz();
//        User user = loginBiz.getUserLogin(id, passwd);
//
//        if (user != null) {
//            log.info("login successful");
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("loginSuccess.html");
//            dispatcher.forward(request, response);
//
//        }
    }

}
