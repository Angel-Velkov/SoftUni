package com.example.books.service.impl;

import com.example.books.model.dto.BookDto;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           ModelMapper mapper) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(book -> this.mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> getBook(Long id) {
        return this.bookRepository
                .findById(id)
                .map(book -> this.mapper.map(book, BookDto.class));
    }

    @Override
    public Page<BookDto> getBooks(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return bookRepository
                .findAll(pageable)
                .map(book -> this.mapper.map(book, BookDto.class));
    }

    @Override
    public long createBook(BookDto bookDto) {
        String authorName = bookDto.getAuthor().getName();

        AuthorEntity author = this.authorRepository
                .findByName(authorName)
                .orElse(new AuthorEntity(authorName));

        BookEntity bookEntity = this.mapper.map(bookDto, BookEntity.class);
        bookEntity.setAuthor(author);

        return this.bookRepository.save(bookEntity).getId();
    }

    @Modifying
    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }
}
