package com.oglcnarbc.bookstore.bookstoreapi.api.controller;

import com.oglcnarbc.bookstore.bookstoreapi.api.BookStoreApi;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.CreateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.SearchCategoryNameRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.UpdateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.CreateCategoryResponse;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.UpdateCategoryResponse;
import com.oglcnarbc.bookstore.bookstoreapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@BookStoreApi
@RequiredArgsConstructor // otomatik olarak constr. oluşturur ve final parametreleri inject eder(öyle bean varsa)
public class CategoryController {

    private final CategoryService categoryService;



    @PostMapping("api/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.create(createCategoryRequest);
    }

    @GetMapping("api/categories")
    @ResponseStatus(HttpStatus.OK)
    public Set<CategoryResource> getAllCategory() {
        return categoryService.getAllCategory();
    }


    @PutMapping("api/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCategoryResponse updateCategoryById(@PathVariable("id") Long id,@Valid @RequestBody UpdateCategoryRequest updateCategoryReuquest) {
        return categoryService.updateCategoryById(updateCategoryReuquest,id );
    }

    @DeleteMapping("api/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable("id") Long categoryIdforDelete) {
        categoryService.deleteCategoryById(categoryIdforDelete);
    }

    @PostMapping("api/categories/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResource> searchCategoryByContainsName(@RequestBody SearchCategoryNameRequest searchCategoryNameRequest) {
       return categoryService.searchCategoryByContainsName(searchCategoryNameRequest);
    }
}
