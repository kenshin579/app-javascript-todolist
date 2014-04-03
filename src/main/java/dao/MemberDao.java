package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 존재하는 데이터들을 sql 문장을 이용해서 다루는 클래스들, 즉
 * DAO(Data Access Object)들이 존재하는 패키지이다.
 */
public class MemberDao {
    private static final Logger log = LoggerFactory.getLogger(MemberDao.class);
    Connection con;


    public MemberDao(Connection con) {
        super();
        this.con = con;
    }


    public boolean createMember(User user) {
        PreparedStatement pstmt = null;
        boolean result = false;
        try {
            String sql = "INSERT INTO account VALUES (?, ?, ?, ?)";
            System.out.println("query: " + sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserid());
            pstmt.setString(2, user.getPasswd());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            int returnValue = pstmt.executeUpdate();
            if (returnValue > 0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
        }
        return result;
    }

    public boolean updateMember(User user) {
        PreparedStatement pstmt = null;
        boolean result = false;
        try {
            String sql = "UPDATE account set userid=?, passwd=?, name=?, email=? WHERE userid=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserid());
            pstmt.setString(2, user.getPasswd());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getUserid());
            int returnValue = pstmt.executeUpdate();

            if (returnValue > 0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
        }
        return result;
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

    public boolean isExistUserId(String userId) {
        boolean isExist = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String sql = "select count(*) from account where userid =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) >= 1) {
                    isExist = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
            DbUtil.getInstance().close(rs);
        }

        return isExist;
    }

    // TODO : 중복아이디 체크
    public boolean isExistEmail(String email) {
        boolean isExist = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String sql = "select count(*) from account where email =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) >= 1) {
                    isExist = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
            DbUtil.getInstance().close(rs);
        }

        return isExist;
    }
}
