package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;

public interface ClearOverdueItemUseCase {
    RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO);
}
