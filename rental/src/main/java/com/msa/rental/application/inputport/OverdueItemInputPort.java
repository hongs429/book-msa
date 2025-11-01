package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.OverdueItemUseCase;
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
public class OverdueItemInputPort implements OverdueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO overDueItem(UserItemInputDTO returnDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 없습니다"));

        rentalCard.overdueItem(Item.of(returnDTO.getItemId(), returnDTO.getItemTitle()));

        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
