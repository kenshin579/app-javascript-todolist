package exception;

public class LoginFailedException extends Exception {
    private static final long serialVersionUID = 1L;

    private long errorCode;

    public LoginFailedException(String message, long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return this.errorCode;
    }
}
