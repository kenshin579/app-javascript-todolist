package svc;

import dao.LoginDao;
import util.DbUtil;
import vo.User;

import java.sql.Connection;

/**
 * 비지니스 로직을 정의하는 자바 파일이 존재하는 패키지이다.
 */
public class LoginBiz {
    public User getUserLogin(String id, String passwd) {
        Connection con = DbUtil.getInstance().getConnection();

        LoginDao loginDao = new LoginDao(con);
        User user = loginDao.getUserLogin(id, passwd);
        DbUtil.getInstance().close(con);
        return user;
    }
}
