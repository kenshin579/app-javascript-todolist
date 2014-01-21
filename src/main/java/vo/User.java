package vo;

public class User {
    private String userid;
    private String name;
    private String passwd;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public String getUserid() {
        return userid;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
