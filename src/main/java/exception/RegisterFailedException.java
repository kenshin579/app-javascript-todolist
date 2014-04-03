package exception;

public class RegisterFailedException extends Exception {
    private static final long serialVersionUID = 1L;

    private long errorCode;

    public RegisterFailedException(String message, long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return this.errorCode;
    }
}
