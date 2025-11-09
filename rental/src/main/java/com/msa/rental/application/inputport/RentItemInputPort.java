package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.RentItemUseCase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.event.ItemRented;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class RentItemInputPort implements RentItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
                .orElseGet(() ->
                        RentalCard.of(IDName.of(rental.getUserId(), rental.getUserNm()))
                );

        Item item = Item.of(rental.getItemId(), rental.getItemTitle());
        rentalCard.rentItem(item);
        RentalCard savedRentalCard = rentalCardOutputPort.save(rentalCard);

        // 대여 이벤트 생성 및 발행
        ItemRented itemRentedEvent = RentalCard.createItemRentedEvent(savedRentalCard.getMember(), item, 10);
        eventOutputPort.occurRentalEvent(itemRentedEvent);

        return RentalCardOutputDTO.mapToDTO(savedRentalCard);

    }
}
