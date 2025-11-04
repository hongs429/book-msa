package com.msa.rental.domain.model.vo;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RentalItem {

    @Embedded
    private final Item item;
    private final LocalDate rentalDate;
    private final boolean overdue;
    private final LocalDate overdueDate; //반납 예정일

    public static RentalItem of(Item item) {
        return new RentalItem(
                item,
                LocalDate.now(),
                false,
                LocalDate.now().plusDays(14)
        );
    }
}
