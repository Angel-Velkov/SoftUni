package com.example.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {

    private AuthorDto author;

    private Long id;
    private String title;
    private String isbn;
}
