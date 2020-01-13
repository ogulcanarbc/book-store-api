package com.oglcnarbc.bookstore.bookstoreapi.service;

import com.oglcnarbc.bookstore.bookstoreapi.entity.Author;
import com.oglcnarbc.bookstore.bookstoreapi.entity.Book;
import com.oglcnarbc.bookstore.bookstoreapi.entity.Category;
import com.oglcnarbc.bookstore.bookstoreapi.repository.AuthorRepository;
import com.oglcnarbc.bookstore.bookstoreapi.repository.BookRepository;
import com.oglcnarbc.bookstore.bookstoreapi.repository.CategoryRepository;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.InvalidRequestException;
import com.oglcnarbc.bookstore.bookstoreapi.common.exception.UnProcessableEntitiyException;
import com.oglcnarbc.bookstore.bookstoreapi.mapper.response.BookResponseMapper;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.BookResource;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.request.CreateBookRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.request.SearchBookNameRequest;
import com.oglcnarbc.bookstore.bookstoreapi.model.book.response.CreateBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookResponseMapper bookResponseMapper;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public CreateBookResponse create(CreateBookRequest createBookRequest) {

        if (CollectionUtils.isEmpty(createBookRequest.getAuthorIds())){
            //hata bad request - author must be provided gibi bi hata
            log.error("author is null ");
            throw  new InvalidRequestException("author must be provided!");
        }
        List<Author> authors = authorRepository.getAuthorsByIdIn(createBookRequest.getAuthorIds());

        if (authors.size()!=createBookRequest.getAuthorIds().size()){
            // gönderilen author id sayısı eşleşmedi tüm authorlar gelmedi
            //eğer eşit değilse hangi id'ler ile author u bulamadığını hata mesajı içerisine ekle.
            //Unprosesible 422
            log.error("request uthor id size with response id size not equals");
           throw  new UnProcessableEntitiyException("request author id size with response id size not equals");
        }

        List<Category> categories = categoryRepository.getCategoriesByIdIn(createBookRequest.getCategoryIds());

        Book book = Book.builder()
                .id(createBookRequest.getId())
                .name(createBookRequest.getName())
                .isbn(createBookRequest.getIsbn())
                .authors(authors)
                .categories(categories)
                .build();

        Book saveBook = bookRepository.save(book);

        return bookResponseMapper.entity2CreateBookResponse(saveBook);
    }

    public List<BookResource> searchBooksByName(SearchBookNameRequest request) {

        if (request==null || ObjectUtils.isEmpty(request.getName())) {
            log.error("request or request.name is null");
            throw  new InvalidRequestException("request or request name is not empty!");
        }
        if (" ".equals(request.getName())) {
            log.error("request.name is contains space");
            throw new InvalidRequestException("book name must be provided!");
        }
        List<Book> booksByName = bookRepository.findAllByNameContaining(request.getName());

       return bookResponseMapper.entity2BookResource(booksByName);
    }
}
