package action;

import dao.MemberDao;
import exception.ErrorCode;
import exception.ErrorMessage;
import util.DbUtil;
import vo.ActionForward;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class ProfileUpdateAction implements IAction {

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        User oldUser = (User) session.getAttribute("user");

        String passwd = req.getParameter("inputPasswd");
        String confirmpasswd = req.getParameter("inputConfirmPasswd");
        String name = req.getParameter("inputName");
        String email = req.getParameter("inputEmail");

        DbUtil dbInstance = DbUtil.getInstance();
        Connection con = null;
        boolean retVal = false,
                isExistEmail = false,
                isSamePwd = false;

        try {
            con = dbInstance.getConnection();
            MemberDao memberDao = new MemberDao(con);

            if (passwd.equals(confirmpasswd))
                isSamePwd = true;

            if (isSamePwd) {
                User newUser = new User(oldUser.getUserid(), passwd, name, email);
                retVal = memberDao.updateMember(newUser);
            }
        } finally {
            dbInstance.close(con);
        }

        ActionForward forward = new ActionForward();

        if (retVal) {
            forward.setRedirect(true);
            forward.setPath("todo.html");
        } else {
            int errorCode = 0;
            if (isExistEmail) errorCode = ErrorCode.ERROR_EMAIL_ALREADY_USED;
            else if (!isSamePwd) errorCode = ErrorCode.ERROR_PASSWD_CONFIRM_INCORRETED;

            forward.setErrorMessage(ErrorMessage.getErrorMessage(errorCode));
            forward.setErrorCode(errorCode);
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
