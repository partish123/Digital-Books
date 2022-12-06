package com.digitalbooks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.digitalbooks.model.Book;
import com.digitalbooks.model.Reader;
import com.digitalbooks.model.Subscription;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.SubscriptionRepository;
import com.digitalbooks.utility.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    public void createbook(Book book) throws BookException {
        try{
           bookRepo.save(book);
        }catch (Exception e){
            throw new BookException(e.getMessage());
        }
    }

    public void updateBook(Book book , @Valid Integer authorId, @Valid Integer bookId) throws BookException {
            Optional<Book> books = bookRepo.findById(bookId);
            if (books.isPresent() && books.get().getAuthorID() == authorId) {
                if (book.getLogo() != null)
                    books.get().setLogo(book.getLogo());
                if (book.getBookTitle() != null && !book.getBookTitle().equalsIgnoreCase(""))
                    books.get().setBookTitle(book.getBookTitle());
                if (book.getCategory() != null && !book.getCategory().equalsIgnoreCase(""))
                    books.get().setCategory(book.getCategory());
                if (book.getPrice() >= 0)
                    books.get().setPrice(book.getPrice());
                if (book.getPublisher() != null && !book.getPublisher().equalsIgnoreCase(""))
                    books.get().setPublisher(book.getPublisher());
                if (book.getPublisher() != null && !book.getPublisher().equalsIgnoreCase(""))
                    books.get().setPublisher(book.getPublisher());
                if (book.getContent() != null && !book.getContent().isEmpty())
                    books.get().setContent(book.getContent());

                books.get().setActive(book.isActive());
                books.get().setUpdatedOn(book.getUpdatedOn());

                bookRepo.save(books.get());
            } else {
                throw new BookException("Sorry something went wrong in update book functionality");
            }
    }


    public List<Book> searchBook(String title, String author, String price, String publisher) throws BookException {
        try{
            return  bookRepo.searchBooks(title,author,price,publisher);
        }
        catch (Exception e){
            throw new BookException("Book not found");
        }

    }

    public boolean blockOrUnBlockBookByAuthor(String authorId, String bookId, String block) throws BookException {
        if (bookId != null && !bookId.isEmpty()) {
            Optional<Book> book = bookRepo.findById(Integer.parseInt(bookId));
            if (book.isPresent() && book.get().getAuthorID() == Integer.parseInt(authorId)) {
                book.get().setActive(block == null || !block.equalsIgnoreCase("Yes"));
                bookRepo.save(book.get());
                return true;
            } else {
                throw new BookException("CANNOT FIND BOOK WITH GIVEN ID" + bookId);
            }
        } else {
            throw new BookException("CANNOT FIND BOOK WITH GIVEN ID" + bookId);
        }
    }


    public boolean subscribeBook(String bookId, Reader reader) throws BookException{

        List<Book> subscribedBooks = new ArrayList<>();
        Subscription subscription = new Subscription();
        boolean subscriptionFlag = true;

        if (bookId != null && !bookId.isEmpty() && !reader.getEmailId().isEmpty()) {
            Optional<Book> book = bookRepo.findById(Integer.parseInt(bookId));
            List<Subscription> subscribe = subscriptionRepo.findByEmailId(reader.getEmailId());
            if (book.isPresent()) {
                if(!subscribe.isEmpty()) {
                    for (Subscription value : subscribe) {
                        for (int j = 0; j < value.getBook().size(); j++) {
                            if (value.getBook().get(j).getBookID() == Integer.parseInt(bookId)) {
                                subscriptionFlag = false;

                            }
                        }
                    }
                }
                if(subscriptionFlag){
                    subscribedBooks.add(book.get());
                    subscription.setBook(subscribedBooks);
                    subscription.setEmailID(reader.getEmailId());
                    subscriptionRepo.save(subscription);
                    return true;
                }
            }
            else {
                throw new BookException("CANNOT FIND BOOK WITH GIVEN ID" + bookId);
            }
        }
        else {
            throw new BookException("Value missing for book ID" + bookId + " and reader");
        }


        return subscriptionFlag;
    }


    public List<Book> getAllSubscribedBooks(String emailId) throws BookException {
        List<Book> book = new ArrayList<>();
        List<Subscription> subscribedBooks = subscriptionRepo.findByEmailId(emailId);
        if (subscribedBooks != null && !subscribedBooks.isEmpty()) {
            for (Subscription value : subscribedBooks) {
                book.addAll(value.getBook());
            }
        }
        else {
            throw new BookException("CANNOT FIND BOOKS For GIVEN EmailID" + emailId);
        }
        return book;
    }


    public Book getSubscribedBook(String emailId, String subscriptionId) throws BookException {

        List<Subscription> subscribedBooks = subscriptionRepo.findByEmailId(emailId);
        Book book = null;
        if (!emailId.isEmpty() && !subscriptionId.isEmpty()) {
            if (subscribedBooks != null && !subscribedBooks.isEmpty()) {
                for (Subscription value : subscribedBooks) {
                    for (int j = 0; j < value.getBook().size(); j++) {
                        if (value.getBook().get(j).getBookID() == Integer.parseInt(subscriptionId)) {
                            book = value.getBook().get(j);
                        }
                    }
                }
            } else {
                throw new BookException("CANNOT FIND BOOK WITH GIVEN ID" + subscriptionId);
            }
        } else {
            throw new BookException("Email and Subscription IDs are missing");
        }
        return book;
    }
}
