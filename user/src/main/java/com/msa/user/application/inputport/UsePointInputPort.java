package com.msa.user.application.inputport;

import com.msa.user.application.outputport.MemberOutputPort;
import com.msa.user.application.usecase.UsePointUseCase;
import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.IDName;
import com.msa.user.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class UsePointInputPort implements UsePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO userPoint(IDName idName, long point) {
        Member loadMember = memberOutputPort.loadMemberByIdName(idName);
        loadMember.usePoint(point);
        return MemberOutputDTO.mapToDTO(loadMember);
    }
}
