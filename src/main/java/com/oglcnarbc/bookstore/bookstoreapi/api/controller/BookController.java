package com.oglcnarbc.bookstore.bookstoreapi.api.controller;


import com.oglcnarbc.bookstore.bookstoreapi.api.BookStoreApi;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.BookResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.request.CreateBookRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.request.SearchBookNameRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.response.CreateBookResponse;
import com.oglcnarbc.bookstore.bookstoreapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@BookStoreApi
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBookResponse createAuthor(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.create(createBookRequest);
    }

    @PostMapping("api/books/search")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResource> getBooksByName(@RequestBody SearchBookNameRequest searchBookNameRequest) {
        return bookService.searchBooksByName(searchBookNameRequest);
    }
}
