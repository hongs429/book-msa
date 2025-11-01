package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.InqueryUseCase;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class InqueryInputPort implements InqueryUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(RentalCardOutputDTO::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<RentItemOutputDTO> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard ->
                        loadCard.getRentalItems().stream()
                                .map(RentItemOutputDTO::mapToDTO)
                                .toList()
                )
                .orElse(List.of());
    }

    @Override
    public List<ReturnItemOutputDTO> getAllReturnItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard ->
                        loadCard.getReturnItems().stream()
                                .map(ReturnItemOutputDTO::mapToDTO)
                                .toList()
                )
                .orElse(List.of());
    }
}
