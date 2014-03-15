package action;

import dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class RegisterAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // TODO
        // 1. 아이디 중복 확인 (ㅇ)
        // 2. 동일한 패스워드 입력했는지 확인
        // 3. 이메일 중복 체크 (ㅇ)

        String userid = req.getParameter("inputId");
        String passwd = req.getParameter("inputPasswd");
        String confirmpasswd = req.getParameter("inputConfirmPasswd");
        String name = req.getParameter("inputName");
        String email = req.getParameter("inputEmail");

        Connection con = null;
        boolean retVal = false, isExistUserId = false, isExistEmail = false;

        try {
            con = DbUtil.getInstance().getConnection();

            MemberDao memberDao = new MemberDao(con);

            isExistUserId = memberDao.isExistUserId(userid);
            isExistEmail = memberDao.isExistEmail(email);

            if (!isExistUserId && !isExistEmail) {
                User newUser = new User(userid, passwd, name, email);
                retVal = memberDao.createMember(newUser);
            }
        } finally {
            DbUtil.getInstance().close(con);
        }

        ActionForward forward = new ActionForward();

        if (retVal == true) {
            forward.setRedirect(true);
            forward.setPath("index.html");
        } else {
            if (isExistUserId) {
                // TODO : 중복 아이디로 인한 오류 발생 처리
            }
            if (isExistEmail) {
                // TODO : 이메일 중복으로 인한 오류 발생 처리
            }
            forward.setRedirect(true);
            forward.setPath("error.html");
        }

        return forward;
    }
}
