package com.oglcnarbc.bookstore.bookstoreapi.model.category.request;

import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateCategoryRequest {

    private String name;
    private Long id;
}
