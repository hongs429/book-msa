package com.msa.book.application.outputport;


import com.msa.book.domain.model.Book;

public interface BookOutputPort {
    Book loadBook(long bookNo);
    Book save(Book book);
}
