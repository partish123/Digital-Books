package com.digitalbooks.services;


import com.digitalbooks.model.Book;
import com.digitalbooks.model.Reader;
import com.digitalbooks.utility.BookException;


import javax.validation.Valid;
import java.util.List;


public interface BookService {
    void createbook(Book book) throws BookException;

    void updateBook(Book book, @Valid Integer authorId, @Valid Integer bookId) throws BookException;

    List<Book> searchBook(String title, String author, String price, String publisher, String category) throws BookException;

    boolean blockOrUnBlockBookByAuthor(String authorId, String bookId, String block) throws BookException;

    boolean subscribeBook(String bookId, Reader reader) throws BookException;

    List<Book> getAllSubscribedBooks(String emailId) throws BookException;

    Book getSubscribedBook(String emailId, String subscriptionId) throws BookException;

    boolean cancleSubscriptionWithIn24Hours(String emailId, String subscriptionId) throws BookException;
}
