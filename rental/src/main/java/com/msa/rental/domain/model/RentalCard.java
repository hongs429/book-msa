package com.msa.rental.domain.model;


import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentalStatus rentalStatus;
    private LateFee totalLateFee;
    private List<RentalItem> rentalItems = new ArrayList<>();
    private List<RentalItem> returnItems = new ArrayList<>();

    private void addRentalItem(RentalItem rentalItem) {
        rentalItems.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        rentalItems.remove(rentalItem);
    }

    private void addReturnItem(RentalItem returnItem) {
        returnItems.add(returnItem);
    }

    private void removeReturnItem(RentalItem returnItem) {
        returnItems.remove(returnItem);
    }
}
