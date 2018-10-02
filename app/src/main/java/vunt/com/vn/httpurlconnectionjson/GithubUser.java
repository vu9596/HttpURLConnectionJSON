package vunt.com.vn.httpurlconnectionjson;

import java.io.Serializable;

public class GithubUser implements Serializable {
    private String mLogin;
    private String mAvatar;
    private String mType;

    public GithubUser(String login) {
        mLogin = login;
    }

    public String getLogin() {
        return mLogin;
    }

    public GithubUser setLogin(String login) {
        mLogin = login;
        return this;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public GithubUser setAvatar(String avatar) {
        mAvatar = avatar;
        return this;
    }

    public String getType() {
        return mType;
    }

    public GithubUser setType(String type) {
        mType = type;
        return this;
    }
}
