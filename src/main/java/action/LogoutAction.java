package action;

import exception.ErrorCode;
import exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements IAction {

    private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        log.info("logout id: " + user.getUserid());

        if (user != null) {
            session.removeAttribute("user");

            log.info("session user = " + session.getAttribute("user"));

            forward.setRedirect(true);
            forward.setPath("index.html");
        } else {
            int errorCode = ErrorCode.ERROR_ID_NOT_EXIST;
            forward.setErrorCode(errorCode);
            forward.setErrorMessage(ErrorMessage.getErrorMessage(errorCode));
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
