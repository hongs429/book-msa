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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
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
                author,isbn,description,publicationDate,source);

        Book book = new Book();
        book.setTitle(title);
        book.setDesc(bookDesc);
        book.setClassification(classification);
        book.setLocation(location);
        book.setBookStatus(BookStatus.ENTERED);
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
}
