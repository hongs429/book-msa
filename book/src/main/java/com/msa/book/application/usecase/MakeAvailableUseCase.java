package com.msa.book.application.usecase;

import com.msa.book.framework.web.dto.BookOutputDTO;

public interface MakeAvailableUseCase {
    BookOutputDTO available(Long bookNo);
}
