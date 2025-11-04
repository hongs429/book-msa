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
public class ReturnItem {

    @Embedded
    private final RentalItem rentalItem;
    private final LocalDate returnDate;

    public static ReturnItem of(RentalItem rentalItem) {
        return new ReturnItem(
                rentalItem,
                LocalDate.now()
        );
    }
}
