package springdata.exercises.bookshopsystem.models;

import lombok.*;
import springdata.exercises.bookshopsystem.models.enums.AgeRestriction;
import springdata.exercises.bookshopsystem.models.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @NonNull
    @Column(length = 50, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;

    @NonNull
    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @NonNull
    @Column(nullable = false)
    private int copies;

    @Column(name = "relesae_date")
    private LocalDate releaseDate;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_registration", nullable = false)
    private AgeRestriction ageRestriction;

    public Book(@NonNull String title, Author author, String description,
                @NonNull EditionType editionType, @NonNull BigDecimal price,
                @NonNull int copies, LocalDate releaseDate, @NonNull AgeRestriction ageRestriction) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public Book(String title, Author randomAuthor, EditionType editionType, BigDecimal price, LocalDate releaseDate, AgeRestriction ageRestriction, Set<Category> randomCategories, int copies) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
