package com.digitalbooks.repository;

import com.digitalbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select b from Book b " +
            "where b.bookTitle = ?1 or b.authorID = ?2 or b.publisher = ?3 or b.price = ?4",nativeQuery = true)
    List<Book> searchBooks(String title, String author, String price, String publisher);

}
