package com.oglcnarbc.bookstore.bookstoreapi.model.book.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookNameRequest {

    private String name;
}
