package com.digitalbooks.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class CreateBookRequest {
    @NotBlank
    @Size(max = 150)
    private String bookTitle;

    private int bookCode;

    @NotBlank
    @Size(max = 100)
    private String category;

    private double price;

    @NotBlank
    @Size(max = 120)
    private String publisher;


    private byte[] image;

    boolean isActive;

    @NotBlank
    private String bookcontent;


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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


    public CreateBookRequest(String bookTitle, int bookCode, String category, double price, String publisher, byte[] image, String bookContent) {
        this.bookTitle = bookTitle;
        this.bookCode = bookCode;
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
                ", bookCode=" + bookCode +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", image=" + Arrays.toString(image) +
                ", isActive=" + isActive +
                ", bookcontent='" + bookcontent + '\'' +
                '}';
    }
}
