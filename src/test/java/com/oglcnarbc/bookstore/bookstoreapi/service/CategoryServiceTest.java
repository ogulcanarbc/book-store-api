package com.oglcnarbc.bookstore.bookstoreapi.service;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.Repository.CategoryRepository;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.ConflictException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.NoContentException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.UnProcessableEntitiyException;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.CategoryResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.CategoryMeta;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.CreateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.response.CreateCategoryResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryResponseMapper categoryResponseMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * {@link CategoryService#create(CreateCategoryRequest)}
     */

    @Test
    public void create_should_throw_InvalidRequestException_when_request_object_is_null() {
        //given
        CreateCategoryRequest request = null;
        expectedException.expect(InvalidRequestException.class);
        expectedException.expectMessage("Invalid create category request");
        //when
        categoryService.create(request);
        //then
    }

    /**
     * {@link CategoryService#create(CreateCategoryRequest)}
     */

    @Test
    public void create_should_throw_InvalidRequestException_when_request_name_is_empty() {
        //given
        CreateCategoryRequest request = CreateCategoryRequest.builder().build();
        expectedException.expect(InvalidRequestException.class);
        expectedException.expectMessage("Invalid create category request");
        //when
        categoryService.create(request);
        //then
    }

    /**
     * {@link CategoryService#create(CreateCategoryRequest)}
     */

    @Test
    public void create_should_throw_UnprocessableEntitiyException_when_no_parent_category_found_by_id() {
        //given
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("Test Category With Invalid Parent")
                .id(1L)
                .build();

        doReturn(Optional.empty()).when(categoryRepository).findById(anyLong());
        expectedException.expect(UnProcessableEntitiyException.class);
        expectedException.expectMessage("Parent Category Not Found!");
        //when
        categoryService.create(request);

    }

    /**
     * {@link CategoryService#create(CreateCategoryRequest)}
     */

    @Test
    public void create_should_throw_ConflictException_when_a_category_already_exist_with_same_name() {
        //given
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("Exist Name")
                .build();

        //bana herhangi bir category objesi dönmeli
        doReturn(Optional.of(Category.builder().build())).when(categoryRepository).findByName(anyString());
        expectedException.expect(ConflictException.class);
        expectedException.expectMessage(request.getName() + " category can not be created because " + request.getName() + " is exist!");
        //when
        categoryService.create(request);

    }

    /**
     * {@link CategoryService#create(CreateCategoryRequest)}
     */

    @Test
    public void create_should_create_category_and_return_create_category_response() {
        //given
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("Test Category")
                .id(1L)
                .build();

        final Category parentCategory = Category.builder()
                .id(1L)
                .name("Test Parent Category")
                .build();

        final CreateCategoryResponse expected = CreateCategoryResponse.builder()
                .id(1L)
                .name("Test Parent Category")
                .parent(CategoryMeta.builder().id(1L).name("Test Parent Category").build())
                .build();

        doReturn(Optional.of(parentCategory)).when(categoryRepository).findById(anyLong());
        doReturn(Optional.empty()).when(categoryRepository).findByName(anyString());
        doReturn(Category.builder().build()).when(categoryRepository).save(any(Category.class));
        doReturn(expected).when(categoryResponseMapper).entity2CreateResponse(any(Category.class));

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        //when
        CreateCategoryResponse actual = categoryService.create(request);

        //Then
        verify(categoryRepository, times(1)).save(categoryArgumentCaptor.capture());
        Assert.assertNotNull(categoryArgumentCaptor.getValue());
        Assert.assertEquals(request.getName(), categoryArgumentCaptor.getValue().getName());
        Assert.assertNotNull(categoryArgumentCaptor.getValue().getParent());
        Assert.assertEquals(request.getId(), categoryArgumentCaptor.getValue().getParent().getId());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getParent(), actual.getParent());
        assertEquals(expected.getParent().getId(), actual.getParent().getId());
        assertEquals(expected.getParent().getName(), actual.getParent().getName());
    }


    /**
     * {@link CategoryService#deleteCategoryById(Long)}
     */

    @Test
    public void delete_should_throw_NoContentException_when_request_categoryId_is_empty() {
        //given
        Category categoryId = Category.builder().build();
        doReturn(Optional.empty()).when(categoryRepository).findById(anyLong());

        //when
        expectedException.expect(NoContentException.class);
        expectedException.expectMessage("Category Id Found!");
        categoryService.deleteCategoryById(categoryId.getId());

        //then
    }

    /**
     * {@link CategoryService#deleteCategoryById(Long)}
     */

    @Test
    public void delete_should_throw_ConflictException_when_request_is_exist_categoryId() {
        //given
        final Long categoryId = 1L;
        final Category category = Category.builder()
                .id(1L)
                .build();

        doReturn(Optional.of(category)).when(categoryRepository).findById(anyLong());
        //when(categoryRepository.existsByParent(category)).thenReturn(true);

        //when
        expectedException.expect(ConflictException.class);
        expectedException.expectMessage("Parent category couldn't deleted");
        categoryService.deleteCategoryById(categoryId);

        //then
    }

    /**
     * {@link CategoryService#getAllCategory()}
     */

    @Test
    public void get_should_return_all_category_response() {
        //given


        final Category parentCategory = Category.builder()
                .id(1L)
                .name("Test Parent Category")
                .build();

        final CreateCategoryResponse expected = CreateCategoryResponse.builder()
                .id(1L)
                .name("Test Parent Category")
                .parent(CategoryMeta.builder().id(6L).name("Test Parent Category").build())
                .build();


        doReturn(Category.builder().build()).when(categoryRepository).save(any(Category.class));
        doReturn(expected).when(categoryResponseMapper).entity2CreateResponse(any(Category.class));

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        List<Category> capturedCategory = categoryArgumentCaptor.getAllValues();
        assertNotNull(capturedCategory);
        categoryService.getAllCategory();
        //then
    }

}