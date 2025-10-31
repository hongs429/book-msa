package com.msa.rental.domain.model.vo;


import com.msa.rental.domain.model.RentalItem;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReturnItem {

    private final RentalItem rentalItem;
    private final LocalDate returnDate;

    public static ReturnItem of(RentalItem rentalItem) {
        return new ReturnItem(
                rentalItem,
                LocalDate.now()
        );
    }
}
