package com.msa.book.domain.vo;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDesc {
    private String description;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDate publicationDate;
    private BookSource source;

}
