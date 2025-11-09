package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.event.ItemReturned;
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
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDTO) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 없습니다"));

        Item item = Item.of(returnDTO.getItemId(), returnDTO.getItemTitle());

        rentalCard.returnItem(item, LocalDate.now());

        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);

        ItemReturned itemReturnEvent = RentalCard.createItemReturnEvent(savedRentalCard.getMember(), item, 10);
        eventOutputPort.occurReturnEvent(itemReturnEvent);

        return RentalCardOutputDTO.mapToDTO(savedRentalCard);
    }
}
