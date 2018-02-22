package com.apitester.test.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "quote", catalog = "test")

public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;

    public Quote(String userName, String quote) {
        this.userName = userName;
        this.quote = quote;
    }

    @Column (name = "quote")

    private String quote;

    public Quote() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Integer getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getQuote() {
        return quote;
    }
}
