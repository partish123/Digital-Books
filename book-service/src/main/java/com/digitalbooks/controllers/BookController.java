package com.digitalbooks.controllers;

import com.digitalbooks.model.Book;
import com.digitalbooks.payload.CreateBookRequest;
import com.digitalbooks.payload.UpdateBookRequest;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.services.BookService;
import com.digitalbooks.utility.BookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/digitalbooks")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService service;

    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false ,defaultValue = "") String title, @RequestParam(required = false,defaultValue = "")
    String author, @RequestParam(defaultValue ="0") String price, @RequestParam(required = false,defaultValue = "") String publisher) throws BookException {
      try{
          logger.info("Inside search book method");

          List<Book> books=service.searchBook(title,author,price,publisher);

          return new ResponseEntity<>(books, HttpStatus.OK);
      }
      catch (Exception e){
          throw new BookException("Sorry something went wrong in search book functionality",e);
      }

    }

        @PostMapping("/author/{author-id}/books")
        public ResponseEntity<?> createBook(@Valid @PathVariable("author-id") int authorId, @RequestBody CreateBookRequest payload) throws BookException {
            try {
                LocalDate date = LocalDate.now();
                Book book = new Book(payload.getBookTitle(), payload.getBookCode(), authorId, payload.getCategory(), payload.getPrice(), payload.getPublisher(), date, date, true, payload.getBookcontent());
                service.createbook(book);
                return new ResponseEntity<>("Book is added successfully", HttpStatus.CREATED);
            }catch (Exception e){
                throw new BookException("Sorry something went wrong in create book functionality",e);
            }
        }

    @PutMapping("/author/{author-id}/books/{book-id}")
    public ResponseEntity<?> updateBook(@Valid @PathVariable("author-id") int authorId, @Valid @PathVariable("book-id") int bookId,@RequestBody UpdateBookRequest payload) throws BookException {
        try{
            LocalDate date = LocalDate.now();
            Book book = new Book(payload.getBookTitle(), bookId, authorId, payload.getCategory(), payload.getPrice(), payload.getPublisher(), date, date, payload.isActive(), payload.getBookcontent());
            service.updateBook(book , authorId , bookId);
            return new ResponseEntity<>("Book is updated successfully",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new BookException("Sorry something went wrong in update book functionality",e);
        }

    }


    @PostMapping("author/{authorId}/books/{bookId}")
    public ResponseEntity<String> blockOrUnBlockBook(@PathVariable(value="authorId") String authorId,@PathVariable(value="bookId") String bookId,@RequestParam(defaultValue ="") String block) throws BookException {
        if(block!=null && !block.isEmpty() && authorId!=null && bookId!=null) {
            try {
                boolean status = service.blockOrUnBlockBookByAuthor(authorId,bookId,block);
                if(status) {
                    if(block.equalsIgnoreCase("Yes"))
                        return ResponseEntity.status(200).body("SUCCESSFULLY_BLOCKED");
                    else
                        return ResponseEntity.status(200).body("SUCCESSFULLY_UNBLOCKED");
                }
                else {
                    throw new BookException("Sorry something went wrong in block/unblock book functionality");
                }
            }catch(Exception e) {
                throw new BookException("Sorry something went wrong in block/unblock book functionality",e);
            }
        }
        else {
            return ResponseEntity.status(400).body("Missing data for authorId:"+ authorId +" bookId: "+bookId+" block: "+block);
        }
    }


//    @PostMapping("/{bookId}/subscribe")
//    public  ResponseEntity<String> subscribeBook(@RequestBody Reader reader, @PathVariable String bookId) throws BookExceptionHandler{
//        try {
//            boolean status= service.subscribeBook(bookId,reader);
//            if(status)
//                return ResponseEntity.status(200).body("SUBSCRIBED SUCCESSFULLY");
//            else
//                throw new BookExceptionHandler("BOOK_IS_ALREADY_SUBSCRIBED");
//
//        }catch(Exception e) {
//            throw new BookExceptionHandler("Sorry something went wrong",e);
//        }
//    }
//
//
//    @GetMapping("/readers/{emailId}/books")
//    public ResponseEntity<List<Book>> getAllSubscribedBooks(@Valid @PathVariable String emailId) throws BookExceptionHandler{
//        if(emailId!=null && !emailId.isEmpty()) {
//            try {
//                List<Book> subscribedBooks=service.getAllSubscribedBooks(emailId);
//                return new ResponseEntity<>(subscribedBooks,HttpStatus.OK);
//            }catch(Exception e) {
//                throw new BookExceptionHandler("Sorry something went wrong",e);
//            }
//        }
//       else {
//            throw new BookExceptionHandler("DATA_MISSING");
//        }
//
//    }
//
//
//    @GetMapping("/readers/{emailId}/books/{subscriptionId}")
//    public ResponseEntity<Book> getSubscribedBook(@PathVariable String emailId,@PathVariable String subscriptionId) throws BookExceptionHandler{
//        if(emailId!=null&&!emailId.isEmpty()) {
//            try {
//                Book subscribedBook = service.getSubscribedBook(emailId,subscriptionId);
//                return new ResponseEntity<>(subscribedBook,HttpStatus.OK);
//            }catch(Exception e) {
//                throw new BookExceptionHandler("Sorry something went wrong",e);
//            }
//        }
//        else {
//            throw new BookExceptionHandler("Parameters are missing");
//        }
//    }

}
