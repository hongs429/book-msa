package com.msa.user.application.inputport;

import com.msa.user.application.outputport.MemberOutputPort;
import com.msa.user.application.usecase.SavePointUseCase;
import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.IDName;
import com.msa.user.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class SavePointInputPort implements SavePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO savePoint(IDName idName, Long point) {
        Member loadMember = memberOutputPort.loadMemberByIdName(idName);
        loadMember.savePoint(point);
        return MemberOutputDTO.mapToDTO(loadMember);
    }
}
