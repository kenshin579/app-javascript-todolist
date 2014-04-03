package exception;

public class ErrorCode {
    /** ErrorCode 정의*/

    /**
     * 중복된 이메일을 입력한 경우
     */
    public static final long ERROR_EMAIL_ALREADY_USED = 0x0101;
    /**
     * 이미 사용하는 아이디를 입력한 경우
     */
    public static final long ERROR_ID_ALREADY_USED = 0x0102;
    /**
     * 패스워드가 올바르지 않은 경우
     */
    public static final long ERROR_PASSWD_CONFIRM_INCORRETED = 0x0103;

    /**
     * 입력한 아이디와 패스워드가 올바르지 않은 경우
     */
    public static final long ERROR_ID_PASSWD_INCORRETED = 0x0201;

}
