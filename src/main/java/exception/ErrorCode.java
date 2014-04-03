package exception;

public class ErrorCode {
    /** ErrorCode 정의*/

    // Define RegisterAction Error Code
    /**
     * 중복된 이메일을 입력한 경우
     */
    public static final int ERROR_EMAIL_ALREADY_USED = 0x0101;

    /**
     * 이미 사용하는 아이디를 입력한 경우
     */
    public static final int ERROR_ID_ALREADY_USED = 0x0102;

    /**
     * 패스워드가 올바르지 않은 경우
     */
    public static final int ERROR_PASSWD_CONFIRM_INCORRETED = 0x0103;


    // Define Login Error Code
    /**
     * 입력한 아이디와 패스워드가 올바르지 않은 경우
     */
    public static final int ERROR_ID_PASSWD_INCORRETED = 0x0201;


    // Define Logout Error Code
    /**
     * 입력한 아이디와 패스워드가 올바르지 않은 경우
     */
    public static final int ERROR_ID_NOT_EXIST = 0x0301;


    // Define Profile Error Code
    /**
     * 세션 객체에서 USER ID 정보를 가져오지 못한 경우
     */
    public static final int ERROR_SEESION_INCORRETED = 0x0401;

}
