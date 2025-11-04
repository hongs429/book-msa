package com.msa.book.framework.web;


import com.msa.book.application.usecase.AddBookUseCase;
import com.msa.book.application.usecase.InquiryUseCase;
import com.msa.book.application.usecase.MakeAvailableUseCase;
import com.msa.book.application.usecase.MakeUnAvailableUseCase;
import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final AddBookUseCase addBookUseCase;
    private final MakeAvailableUseCase makeAvailableUseCase;
    private final MakeUnAvailableUseCase makeUnAvailableUseCase;
    private final InquiryUseCase inquiryUseCase;


    @PostMapping("/book")
    public ResponseEntity<BookOutputDTO> createBook(@RequestBody BookInfoDTO bookInfoDTO) {
        BookOutputDTO bookOutPutDTO = addBookUseCase.addBook(bookInfoDTO);
        return new ResponseEntity<>(bookOutPutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/book/{no}")
    public ResponseEntity<BookOutputDTO> getBookInfo(@PathVariable("no") Long no) {
        BookOutputDTO bookInfo = inquiryUseCase.getBookInfo(no);
        return bookInfo != null ? ResponseEntity.ok(bookInfo) : ResponseEntity.notFound().build();
    }
}
