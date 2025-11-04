package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.application.usecase.AddBookUseCase;
import com.msa.book.domain.model.Book;
import com.msa.book.domain.vo.BookClassification;
import com.msa.book.domain.vo.BookSource;
import com.msa.book.domain.vo.Location;
import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class AddBookInputPort implements AddBookUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO addBook(BookInfoDTO bookInfoDTO) {
        Book book = Book.enterBook(
                bookInfoDTO.getTitle(),
                bookInfoDTO.getAuthor(),
                bookInfoDTO.getIsbn(),
                bookInfoDTO.getDescription(),
                bookInfoDTO.getPublicationDate(),
                BookSource.valueOf(bookInfoDTO.getSource()),
                BookClassification.valueOf(bookInfoDTO.getClassfication()),
                Location.valueOf(bookInfoDTO.getLocation())
        );

        bookOutputPort.save(book);

        return BookOutputDTO.mapToDTO(book);
    }
}
