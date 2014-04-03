package action;

import vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileGetAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 프로파일 버튼 클릭시 클라이언트로 해당 user id에 대한 정보를 넘겨줘야 한다.
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        return null;
    }
}
