package com.example.freeeducation00;

public class UserInfo {
    private String mail;

    private String password;

    public UserInfo() {

    }

    public UserInfo(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
