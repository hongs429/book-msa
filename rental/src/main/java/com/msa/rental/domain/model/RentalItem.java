package com.msa.rental.domain.model;


import com.msa.rental.domain.model.vo.Item;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalItem {

    private Item item;
    private LocalDate rentalDate;
    private boolean overdue;
    private LocalDate overdueDate; //반납 예정일

    public static RentalItem of(Item item) {
        return new RentalItem(
                item,
                LocalDate.now(),
                false,
                LocalDate.now().plusDays(14)
        );
    }
}
