package controller;

import action.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            action = new ProfileGetAction();
            try {
                forward = action.execute(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                    log.info("forward is unsuccessful, code : " + forward.getErrorCode());

                    res.setContentType("application/json; charset=UTF-8");
                    PrintWriter printout = res.getWriter();

                    JSONObject resObject = new JSONObject();
                    resObject.put("errorCode", forward.getErrorCode());
                    resObject.put("errorMessage", forward.getErrorMessage());

                    printout.print(resObject);
                    printout.flush();
                } else {
                    log.info("forward is successful");

                    if (forward.getUser() != null) {
                        res.setContentType("application/json; charset=UTF-8");
                        PrintWriter printout = res.getWriter();

                        JSONObject resObject = new JSONObject();
                        resObject.put("userId", forward.getUser().getUserid());
                        resObject.put("name", forward.getUser().getName());
                        resObject.put("passwd", forward.getUser().getPasswd());
                        resObject.put("email", forward.getUser().getEmail());

                        printout.print(resObject);
                        printout.flush();

                        // TODO : user 정보 전달 후 redirect 처리는 어떻게 해야할까?
//                        res.sendRedirect(forward.getPath());
                    } else {
                        res.sendRedirect(forward.getPath());
                    }
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
