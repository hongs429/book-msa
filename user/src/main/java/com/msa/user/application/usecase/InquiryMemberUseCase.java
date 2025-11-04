package com.msa.user.application.usecase;

import com.msa.user.framework.web.dto.MemberOutputDTO;

public interface InquiryMemberUseCase {
    MemberOutputDTO getMember(Long memberNo);
}
