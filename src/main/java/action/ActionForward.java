package action;

public class ActionForward {
    private boolean isRedirect = false;
    private String path = null;
    private long errorCode = 0L;

    public boolean isRedirect() {
        return this.isRedirect;
    }

    public String getPath() {
        return this.path;
    }

    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

}
