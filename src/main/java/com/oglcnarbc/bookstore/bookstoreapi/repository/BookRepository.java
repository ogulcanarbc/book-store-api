package com.oglcnarbc.bookstore.bookstoreapi.repository;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Book;
import com.oglcnarbc.bookstore.bookstoreapi.repository.CustomRepoExample.BookDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>, BookDao {

    @Query("select book from Book book where book.name like %:name%") // birden fazla search parametresi ile arama yapılabilmesi için
    List<Book> findAllByNameContaining(String name);
}
