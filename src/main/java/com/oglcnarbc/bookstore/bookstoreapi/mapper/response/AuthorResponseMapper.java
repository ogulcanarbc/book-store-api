package com.oglcnarbc.bookstore.bookstoreapi.mapper.response;


import com.oglcnarbc.bookstore.bookstoreapi.entity.Author;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.AuthorMeta;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.response.CreateAuthorResponse;

import java.util.List;
import java.util.stream.Collectors;


public class AuthorResponseMapper {

    public CreateAuthorResponse entity2CreateAuthorResponse(Author entity) {
        return CreateAuthorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }


    public AuthorMeta entity2AuthorMeta(Author entity) {
        return AuthorMeta.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();

    }

    public List<AuthorMeta> entity2AuthorMeta(List<Author> entitis){
        return entitis.stream().map(this::entity2AuthorMeta).collect(Collectors.toList());
    }


}
