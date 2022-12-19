package com.digitalbooks.models;

public class Reader {
    private long readerId;
    private String emailId;

    public Reader(long readerId, String emailId) {
        this.readerId = readerId;
        this.emailId = emailId;
    }

    public Reader() {
        super();
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
