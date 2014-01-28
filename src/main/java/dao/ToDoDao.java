package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB에 존재하는 데이터들을 sql 문장을 이용해서 다루는 클래스들, 즉
 * DAO(Data Access Object)들이 존재하는 패키지이다.
 */
public class ToDoDao {
    private static final Logger log = LoggerFactory.getLogger(ToDoDao.class);
    Connection con;


    public ToDoDao(Connection con) {
        super();
        this.con = con;
    }


    public boolean updateToDoData(String userid, String todoData) {
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            String updateSql = "UPDATE todo SET todoContent = ? where userid = ?";
            log.info("query: " + updateSql);
            pstmt = con.prepareStatement(updateSql);
            pstmt.setString(1, todoData);
            pstmt.setString(2, userid);
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

    public String getTodoData(String userid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String jsonStr = null;

        try {
            String sql = "select todoContent from todo where userid=?";
            pstmt = con.prepareStatement(sql);
            log.info(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                jsonStr = rs.getString("todoContent");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.getInstance().close(pstmt);
            DbUtil.getInstance().close(rs);
        }
        return jsonStr;
    }
}
