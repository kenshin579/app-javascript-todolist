package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.td")
public class FrontController extends HttpServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(FrontController.class);

    protected void doProcess(HttpServletRequest req, HttpServletResponse res) {

        String reqURI = req.getRequestURI();
        // TODO : what context?
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());

        log.info("reqURI = " + reqURI); // ex) LoginAtion.td
        log.info("reqURI = " + contextPath); // ex) nothing
        log.info("reqURI = " + command); // // ex) LoginAtion.td

        ActionForward forward = null;
        Action action = null;

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
        } else {
            log.debug("DEBUG", "not supported action.");
        }

        if (forward != null && forward.isRedirect()) {
            try {
                res.sendRedirect(forward.getPath());
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
