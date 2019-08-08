package com.oglcnarbc.bookstore.bookstoreapi.model.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorMeta {

    private Long id;
    private String name;
    private String surname;
}
