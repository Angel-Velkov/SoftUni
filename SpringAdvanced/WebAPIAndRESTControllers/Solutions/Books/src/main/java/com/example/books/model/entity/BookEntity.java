package com.example.books.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AuthorEntity author;
}
