package com.oglcnarbc.bookstore.bookstoreapi.model.category.response;

import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryResponse {

    private Long id;
    private String name;
    private CategoryMeta parent;
}
