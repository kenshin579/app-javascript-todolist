package vo;

public class ActionForward {
    private boolean isRedirect = false;
    private String path = null;

    private int errorCode = 0;
    private String errorMessage = "";

    private User user = null;

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

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
