package com.msa.book.domain.model;


import com.msa.book.domain.vo.BookClassification;
import com.msa.book.domain.vo.BookDesc;
import com.msa.book.domain.vo.BookStatus;
import com.msa.book.domain.vo.Location;
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
}
