package com.oglcnarbc.bookstore.bookstoreapi.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books", schema = "book_store")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "isbn", nullable = false)
    private String isbn;


    @ManyToMany
    @JoinTable(
            name="book_authors",
            schema = "book_store",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = {@JoinColumn(name="author_id")}

    )
    List<Author> authors;


    @ManyToMany
    @JoinTable(
            name="book_categories",
            schema = "book_store",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = {@JoinColumn(name="category_id")}

    )
    List<Category> categories;

    @ManyToMany(mappedBy = "books") //mapper'lar değişecek bunu ekledik
            List<BookStore> bookStores;
}
