package com.msa.book.domain.model;


import com.msa.book.domain.vo.BookClassification;
import com.msa.book.domain.vo.BookDesc;
import com.msa.book.domain.vo.BookSource;
import com.msa.book.domain.vo.BookStatus;
import com.msa.book.domain.vo.Location;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long no;
    private String title;
    private BookDesc desc;
    private BookClassification classification;
    private BookStatus bookStatus;
    private Location location;

    public static Book enterBook(String title,
                                 String author,
                                 String isbn,
                                 String description,
                                 LocalDate publicationDate,
                                 BookSource source,
                                 BookClassification classfication,
                                 Location location) {

        BookDesc bookDesc = BookDesc.createBookDesc(
                author,isbn,description,publicationDate,source);

        Book book = new Book();
        book.setTitle(title);
        book.setDesc(bookDesc);
        book.setClassification(classfication);
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
