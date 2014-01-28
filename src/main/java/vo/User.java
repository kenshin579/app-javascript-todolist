package vo;

public class User {
    private String userid;
    private String name;
    private String passwd;
    private String email;

    public User() {
    }

    public User(String userid, String passwd, String name, String email) {
        this.userid = userid;
        this.name = name;
        this.passwd = passwd;
        this.email = email;
    }

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
