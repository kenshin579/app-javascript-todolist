package svc;

import dao.MemberDao;
import dao.ToDoDao;
import util.DbUtil;
import vo.User;

import java.sql.Connection;

/**
 * 비지니스 로직을 정의하는 자바 파일이 존재하는 패키지이다.
 */
public class TodoBiz {
    public static String getTodoContent(String userid) {
        Connection con = DbUtil.getInstance().getConnection();

        ToDoDao toDoDao = new ToDoDao(con);
        String result = toDoDao.getTodoData(userid);
        DbUtil.getInstance().close(con);
        return result;
    }

    public static boolean updateTodoContent(String userid, String todoContent) {
        Connection con = DbUtil.getInstance().getConnection();

        ToDoDao toDoDao = new ToDoDao(con);
        boolean result = toDoDao.updateToDoData(userid, todoContent);
        DbUtil.getInstance().close(con);
        return result;
    }

    public User getUserLogin(String id, String passwd) {
        Connection con = DbUtil.getInstance().getConnection();

        MemberDao memberDao = new MemberDao(con);
        User user = memberDao.getUserLogin(id, passwd);
        DbUtil.getInstance().close(con);
        return user;
    }
}
