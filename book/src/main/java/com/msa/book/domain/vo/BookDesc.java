package com.msa.book.domain.vo;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class BookDesc {
    private final String description;
    private final String author;
    private final String isbn;
    private final LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    private final BookSource source;

    public static BookDesc createBookDesc(String author,
                                          String isbn,
                                          String description,
                                          LocalDate publicationDate,
                                          BookSource source) {
        return new BookDesc(description, author, isbn, publicationDate, source);
    }
}
