package com.apitester.test.zuul.apigateway.xss;

public class Comment {

    private String userName;
    private String text;

    public Comment() {
    }

    public Comment(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}