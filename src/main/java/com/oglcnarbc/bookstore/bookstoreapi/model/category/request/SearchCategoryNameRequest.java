package com.oglcnarbc.bookstore.bookstoreapi.model.category.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SearchCategoryNameRequest {
    private String name;
}
