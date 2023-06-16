package com.czk.domain;

public class User {
    private String u_id;
    private String username;
    private String password;
    private String headPath;
    private String email;
    private String token;

    public User() {
    }

    public User(String u_id) {
        this.u_id = u_id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String u_id, String username, String password) {
        this.u_id = u_id;
        this.username = username;
        this.password = password;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

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

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headPath='" + headPath + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
