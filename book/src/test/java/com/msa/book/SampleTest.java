package com.msa.book;

import com.msa.book.domain.model.Book;
import com.msa.book.domain.vo.BookClassification;
import com.msa.book.domain.vo.BookSource;
import com.msa.book.domain.vo.Location;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SampleTest {

    @Test
    void test() {
        System.out.println("------------도메인 모델 테스트 진행------------");
        System.out.println("도서1 입고");

        Book book = Book.enterBook( "노인과바다", "훼밍웨이",
                "2312321",
                "주인공 노인과 바다",
                LocalDate.now(),
                BookSource.SUPPLY,
                BookClassification.Literature,
                Location.PANGYO);
        System.out.println(book);

        System.out.println("도서1 입고 --> 대여가능 처리");
        book.makeAvailable();
        System.out.println(book.getBookStatus());
        System.out.println("샘플도서 생성");

        Book book2 = Book.enterBook(
                "타이틀", "저자", "123123", "테스트 도서", LocalDate.now(),
                BookSource.SUPPLY,
                BookClassification.Literature,
                Location.PANGYO
        );

        book2.makeUnavailable();
        System.out.println(book2.getBookStatus());
    }
}
