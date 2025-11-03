package com.msa.rental.domain.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReturnItem {

    @Embedded
    private RentalItem rentalItem;
    private LocalDate returnDate;

    public static ReturnItem of(RentalItem rentalItem) {
        return new ReturnItem(
                rentalItem,
                LocalDate.now()
        );
    }
}
