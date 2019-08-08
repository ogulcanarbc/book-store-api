package com.oglcnarbc.bookstore.bookstoreapi.common.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private Integer status;
    private LocalDate timestamp;
}
