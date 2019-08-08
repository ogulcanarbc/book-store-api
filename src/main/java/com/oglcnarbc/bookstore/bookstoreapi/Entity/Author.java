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
@Table(name = "authors", schema = "book_store")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name="surname")
    private String surname;

    @ManyToMany(cascade = CascadeType.REMOVE,mappedBy = "authors")
    List<Book> books;

}
