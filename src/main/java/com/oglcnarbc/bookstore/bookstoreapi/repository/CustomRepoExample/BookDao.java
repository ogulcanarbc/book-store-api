package com.oglcnarbc.bookstore.bookstoreapi.repository.CustomRepoExample;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAllByParams(String name, String isbn);
}
