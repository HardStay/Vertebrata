package modeldata;

public class DataLogin {
    private String loginUser;
    private String user;

    public DataLogin() {
    }

    public DataLogin(String loginUser, String user) {
        this.loginUser = loginUser;
        this.user = user;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
