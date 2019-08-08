package com.oglcnarbc.bookstore.bookstoreapi.model.category.request;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateCategoryRequest {

    private Long id; // create requestinde id olmaz sil bunları
    private String name;
    private Long parentCategoryId;
}
