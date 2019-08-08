package com.oglcnarbc.bookstore.bookstoreapi.Entity;

import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories", schema = "book_store")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "parent_category")
    @ManyToOne
    private Category parent;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;


}
