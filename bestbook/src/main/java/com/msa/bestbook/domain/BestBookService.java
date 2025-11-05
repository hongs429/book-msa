package com.msa.bestbook.domain;


import com.msa.bestbook.domain.model.BestBook;
import com.msa.bestbook.domain.vo.Item;
import com.msa.bestbook.persistence.BestBookRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BestBookService {

    private final BestBookRepository bestBookRepository;

    public List<BestBook> getAllBooks() {
        return bestBookRepository.findAll();
    }

    public Optional<BestBook> getBookById(String id) {
        return bestBookRepository.findById(id);
    }

    public void dealBestBook(Item item) {
        BestBook bestBook = bestBookRepository.findBestBookByItem_No(item.getNo());
        if (bestBook != null) {
            bestBook.increaseBestBookCount();
        } else {
            bestBook = BestBook.registerBestBook(item);
        }
        saveBook(bestBook);
    }


    public BestBook updateBook(String id, BestBook book) {
        Optional<BestBook> existingBookOptional = bestBookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            BestBook existingBook = existingBookOptional.get();
            existingBook.setItem(book.getItem());
            existingBook.setRentCount(book.getRentCount());
            return saveBook(existingBook);
        }
        return null;
    }

    public boolean deleteBook(String id) {
        Optional<BestBook> bookOptional = bestBookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bestBookRepository.delete(bookOptional.get());
            return true;
        }
        return false;
    }

    public BestBook saveBook(BestBook book) {
        return bestBookRepository.save(book);
    }
}
