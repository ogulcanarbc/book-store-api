package com.oglcnarbc.bookstore.bookstoreapi.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.model.category.request.CreateCategoryRequest;
import com.oglcnarbc.bookstore.bookstoreapi.service.CategoryService;
import com.sun.xml.internal.xsom.impl.Ref;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    @SneakyThrows // runtime'dan exception'dan türemeyen / checked exceptionları run time exception gibi fırlatır. throw ya da try-catch kullanmana gerek kalmaz
    public void createCategory_should_return_InvalidRequestException_when_request_is_invalid()  {
        //given
        final CreateCategoryRequest request = CreateCategoryRequest.builder().build();
        doThrow(new InvalidRequestException("Invalid Request")).when(categoryService).create(any(CreateCategoryRequest.class));
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //Then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Request"))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());

    }

}