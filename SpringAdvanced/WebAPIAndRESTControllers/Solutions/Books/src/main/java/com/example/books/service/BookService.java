package com.example.books.service;

import com.example.books.model.dto.BookDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> getAllBooks();

    Optional<BookDto> getBook(Long id);

    Page<BookDto> getBooks(int pageNo, int pageSize, String sortBy);

    long createBook(BookDto bookDto);

    void deleteBook(Long id);
}
