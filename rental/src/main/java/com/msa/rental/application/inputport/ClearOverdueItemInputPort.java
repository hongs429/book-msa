package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.ClearOverdueItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.event.OverdueCleared;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClearOverdueItemInputPort implements ClearOverdueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 없습니다"));

        rentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());

        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);

        OverdueCleared overdueClearedEvent
                = RentalCard.createOverdueClearedEvent(savedRentalCard.getMember(), clearOverdueInfoDTO.getPoint());
        eventOutputPort.occurOverdueClearedEvent(overdueClearedEvent);

        return RentalResultOutputDTO.mapToDTO(savedRentalCard);
    }
}
