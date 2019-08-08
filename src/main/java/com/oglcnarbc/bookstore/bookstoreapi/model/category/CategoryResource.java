package com.oglcnarbc.bookstore.bookstoreapi.model.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResource {

    private Long id;

    private String name;

    private CategoryMeta parent;

}
