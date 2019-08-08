package com.oglcnarbc.bookstore.bookstoreapi.common.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
