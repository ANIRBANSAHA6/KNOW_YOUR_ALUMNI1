package com.example.know_your_alumni;

public class post {
    private String userName;
    private String caption;

    public post(String userName, String caption) {
        this.userName = userName;
        this.caption = caption;
    }

    public String getUserName() {
        return userName;
    }

    public String getCaption() {
        return caption;
    }
}
