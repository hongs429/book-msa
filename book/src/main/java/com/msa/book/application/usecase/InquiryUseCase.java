package com.msa.book.application.usecase;

import com.msa.book.framework.web.dto.BookOutputDTO;

public interface InquiryUseCase {
    BookOutputDTO getBookInfo(long bookNo);
}
