package com.oglcnarbc.bookstore.bookstoreapi.Repository.CustomRepoExample;

import com.oglcnarbc.bookstore.bookstoreapi.Entity.Book;

import java.util.List;

public class BookDaoImpl implements BookDao{

    @Override
    public List<Book> findAllByParams(String name, String isbn) {
        /*Criteria criteria = new CriteriaBuilderImpl(null)
                .createQuery()
                .from(bok)
                .where(name.eqau(na))
                .and(isbn.eq(isbn))
                .list(Book.class);
                */
        return null;
    }
}
