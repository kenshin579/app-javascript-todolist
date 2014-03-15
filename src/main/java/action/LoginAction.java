package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import svc.TodoBiz;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO LIST
        // 1. 현재 하드 코딩되어 있는 부분 수정하기

        ActionForward forward = new ActionForward();

        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");

        log.info("id: " + id + " passwd: " + passwd);

        TodoBiz todoBiz = new TodoBiz();
        User user = todoBiz.getUserLogin(id, passwd);

        if (user != null) {
            log.info("login successful");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            session.setAttribute("test", "frank");

            forward.setRedirect(true);
            forward.setPath("todo.html");

            return forward;
        } else {
            log.info("login unsuccessful");
        }

        return null;
    }
}
