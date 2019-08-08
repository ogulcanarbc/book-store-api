package com.oglcnarbc.bookstore.bookstoreapi.model.book.response;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Author;
import com.oglcnarbc.bookstore.bookstoreapi.Entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.AuthorMeta;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateBookResponse {

    private Long id;
    private String bookName;
    private String isbn;
    private List<AuthorMeta> authors;
    private List<CategoryMeta> categories;
}
