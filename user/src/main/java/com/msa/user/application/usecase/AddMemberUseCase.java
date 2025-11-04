package com.msa.user.application.usecase;

import com.msa.user.framework.web.dto.MemberInfoDTO;
import com.msa.user.framework.web.dto.MemberOutputDTO;

public interface AddMemberUseCase {
    MemberOutputDTO addMember(MemberInfoDTO memberInfoDTO);
}
