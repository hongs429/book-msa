package com.msa.rental.framework.web;


import com.msa.rental.application.usecase.ClearOverdueItemUseCase;
import com.msa.rental.application.usecase.CreateRentalCardUseCase;
import com.msa.rental.application.usecase.InqueryUseCase;
import com.msa.rental.application.usecase.OverdueItemUseCase;
import com.msa.rental.application.usecase.RentItemUseCase;
import com.msa.rental.application.usecase.ReturnItemUseCase;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {

    private final RentItemUseCase rentItemUseCase;
    private final ReturnItemUseCase returnItemUseCase;
    private final CreateRentalCardUseCase createRentalCardUseCase;
    private final OverdueItemUseCase overdueItemUseCase;
    private final ClearOverdueItemUseCase clearOverdueItemUseCase;
    private final InqueryUseCase inqueryUseCase;

    @PostMapping("/rentalCards")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(
            @RequestBody UserInputDTO userInputDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createRentalCardUseCase.createRentalCard(userInputDTO));
    }


    @GetMapping("/rentalCards/{userId}")
    public ResponseEntity<RentalCardOutputDTO> getRentalCard(
            @PathVariable String userId
    ) {

        return ResponseEntity.ok(inqueryUseCase.getRentalCard(
                new UserInputDTO(userId, "")
        ));
    }


    @GetMapping("/rentalCards/{userId}/rentbooks")
    public ResponseEntity<List<RentItemOutputDTO>> getAllRentItem(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(
                inqueryUseCase.getAllRentItem(new UserInputDTO(userId, ""))
        );
    }

    @GetMapping("/rentalCards/{userId}/returnbooks")
    public ResponseEntity<List<ReturnItemOutputDTO>> getAllReturnItem(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(
                inqueryUseCase.getAllReturnItem(new UserInputDTO(userId, ""))
        );
    }


    @PostMapping("/rentalCards/actions/rent")
    public ResponseEntity<RentalCardOutputDTO> rentItem(
            @RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = rentItemUseCase.rentItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCards/actions/return")
    public ResponseEntity<RentalCardOutputDTO> returnItem(
            @RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = returnItemUseCase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCards/actions/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdueItem(
            @RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = overdueItemUseCase.overDueItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }

    @PostMapping("/rentalCards/actions/clearoverdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverdueItem(
            @RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) {
        RentalResultOutputDTO rentalResultOutputDTO = clearOverdueItemUseCase.clearOverdue(clearOverdueInfoDTO);
        return ResponseEntity.ok(rentalResultOutputDTO);
    }
}
