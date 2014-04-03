package action;

import exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import svc.TodoBiz;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    public static final long ERROR_ID_ALREADY_USED = 0x02;

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");

        log.info("id: " + id + " passwd: " + passwd);

        TodoBiz todoBiz = new TodoBiz();
        User user = todoBiz.getUserLogin(id, passwd);

        ActionForward forward = new ActionForward();

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            forward.setRedirect(true);
            forward.setPath("todo.html");
        } else {
            forward.setErrorCode(ErrorCode.ERROR_ID_PASSWD_INCORRETED);
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
