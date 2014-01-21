package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DB에 존재하는 데이터들을 sql 문장을 이용해서 다루는 클래스들, 즉
 * DAO(Data Access Object)들이 존재하는 패키지이다.
 */
public class LoginDao {
    private static final Logger log = LoggerFactory.getLogger(LoginDao.class);
    Connection con;


    public LoginDao(Connection con) {
        super();
        this.con = con;
    }

    public User getUserLogin(String id, String passwd) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            String sql = "select * from account where userid =? and passwd=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setUserid(rs.getString("userid"));
                user.setName(rs.getString("name"));
                user.setPasswd(rs.getString("passwd"));
                user.setEmail(rs.getString("email"));
                log.info(user.getUserid());
                log.info(user.getPasswd());
                log.info(user.getName());
                log.info(user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
            DbUtil.getInstance().close(rs);
        }

        return user;
    }
}
