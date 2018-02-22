package com.apitester.test.dbservice.model;

import java.util.List;

public class Quotes {

    private String userName;

    private List<String> quotes;

    public Quotes() {}

    public Quotes(String userName, List<String> quotes) {
        this.userName = userName;
        this.quotes = quotes;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }

    public String getUserName() {

        return userName;
    }

    public List<String> getQuotes() {
        return quotes;
    }
}
