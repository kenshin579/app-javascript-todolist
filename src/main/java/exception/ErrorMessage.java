package exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

    private static final Map<Integer, String> mErrorMsgMap = new HashMap<Integer, String>();

    static {
        mErrorMsgMap.put(ErrorCode.ERROR_EMAIL_ALREADY_USED, "");
        mErrorMsgMap.put(ErrorCode.ERROR_ID_ALREADY_USED, "");
        mErrorMsgMap.put(ErrorCode.ERROR_ID_PASSWD_INCORRETED, "");
        mErrorMsgMap.put(ErrorCode.ERROR_PASSWD_CONFIRM_INCORRETED, "");
        mErrorMsgMap.put(ErrorCode.ERROR_ID_NOT_EXIST, "");
        mErrorMsgMap.put(ErrorCode.ERROR_SEESION_INCORRETED, "");
    }

    public static String getErrorMessage(int errorCode) {
        return mErrorMsgMap.get(errorCode);
    }
}
