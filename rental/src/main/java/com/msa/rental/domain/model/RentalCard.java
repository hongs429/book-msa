package com.msa.rental.domain.model;


import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalStatus;
import com.msa.rental.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 어그리거트
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentalStatus rentalStatus;
    private LateFee totalLateFee;
    private List<RentalItem> rentalItems = new ArrayList<>();
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

        rentalCard.setRentalCardNo(RentalCardNo.of());
        rentalCard.setMember(creator);

        rentalCard.setRentalStatus(RentalStatus.RENT_AVAILABLE);
        rentalCard.setTotalLateFee(LateFee.createLateFee());

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
        RentalItem rentalItem = this.rentalItems.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .orElse(null);

        if (rentalItem == null) {
            return this;
        }

        rentalItem.setOverdue(true);
        this.rentalStatus = RentalStatus.RENT_UNAVAILABLE;

        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }


    public long makeAvailableRental(long point) {
        if (!this.rentalItems.isEmpty()) {
            throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다");
        }

        if (this.getTotalLateFee().getPoint() != point) {
            throw new IllegalArgumentException("포인트로 연체 해제 불가능");
        }

        setTotalLateFee(totalLateFee.subtractPoint(point));

        if (this.getTotalLateFee().getPoint() == 0) {
            this.rentalStatus = RentalStatus.RENT_AVAILABLE;
        }

        return this.getTotalLateFee().getPoint();
    }




    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        if (returnDate.isAfter(rentalItem.getOverdueDate())) {
            long point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10L;

            this.setTotalLateFee(this.totalLateFee.addPoint(point));
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





}
