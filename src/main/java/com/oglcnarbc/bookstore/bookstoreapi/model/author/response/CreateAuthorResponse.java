package com.oglcnarbc.bookstore.bookstoreapi.model.author.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateAuthorResponse {

    private Long id;
    private String name;
    private String surname;
}
