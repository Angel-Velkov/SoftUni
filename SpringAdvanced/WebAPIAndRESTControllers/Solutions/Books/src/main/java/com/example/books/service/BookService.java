package com.example.books.service;

import com.example.books.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> getAllBooks();

    Optional<BookDto> getBook(Long id);

    long createBook(BookDto bookDto);

    void deleteBook(Long id);
}
