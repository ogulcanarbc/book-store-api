package com.oglcnarbc.bookstore.bookstoreapi.config;

import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.AuthorResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.BookResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.CategoryResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {


    @Bean
    public CategoryResponseMapper categoryResponseMapper(){
        return new CategoryResponseMapper();
    }

    @Bean
    public AuthorResponseMapper authorResponseMapper(){
        return new AuthorResponseMapper();
    }

    @Bean
    public BookResponseMapper bookResponseMapper(AuthorResponseMapper authorResponseMapper, CategoryResponseMapper categoryResponseMapper){
        return new BookResponseMapper(authorResponseMapper, categoryResponseMapper);
    }
}
