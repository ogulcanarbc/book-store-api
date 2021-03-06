package com.oglcnarbc.bookstore.bookstoreapi.repository;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<List<Author>> findAllById(List<Long> id);
    List<Author> getAuthorsByIdIn(List<Long> id);

}
