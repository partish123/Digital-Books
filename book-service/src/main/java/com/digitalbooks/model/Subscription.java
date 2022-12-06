package com.digitalbooks.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Subscription")
public class Subscription {
    @Id
    @Column(name = "Subscriber_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerId;

    @NotNull
    @Size(max = 50)
    @Column(name = "emailID",unique = true)
    private String emailId;

    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY , mappedBy ="subscription")
    private List<Book> book = new ArrayList<>( );

    public Subscription() {
        super();
    }

    public Subscription(String emailId, List<Book> book) {
        this.emailId = emailId;
        this.book = book;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getEmailID() {
        return emailId;
    }

    public void setEmailID(String emailID) {
        this.emailId = emailID;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
