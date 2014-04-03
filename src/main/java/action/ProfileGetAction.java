package action;

import exception.ErrorCode;
import exception.ErrorMessage;
import vo.ActionForward;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileGetAction implements IAction {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            forward.setUser(user);
            forward.setRedirect(true);
            forward.setPath("profile.html");
        } else {
            int errorCode = ErrorCode.ERROR_SEESION_INCORRETED;
            forward.setErrorCode(errorCode);
            forward.setErrorMessage(ErrorMessage.getErrorMessage(errorCode));
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
