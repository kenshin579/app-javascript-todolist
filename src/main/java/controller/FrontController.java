package controller;

import action.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("*.td")
public class FrontController extends HttpServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(FrontController.class);

    protected void doProcess(HttpServletRequest req, HttpServletResponse res) {
        String reqURI = req.getRequestURI();
        // TODO : what context?
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());

        log.info("reqURI = " + reqURI); // ex) LoginAction.td
        log.info("reqURI = " + contextPath); // ex) nothing
        log.info("reqURI = " + command); // // ex) LoginAction.td

        ActionForward forward = null;
        IAction action = null;

        if (command.equals(Constants.ACTION_LOGIN)) {
            action = new LoginAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals(Constants.ACTION_LOGOUT)) {
            action = new LogoutAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals(Constants.ACTION_REGISTER)) {
            action = new RegisterAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals(Constants.ACTION_SYNC)) {
            action = new SyncAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals(Constants.ACTION_GET_PROFILE)) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            // TODO : user 값을 클라이언트로 전달 어떻게?
            // action를 관리하는 FrontController 하나와
            // 이처럼 데이터를 전달해야 하는 DataController 하나가 나눠야하지 않을까?
            return;
        } else if (command.equals(Constants.ACTION_UPDATE_PROFILE)) {
            action = new ProfileUpdateAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.debug("DEBUG", "not supported action.");
        }

        // TODO : 에러 처리
        // http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        // http://javapapers.com/ajax/getting-started-with-ajax-using-java/

        if (forward != null && forward.isRedirect()) {
            try {
                if (forward.getErrorCode() > 0) {
                    // TODO : 클라이언트로 에러 코드를 전달할 수 있는 방법 필요
                    res.sendError(res.SC_CONFLICT, "param needed");
                    log.info("forward is error!!");
                } else {
                    res.sendRedirect(forward.getPath());
                    log.info("forward is successful");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.debug("DEBUG", "forward is null.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {
        doProcess(req, rep);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {
        doProcess(req, rep);
    }
}
