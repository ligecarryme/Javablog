package com.darin.blog.dto;

public class AdminLoginParam {
    private String username;
    private String password;

    public AdminLoginParam(){}

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
}
