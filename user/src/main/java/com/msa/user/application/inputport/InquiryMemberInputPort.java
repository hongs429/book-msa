package com.msa.user.application.inputport;

import com.msa.user.application.outputport.MemberOutputPort;
import com.msa.user.application.usecase.InquiryMemberUseCase;
import com.msa.user.domain.model.Member;
import com.msa.user.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryMemberInputPort implements InquiryMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO getMember(Long memberNo) {
        Member loadMember = memberOutputPort.loadMember(memberNo);
        return MemberOutputDTO.mapToDTO(loadMember);
    }
}
