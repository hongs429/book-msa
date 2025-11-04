package com.msa.book.framework.jpaadapter;

import com.msa.book.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
