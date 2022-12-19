package com.digitalbooks.services;

import com.digitalbooks.models.*;
import com.digitalbooks.payload.response.MessageResponse;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.utility.UserException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestApiCall restClient;
    public Object createBook(CreateBookRequest book, String authorId) throws UserException {
        if (book.getPrice() < 0 || book.getPrice()==0) {
            throw new UserException("Price cant be Negative or Zero!");

        } else if (book.getBookTitle() == null || book.getBookTitle().equalsIgnoreCase("")) {
            throw new UserException("Book Title cant be Empty!");
        } else {
            ResponseEntity<String> createdBook = restClient.createBook("author/" + authorId + "/books", book);
            if (createdBook != null) {
                return createdBook;
            } else {
                throw new UserException("Something went wrong.. Please try after some time!");
            }
        }


    }

    public Object updateBook(UpdateBookRequest book, String authorId, String bookId) throws UserException{
        if (book.getPrice() == 0 || book.getPrice() < 0) {
            throw new UserException("Price cant be  Negative or Zero!");

        } else if (book.getBookTitle() == null || book.getBookTitle().equalsIgnoreCase("")) {
            throw new UserException("Book Title cant be Empty!");
        } else {
            ResponseEntity<?> updatedBook = restClient.updateForBook("author/" + authorId + "/books/" + bookId, book);
            if (updatedBook != null) {
                return updatedBook;
            } else {
                throw new UserException("Something went wrong. Please try after some time!");
            }
        }
    }


    public List<Book> searchBook(String category, String title, String author, String price, String publisher) throws JsonProcessingException{
        ResponseEntity<?> books = restClient.searchBook("search", category, title, author, price, publisher);
        List<Book> bookList = null;
        if (books.getStatusCode().equals(HttpStatus.OK))
            bookList = (List<Book>) books.getBody();
        return bookList;
    }


    public boolean subscribeBook(String bookId, Reader reader) throws NumberFormatException, Exception  {
        boolean subscribe=false;

        User user= getUserById(reader.getReaderId());
        if(user!=null && user.getEmail().equalsIgnoreCase(reader.getEmailId())) {

            ResponseEntity<String> result = restClient.subscribeBook(bookId + "/subscribe", reader);
            System.out.println("subscribe book status code" + result.getStatusCode());
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                subscribe=true;
            }
        }else {
            throw  new UserException("User not valid");
        }

        return subscribe;
    }

    public User getUserById(long readerId) throws Exception {
        Optional<User> user = userRepository.findById(readerId);
        if (!user.isPresent()) {
            throw new Exception("Can not find movie with id: " + readerId);
        } else {
            return user.get();
        }

    }


    public List<Book> getAllSubscribeBooksByReader(String emailId) throws UserException {
        List<Book> bookList = null;
        boolean userExists=userRepository.existsByEmail(emailId);

        if(userExists) {
            ResponseEntity<?> books = restClient.getAllSubscribeBooksByReader("readers/{emailId}/books", emailId);

            if (books.getStatusCode().equals(HttpStatus.OK))
                bookList = (List<Book>) books.getBody();
        }
        else {
            throw new UserException("User Not Valid..!");
        }

        return bookList;
    }

    public Book getSubscribeBookByReaderEmailId(String emailId, String subscriptionId) throws UserException {
        List<Book> bookList = new ArrayList<>();
        boolean userExists=userRepository.existsByEmail(emailId);
        if(userExists) {
            ResponseEntity<?> books = restClient.getSubscribeBookByReaderEmailId("readers/"+emailId+"/books/"+subscriptionId, emailId,subscriptionId);

            if (books.getStatusCode().equals(HttpStatus.OK))
                bookList =  (List<Book>)  books.getBody();
        }
        else {
            throw new UserException("User Not Valid..!");
        }

            return bookList.get(0);



    }


    public String getSubscribeBookByReader(String emailId, String subscriptionId) {
        String content="";
        ResponseEntity<String> result = restClient.getSubscribeBookByReader("readers/"+emailId + "/books/"+subscriptionId+"/read");
        if(result.getStatusCode().equals(HttpStatus.OK))
            content=result.getBody();

        return content;
    }

    public boolean cancleSubscriptionWithIn24Hours(String readerId, String subscriptionId) {
        boolean cancleSub=false;
        ResponseEntity<String> result = restClient.cancleSubscriptionWithIn24Hours("readers/"+readerId + "/books/"+subscriptionId+"/cancel-subscription",subscriptionId);
        if(result.getStatusCode().equals(HttpStatus.OK))
            cancleSub=true;
        return cancleSub;
    }

    public boolean blockOrUnBlockBookByAuthor(String authorId, String bookId, String block) {
        boolean blockOrUnBlock=false;
        ResponseEntity<String> result = restClient.blockOrUnBlockBookByAuthor("author/{authorId}/books/{bookId}/block={block}",authorId,bookId,block);
        if(result.getStatusCode().equals(HttpStatus.OK))
            blockOrUnBlock=true;
        return blockOrUnBlock;
    }
}
