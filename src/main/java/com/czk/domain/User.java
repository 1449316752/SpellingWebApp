package com.czk.domain;

public class User {
    private String u_id;
    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
