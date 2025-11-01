package com.msa.rental.framework.web.dto;


import com.msa.rental.domain.model.RentalItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RentItemOutputDTO {

    private Integer itemNo;
    private String itemTitle;
    private LocalDate rentDate;
    private boolean overdue;
    private LocalDate overdueDate; //반납 예정일

    public static RentItemOutputDTO mapToDTO(RentalItem rentItem) {
        RentItemOutputDTO rentItemOutputDTO = new RentItemOutputDTO();
        rentItemOutputDTO.setItemNo(rentItem.getItem().getId());
        rentItemOutputDTO.setItemTitle(rentItem.getItem().getTitle());
        rentItemOutputDTO.setRentDate(rentItem.getRentalDate());
        rentItemOutputDTO.setOverdue(rentItem.isOverdue());
        rentItemOutputDTO.setOverdueDate(rentItem.getOverdueDate());
        return rentItemOutputDTO;
    }
}
