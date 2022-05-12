package com.SpiritStore.Domain;

/**
 * Created by Dell on 2017/9/19.
 */
public class AdminInfo {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public AdminInfo(){}
}
