package com.msa.book.domain.model;


import com.msa.book.domain.vo.BookClassification;
import com.msa.book.domain.vo.BookDesc;
import com.msa.book.domain.vo.BookSource;
import com.msa.book.domain.vo.BookStatus;
import com.msa.book.domain.vo.Location;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String title;

    @Embedded
    private BookDesc desc;

    @Enumerated(EnumType.STRING)
    private BookClassification classification;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Enumerated(EnumType.STRING)
    private Location location;

    public static Book enterBook(String title,
                                 String author,
                                 String isbn,
                                 String description,
                                 LocalDate publicationDate,
                                 BookSource source,
                                 BookClassification classification,
                                 Location location) {

        BookDesc bookDesc = BookDesc.createBookDesc(
                author, isbn, description, publicationDate, source);

        Book book = new Book();
        book.title = title;
        book.desc = bookDesc;
        book.classification = classification;
        book.location = location;
        book.bookStatus = BookStatus.ENTERED;
        return book;
    }

    public Book makeAvailable() {
        this.bookStatus = BookStatus.AVAILABLE;
        return this;
    }

    public Book makeUnavailable() {
        this.bookStatus = BookStatus.UNAVAILABLE;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return no != null && Objects.equals(no, book.no);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
