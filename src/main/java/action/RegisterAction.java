package action;

import dao.MemberDao;
import exception.ErrorCode;
import exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class RegisterAction implements IAction {

    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String userid = req.getParameter("inputId");
        String passwd = req.getParameter("inputPasswd");
        String confirmpasswd = req.getParameter("inputConfirmPasswd");
        String name = req.getParameter("inputName");
        String email = req.getParameter("inputEmail");

        DbUtil dbInstance = DbUtil.getInstance();
        Connection con = null;
        boolean retVal = false,
                isExistUserId = false,
                isExistEmail = false,
                isSamePwd = false;

        try {
            con = dbInstance.getConnection();
            MemberDao memberDao = new MemberDao(con);

            isExistUserId = memberDao.isExistUserId(userid);
            isExistEmail = memberDao.isExistEmail(email);
            if (passwd.equals(confirmpasswd))
                isSamePwd = true;

            if (!isExistUserId && !isExistEmail && isSamePwd) {
                User newUser = new User(userid, passwd, name, email);
                retVal = memberDao.createMember(newUser);
            }
        } finally {
            dbInstance.close(con);
        }

        ActionForward forward = new ActionForward();

        if (retVal) {
            forward.setRedirect(true);
            forward.setPath("index.html");
        } else {
            int errorCode = 0;

            if (isExistUserId) errorCode = ErrorCode.ERROR_ID_ALREADY_USED;
            else if (isExistEmail) errorCode = ErrorCode.ERROR_EMAIL_ALREADY_USED;
            else if (!isSamePwd) errorCode = ErrorCode.ERROR_PASSWD_CONFIRM_INCORRETED;

            forward.setErrorMessage(ErrorMessage.getErrorMessage(errorCode));
            forward.setErrorCode(errorCode);
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
