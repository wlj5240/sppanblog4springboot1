package net.sppan.blog.common.param;

public class LoginParam {
    private String username;
    private String password;
    private Boolean keepLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getKeepLogin() {
        return keepLogin;
    }

    public void setKeepLogin(Boolean keepLogin) {
        this.keepLogin = keepLogin;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", keepLogin=" + keepLogin +
                '}';
    }
}
