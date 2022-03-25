package com.example.books.web;

import com.example.books.model.dto.BookDto;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper mapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = this.bookService.getAllBooks();

        return ResponseEntity
                .ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        Optional<BookDto> book = this.bookService.getBook(id);

        return book
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build()
                );
    }

    @PutMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, UriComponentsBuilder builder) {

        long bookId = this.bookService.createBook(bookDto);

        URI location = builder
                .path("/books/{id}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
        this.bookService.deleteBook(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}