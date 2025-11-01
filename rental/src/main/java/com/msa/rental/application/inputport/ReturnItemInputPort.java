package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnItemInputPort implements ReturnItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 없습니다"));

        rentalCard.returnItem(
                Item.of(returnDTO.getItemId(), returnDTO.getItemTitle()), LocalDate.now()
        );

        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
