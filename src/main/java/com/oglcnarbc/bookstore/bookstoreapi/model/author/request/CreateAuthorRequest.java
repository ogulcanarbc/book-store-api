package com.oglcnarbc.bookstore.bookstoreapi.model.author.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateAuthorRequest {

    private String name;
    private String surname;


}
