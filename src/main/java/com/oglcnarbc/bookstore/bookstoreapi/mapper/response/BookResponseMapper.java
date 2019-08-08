package com.oglcnarbc.bookstore.bookstoreapi.mapper.response;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Book;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.BookResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.response.CreateBookResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookResponseMapper {


    private final AuthorResponseMapper authorResponseMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    public CreateBookResponse entity2CreateBookResponse(Book savedBook) {
        return CreateBookResponse.builder()
                .id(savedBook.getId())
                .bookName(savedBook.getName())
                .isbn(savedBook.getIsbn())
                .authors(authorResponseMapper.entity2AuthorMeta(savedBook.getAuthors()))
                .categories(categoryResponseMapper.entity2CategoryMeta(savedBook.getCategories()))
                .build();
    }


    public BookResource entity2BookResource(Book book) {
        return BookResource
                .builder()
                .id(book.getId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .authors(authorResponseMapper.entity2AuthorMeta(book.getAuthors()))
                .categories(categoryResponseMapper.entity2CategoryMeta(book.getCategories()))
                .build();
    }

    public List<BookResource> entity2BookResource(List<Book> books) {
        return books
                .stream()
                .map(this::entity2BookResource)
                .collect(Collectors.toList());
    }
}
