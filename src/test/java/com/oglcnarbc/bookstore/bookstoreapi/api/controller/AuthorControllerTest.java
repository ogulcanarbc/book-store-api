package com.oglcnarbc.bookstore.bookstoreapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.request.CreateAuthorRequest;
import com.oglcnarbc.bookstore.bookstoreapi.service.AuthorService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //controller testi olduğu için spring contex'i ayağa kalkması gerekir ondan Spring Runner
@WebMvcTest(AuthorController.class) //sadece AuthorController için spring contex'i ayağa kaldıracak
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    @SneakyThrows
    public void createAuthor_should_return_InvalidRequestException_when_request_is_invalid() {
        //given
        final CreateAuthorRequest authorRequest = CreateAuthorRequest.builder().build();

        //when
        when(authorService.create(any(CreateAuthorRequest.class))).thenThrow(new InvalidRequestException("Invalid Request"));


        ResultActions resultActions = mockMvc.perform(
                post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(authorRequest))
        ).andDo(print());

        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Invalid Request"))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
        //then
    }
}