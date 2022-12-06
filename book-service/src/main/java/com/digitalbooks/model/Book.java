package com.digitalbooks.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 150)
    @Column(name = "Title")
    private String bookTitle;
    @Column(name = "BookID")
    private int bookID;
    @Column(name = "Author")
    private int authorID;
    @Column(name = "Price")
    private double price;
    @NotBlank
    @Size(max = 100)
    @Column(name = "Category")
    private String category;
    @NotBlank
    @Size(max = 150)
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "Logo")
    @Lob
    private byte[] logo;
    @Column(name = "AudioURL")
    private String audioURL;
    @Column(name = "Content")
    private String content;
    @Column(name = "IsActive")
    private boolean isActive;
    @Column(name = "Updated_ON")
    private LocalDate updatedOn;
    @Column(name = "Created_ON")
    private LocalDate createdOn;


    public Book() {
    }

    public Book(String bookTitle, int bookCode, @Valid int authorId, String category, double price, String publisher, LocalDate now, LocalDate localDate, boolean b, String bookcontent) {
        this.bookTitle=bookTitle;
        this.bookID = bookCode;
        this.authorID=authorId;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
        this.createdOn = now;
        this.updatedOn = localDate;
        this.isActive = b;
        this.content = bookcontent;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookID=" + bookID +
                ", authorID=" + authorID +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", audioURL='" + audioURL + '\'' +
                ", content='" + content + '\'' +
                ", isActive=" + isActive +
                ", updatedOn=" + updatedOn +
                ", createdOn=" + createdOn +
                '}';
    }
}
