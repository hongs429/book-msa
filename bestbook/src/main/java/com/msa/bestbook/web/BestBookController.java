package com.msa.bestbook.web;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.msa.bestbook.domain.BestBookService;
import com.msa.bestbook.domain.model.BestBook;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BestBookController {

    private final BestBookService bestBookService;

    @GetMapping("/books")
    public ResponseEntity<List<BestBook>> getAllBooks() {
        List<BestBook> books = bestBookService.getAllBooks();
        return new ResponseEntity<>(books, OK);
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<BestBook> getBookById(@PathVariable("id") String id) {
        Optional<BestBook> bookOptional = bestBookService.getBookById(id);
        return bookOptional.map(book -> new ResponseEntity<>(book,
                        OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }


    @PostMapping("/books")
    public ResponseEntity<BestBook> createBook(@RequestBody BestBook book) {
        BestBook createdBook = bestBookService.saveBook(book);
        return new ResponseEntity<>(createdBook, CREATED);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<BestBook> updateBook(
            @PathVariable("id") String id,
            @RequestBody BestBook book
    ) {
        BestBook updatedBook = bestBookService.updateBook(id, book);
        return updatedBook != null ? new ResponseEntity<>(updatedBook, OK) : new ResponseEntity<>(NOT_FOUND);
    }
}
