package com.msa.rental.framework.web.dto;


import com.msa.rental.domain.model.ReturnItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReturnItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDTO rentItemOutputDTO = new ReturnItemOutputDTO();
        rentItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getId());
        rentItemOutputDTO.setItemTitle(returnItem.getRentalItem().getItem().getTitle());
        rentItemOutputDTO.setReturnDate(returnItem.getReturnDate());
        return rentItemOutputDTO;
    }
}
