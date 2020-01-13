package com.oglcnarbc.bookstore.bookstoreapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book_store", schema = "book_store")
public class BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="book_store_books",
            schema = "book_store",
            joinColumns = @JoinColumn(name = "book_store_id"),
            inverseJoinColumns = {@JoinColumn(name="book_id_store")}

    )
    private List<Book> books;
}
