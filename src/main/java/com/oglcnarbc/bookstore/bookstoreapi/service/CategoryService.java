package com.oglcnarbc.bookstore.bookstoreapi.service;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.repository.CategoryRepository;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.ConflictException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.NoContentException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.UnProcessableEntitiyException;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.CategoryResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.CreateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.SearchCategoryNameRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.UpdateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.CreateCategoryResponse;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.UpdateCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryResponseMapper categoryResponseMapper;
    private final CategoryRepository categoryRepository;

    public CreateCategoryResponse create(CreateCategoryRequest request) {
        if (ObjectUtils.isEmpty(request) || ObjectUtils.isEmpty(request.getName())) {
            log.error("Category couldn't created, request body is empty or invalid");
            throw new InvalidRequestException("Invalid create category request");
        }
        Category parentCategory = null;
        if (request.getParentCategoryId() != null) {
            Optional<Category> parentCategoryOption = categoryRepository.findById(request.getParentCategoryId());
            if (!parentCategoryOption.isPresent()) {
                log.error("Category couldn't created because of no parent found by authorIds: {}", request.getParentCategoryId());
                throw new UnProcessableEntitiyException("Parent Category Not Found!");
            }
            parentCategory = parentCategoryOption.get();
        }
        Optional<Category> categoryName = categoryRepository.findByName(request.getName());
        if (categoryName.isPresent()) {
            log.error("you cannot create a category with the same name is: {}", request.getName());
            throw new ConflictException(request.getName() + " category can not be created because " + request.getName() + " is exist!");
        }
        Category category = Category.builder()
                .name(request.getName())
                .parent(parentCategory)
                .build();

        Category savedCategory = categoryRepository.save(category);
        return categoryResponseMapper.entity2CreateResponse(savedCategory);
    }

    public void deleteCategoryById(Long categoryId) {
        Optional<Category> categorie = categoryRepository.findById(categoryId);
        if (!categorie.isPresent()) {
            log.error("Category couldn't deleted because of no category found by authorIds: {}", categoryId);
            throw new NoContentException("Category Id Found!");
        }
        /*if (categoryRepository.existsByParent(categorie.get())){ //parent kategori bir kategoriye bağlı!
            log.error("Category couldn't deleted by authorIds : {}, because of it's a parent category ", categoryId);
            throw new ConflictException("Parent category couldn't deleted");
        }*/

        categoryRepository.deleteById(categoryId);
    }

    public Set<CategoryResource> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryResponseMapper::entity2Resource)
                .collect(Collectors.toSet());
    }

    public UpdateCategoryResponse updateCategoryById(UpdateCategoryRequest updateCategoryReuquest, Long id) {


        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            log.error("Category couldn't deleted because of no category found by authorIds: {}", id);
            throw new NoContentException(" Category Id Found!");
        }

        Category parentCategory = null;
        if (id != null) {
            Optional<Category> parentCategoryOptional = categoryRepository.findById(updateCategoryReuquest.getId());
            if (!parentCategoryOptional.isPresent()) {
                log.error("Category couldn't update because of no parent found by authorIds: {}", id);
                throw new UnProcessableEntitiyException("Parent Category Not Found!");
            }
            parentCategory = parentCategoryOptional.get();
        }

        final Category category = categoryOptional.get();
        category.setName(updateCategoryReuquest.getName());
        category.setParent(parentCategory);
        Category savedCategory = categoryRepository.save(category);

        return categoryResponseMapper.entity2UpdateResponse(savedCategory);
    }

    public List<CategoryResource> searchCategoryByContainsName(SearchCategoryNameRequest searchCategoryNameRequest) {


       List<Category> categories = categoryRepository.findAllByNameContaining(searchCategoryNameRequest.getName());
      // if (CollectionUtils.isEmpty(categories)
       return categoryResponseMapper.entity2Resource(categories);

    }
}