package com.digitalbooks.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.Arrays;

@JsonDeserialize(as = Book.class)
public class Book {
    private int id;
    private String bookTitle;
    private int authorId;
    private double price;
    private String category;
    private String publisher;
    private byte[] logo;
    private String audioURL;
    private String content;
    private boolean isActive;
    private LocalDate updatedOn;

    @JsonAlias("createdDate")
    private LocalDate createdOn;
    private Long subscriptionCount;


    public Book() {
        super();
    }


    public Book(int id, String bookTitle, int authorId, double price, String category, String publisher, byte[] logo, String audioURL, String content, boolean isActive, LocalDate updatedOn, LocalDate createdOn, Long subscriptionCount) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.authorId = authorId;
        this.price = price;
        this.category = category;
        this.publisher = publisher;
        this.logo = logo;
        this.audioURL = audioURL;
        this.content = content;
        this.isActive = isActive;
        this.updatedOn = updatedOn;
        this.createdOn = createdOn;
        this.subscriptionCount = subscriptionCount;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public Long getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(Long subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", authorId=" + authorId +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", audioURL='" + audioURL + '\'' +
                ", content='" + content + '\'' +
                ", isActive=" + isActive +
                ", updatedOn=" + updatedOn +
                ", createdOn=" + createdOn +
                ", subscriptionCount=" + subscriptionCount +
                '}';
    }
}
