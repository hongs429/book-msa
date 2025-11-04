package com.msa.rental;


import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.RentalItem;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ScenarioUnitTest {


    @Test
    void test() {

        IDName hongs = IDName.of("1", "홍승현");

        RentalCard rentalCard = RentalCard.of(hongs);

        showCardStatus(rentalCard);

        Item item1 = Item.of(1, "도서1");
        Item item2 = Item.of(2, "도서2");

        rendAndShowStatus(rentalCard, item1);
        rendAndShowStatus(rentalCard, item2);
        returnAndShowStatus(rentalCard, item1);
        overdueAndShowStatus(rentalCard, item2);
        returnAndShowStatus(rentalCard, item2);
        makeAvailableAndShowStatus(rentalCard);

    }

    private void makeAvailableAndShowStatus(RentalCard rentalCard) {
        System.out.println("현재 남은 연체료는 " + rentalCard.getTotalLateFee().getPoint());
        rentalCard.makeAvailableRental(rentalCard.getTotalLateFee().getPoint());
        System.out.println("정지해제 처리");
        showCardStatus(rentalCard);
    }

    private void overdueAndShowStatus(RentalCard rentalCard, Item item) {
        rentalCard.overdueItem(item);
        System.out.println(" 도서명: " + item.getTitle() + " 강제 연체");
        showCardStatus(rentalCard);
    }

    private void returnAndShowStatus(RentalCard rentalCard, Item item) {
        rentalCard.returnItem(item, LocalDate.now());
        System.out.println(" 도서명: " + item.getTitle() + " 반납됨");
        showCardStatus(rentalCard);
    }

    private void rendAndShowStatus(RentalCard rentalCard, Item item) {
        rentalCard.rentItem(item);
        System.out.println(" 도서명: " + item.getTitle() + " 대여됨");
        showCardStatus(rentalCard);
    }

    private void showCardStatus(RentalCard card) {
        System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
        System.out.println(" " + card.getMember().getName() + " 도서카드 ");
        System.out.println(" 대여도서 연체상태 : " + card.getRentalItems().stream().map(RentalItem::isOverdue).toList());
        System.out.println(" 총 연체료 : " + card.getTotalLateFee().getPoint());
        System.out.println(" 대여가능 여부 : " + card.getRentalStatus().toString());
        System.out.println(" 대여 목록 ");
        System.out.println(
                card.getRentalItems().stream()
                        .map(RentalItem::getItem)
                        .map(Item::getTitle)
                        .toList()
        );
        System.out.println(" 반납 목록 ");
        System.out.println(
                card.getReturnItems().stream()
                        .map(ReturnItem::getRentalItem)
                        .map(RentalItem::getItem)
                        .map(Item::getTitle)
                        .toList()
        );
        System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
    }
}
