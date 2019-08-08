package com.oglcnarbc.bookstore.bookstoreapi.api.controller;

import com.oglcnarbc.bookstore.bookstoreapi.api.BookStoreApi;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.request.CreateAuthorRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.response.CreateAuthorResponse;
import com.oglcnarbc.bookstore.bookstoreapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@BookStoreApi
@RequiredArgsConstructor
public class AuthorController {

   private final AuthorService authorService;

   @PostMapping("api/authors")
   @ResponseStatus(HttpStatus.CREATED)
   public CreateAuthorResponse createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
      return authorService.create(createAuthorRequest);
   }
}
