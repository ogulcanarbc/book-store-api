package com.oglcnarbc.bookstore.bookstoreapi.model.book.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateBookRequest {

    private Long id; // bu id olmamalı, uçur her yerden
    private String name;
    private String isbn;
    private List<Long> authorIds;
    private List<Long> categoryIds;
}
