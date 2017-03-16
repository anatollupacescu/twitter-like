package com.test.core;

import java.util.Date;

public class Entry {

    private final User user;
    private final String status;
    private final Date date;

    public Entry(User user, String status, Date date) {
        this.user = user;
        this.status = status;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
