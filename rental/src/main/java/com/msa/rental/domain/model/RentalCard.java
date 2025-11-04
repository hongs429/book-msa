package com.msa.rental.domain.model;


import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalItem;
import com.msa.rental.domain.model.vo.RentalStatus;
import com.msa.rental.domain.model.vo.ReturnItem;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 어그리거트
 */
@Entity
@Table(name = "rental_card")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    @EmbeddedId
    private RentalCardNo rentalCardNo;

    @Embedded
    private IDName member;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @Embedded
    private LateFee totalLateFee;

    @ElementCollection
    @CollectionTable(
            name = "rental_item",
            joinColumns = @JoinColumn(name = "rental_card_no")
    )
    @OrderColumn(name = "item_order")
    private List<RentalItem> rentalItems = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "return_item",
            joinColumns = @JoinColumn(name = "rental_card_no")
    )
    @OrderColumn(name = "item_order")
    private List<ReturnItem> returnItems = new ArrayList<>();

    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItems.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItems.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItems.add(returnItem);
    }

    private void removeReturnItem(ReturnItem returnItem) {
        this.returnItems.remove(returnItem);
    }

    // 대여카드 생성
    public static RentalCard of(IDName creator) {
        RentalCard rentalCard = new RentalCard();

        rentalCard.rentalCardNo = RentalCardNo.of();
        rentalCard.member = creator;
        rentalCard.rentalStatus = RentalStatus.RENT_AVAILABLE;
        rentalCard.totalLateFee = LateFee.createLateFee();
        rentalCard.rentalItems = new ArrayList<>();
        rentalCard.returnItems = new ArrayList<>();

        return rentalCard;
    }

    // 대여처리
    public RentalCard rentItem(Item item) {
        checkRentAvailable();
        this.addRentalItem(RentalItem.of(item));
        return this;
    }

    public RentalCard returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = this.rentalItems.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .orElse(null);

        if (rentalItem == null) {
            return this;
        }

        calculateLateFee(rentalItem, returnDate);

        addReturnItem(ReturnItem.of(rentalItem));
        removeRentalItem(rentalItem);
        return this;
    }

    public RentalCard overdueItem(Item item) {
        // RentalItem이 불변이므로 리스트에서 찾아서 새로운 불변 객체로 교체
        for (int i = 0; i < this.rentalItems.size(); i++) {
            RentalItem rentalItem = this.rentalItems.get(i);
            if (rentalItem.getItem().equals(item)) {
                // 새로운 연체된 RentalItem 생성
                RentalItem overdueRentalItem = new RentalItem(
                        rentalItem.getItem(),
                        rentalItem.getRentalDate(),
                        true,  // overdue = true
                        LocalDate.now().minusDays(1)
                );

                // 리스트에서 교체
                this.rentalItems.set(i, overdueRentalItem);
                this.rentalStatus = RentalStatus.RENT_UNAVAILABLE;
                break;
            }
        }

        return this;
    }


    public long makeAvailableRental(long point) {
        if (!this.rentalItems.isEmpty()) {
            throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다");
        }

        if (this.totalLateFee.getPoint() != point) {
            throw new IllegalArgumentException("포인트로 연체 해제 불가능");
        }

        this.totalLateFee = totalLateFee.subtractPoint(point);

        if (this.totalLateFee.getPoint() == 0) {
            this.rentalStatus = RentalStatus.RENT_AVAILABLE;
        }

        return this.totalLateFee.getPoint();
    }




    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        if (returnDate.isAfter(rentalItem.getOverdueDate())) {
            long point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10L;

            this.totalLateFee = this.totalLateFee.addPoint(point);
        }
    }

    private void checkRentAvailable() {
        if (this.rentalStatus == RentalStatus.RENT_UNAVAILABLE) {
            throw new IllegalStateException("Rental card has been unavailable");
        }

        if (this.rentalItems.size() > 5) {
            throw new IllegalStateException("Rental card has more than 5 Items");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentalCard)) return false;
        RentalCard that = (RentalCard) o;
        return rentalCardNo != null && Objects.equals(rentalCardNo, that.rentalCardNo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
