package com.oglcnarbc.bookstore.bookstoreapi.repository;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);

    //Boolean existsByParent(Category parent);

    List<Category> getCategoriesByIdIn(List<Long> id);

    @Query("select category from Category category where category.name like %:name%")
    List<Category> findAllByNameContaining(String name);


}
