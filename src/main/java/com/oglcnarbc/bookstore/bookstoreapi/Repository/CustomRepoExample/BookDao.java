package com.oglcnarbc.bookstore.bookstoreapi.Repository.CustomRepoExample;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAllByParams(String name, String isbn);
}
