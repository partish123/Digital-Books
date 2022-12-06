package com.digitalbooks.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="subscription" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "emailID")
})
public class Subscription {
    @Id
    @Column(name = "Reader_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerId;

    @NotNull
    @Size(max = 50)
    @Email
    private String emailID;

    @Column(name = "Book_subscription_List")

    private List<Book> book;

    public Subscription() {
        super();
    }

//    public Subscription(String emailID, List<Book> book) {
//        this.emailID = emailID;
//        this.book = book;
//    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

//    public List<Book> getBook() {
//        return book;
//    }
//
//    public void setBook(List<Book> book) {
//        this.book = book;
//    }
}
