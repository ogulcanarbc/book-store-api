package com.oglcnarbc.bookstore.bookstoreapi.service;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Author;
import com.oglcnarbc.bookstore.bookstoreapi.repository.AuthorRepository;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.AuthorResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.request.CreateAuthorRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.author.response.CreateAuthorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {


    private final AuthorResponseMapper authorResponseMapper;
    private final AuthorRepository authorRepository;

    //TODO yapılacak
    public CreateAuthorResponse create(CreateAuthorRequest createAuthorRequest) {
        if (ObjectUtils.isEmpty(createAuthorRequest) || ObjectUtils.isEmpty(createAuthorRequest.getName())) {
            log.error("Author couldn't created, request body is empty or invalid");
            throw new InvalidRequestException("Invalid create authors request");
        }

        Author author = Author.builder()
                .name(createAuthorRequest.getName())
                .surname(createAuthorRequest.getSurname())
                .build();
        Author savedAuthor = authorRepository.save(author);
        return authorResponseMapper.entity2CreateAuthorResponse(savedAuthor);
    }
}
