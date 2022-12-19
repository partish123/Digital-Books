package com.digitalbooks.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

@JsonDeserialize(as = CreateBookRequest.class)
public class CreateBookRequest {
@JsonAlias("title")
    private String bookTitle;
    private String category;
    private double price;
    private String publisher;

    private String image;
    boolean isActive;

    @JsonAlias("content")
    private String bookcontent;


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent;
    }


    public CreateBookRequest(String bookTitle, String category, double price, String publisher, String image, String bookContent) {
        this.bookTitle = bookTitle;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
        this.image = image;
        this.bookcontent = bookContent;
    }

    @Override
    public String toString() {
        return "CreateBookRequest{" +
                "bookTitle='" + bookTitle + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", image=" + image +
                ", isActive=" + isActive +
                ", bookcontent='" + bookcontent + '\'' +
                '}';
    }
}
