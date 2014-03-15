package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
