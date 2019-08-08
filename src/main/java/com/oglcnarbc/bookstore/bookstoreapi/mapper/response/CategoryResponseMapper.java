package com.oglcnarbc.bookstore.bookstoreapi.mapper.response;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.CreateCategoryResponse;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.UpdateCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponseMapper {

    public CreateCategoryResponse entity2CreateResponse(Category entity) {
        return CreateCategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parent(entity.getParent() == null ? null : entitiy2Meta(entity.getParent()))
                .build();
    }

    public UpdateCategoryResponse entity2UpdateResponse(Category entity) {
        return UpdateCategoryResponse.builder()
                .categoryId(entity.getId())
                .name(entity.getName())
                .parent(entity.getParent() == null ? null : entitiy2Meta(entity.getParent()))
                .build();
    }

    public CategoryResource entity2Resource(Category entity) {
        return CategoryResource.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parent(entity.getParent() == null ? null : entitiy2Meta(entity.getParent()))
                .build();
    }

    public List<CategoryResource> entity2Resource(List<Category> entities) {
        return entities
                .stream()
                .map(this::entity2Resource)
                .collect(Collectors.toList());
    }

    private CategoryMeta entitiy2Meta(Category parent) {
        return CategoryMeta.builder()
                .id(parent.getId())
                .name(parent.getName())
                .build();
    }

    public List<CategoryMeta> entity2CategoryMeta(List<Category> categories) {
        return categories
                .stream()
                .map(this::entitiy2Meta)
                .collect(Collectors.toList());
    }
}
