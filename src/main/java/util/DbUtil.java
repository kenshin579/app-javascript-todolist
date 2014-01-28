package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DbUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);

    private static final String url = "jdbc:mysql://" + "localhost:3306/jtl";

    private static final String id = "root";
    private static final String passwd = "1234";

    private static final String driver = "com.mysql.jdbc.Driver";

    private Connection connection;

    private static DbUtil DbUtil = new DbUtil();

    public static DbUtil getInstance() {
        return DbUtil;
    }

    private DbUtil() {
        loadDriver();
        getConnection();
    }

    // 드라이버 로딩하는 메소드
    public void loadDriver() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // Connection 객체를 반환하는 메소드
    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, id, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }


    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
