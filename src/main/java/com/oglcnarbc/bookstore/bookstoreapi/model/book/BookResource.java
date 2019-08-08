package com.oglcnarbc.bookstore.bookstoreapi.model.book;

import com.oglcnarbc.bookstore.bookstoreapi.model.author.AuthorMeta;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResource {

    private Long id;
    private String name;
    private String isbn;
    private List<AuthorMeta> authors;
    private List<CategoryMeta> categories;

}
