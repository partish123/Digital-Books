package com.digitalbooks.services;

import com.digitalbooks.models.Book;
import com.digitalbooks.models.CreateBookRequest;
import com.digitalbooks.models.Reader;
import com.digitalbooks.models.UpdateBookRequest;
import com.digitalbooks.payload.response.MessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class RestApiCall {
    private static final String BOOK_URL = "http://localhost:8082/books/";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> createBook(String url, CreateBookRequest book) {
        String result = restTemplate.postForObject(BOOK_URL+url, book, String.class);
        assert result != null;
        return ResponseEntity.ok(result);
    }


    public ResponseEntity<?> updateForBook(String url, UpdateBookRequest book) {
        String result = restTemplate.postForObject(BOOK_URL+url, book, String.class);
        assert result != null;
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> searchBook(String search, String category, String title, String author, String price, String publisher) {
        List<Book> result = restTemplate.getForObject(BOOK_URL+"search?category={category}&title={title}&author={author}&price={price}&publisher={publisher}", List.class,category,title, author, price, publisher);
        assert result != null;
        return ResponseEntity.ok(result);

    }


    public ResponseEntity<String> subscribeBook(String url, Reader reader) {
        String result = restTemplate.postForObject(BOOK_URL+url, reader, String.class);
        assert result != null;
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> getAllSubscribeBooksByReader(String url, String emailId) {
        List<Book> result = restTemplate.getForObject(BOOK_URL+url, List.class,emailId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> getSubscribeBookByReaderEmailId(String url, String emailId, String subscriptionId) {
        List<Book> result = restTemplate.getForObject(BOOK_URL+url, List.class );
        assert result != null;
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> getSubscribeBookByReader(String url) {
        String result = restTemplate.getForObject(BOOK_URL+url,  String.class);
        assert result != null;
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> cancleSubscriptionWithIn24Hours(String url,String subscriptionId) {
        String result = restTemplate.postForObject(BOOK_URL+url,  subscriptionId,String.class);
        assert result != null;
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> blockOrUnBlockBookByAuthor(String url, String authorId, String bookId, String block) {
        String result = restTemplate.postForObject(BOOK_URL+url,null, String.class,authorId,bookId,block);
        assert result != null;
        return ResponseEntity.ok(result);
    }

}
