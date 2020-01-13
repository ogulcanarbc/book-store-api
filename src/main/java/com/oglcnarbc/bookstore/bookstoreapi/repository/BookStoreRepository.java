package com.oglcnarbc.bookstore.bookstoreapi.repository;

import com.oglcnarbc.bookstore.bookstoreapi.entity.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore,Long> {
}
