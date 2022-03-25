package com.example.books.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    private List<BookEntity> books = new ArrayList<>();

    public AuthorEntity(String name) {
        this.name = name;
    }

    public AuthorEntity addBook(BookEntity book) {
        this.books.add(book);
        book.setAuthor(this);

        return this;
    }
}
