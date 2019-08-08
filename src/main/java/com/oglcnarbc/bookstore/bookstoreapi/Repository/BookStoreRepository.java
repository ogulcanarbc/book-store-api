package com.oglcnarbc.bookstore.bookstoreapi.Repository;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore,Long> {
}
