package com.msa.user.application.inputport;

import com.msa.user.application.outputport.MemberOutputPort;
import com.msa.user.application.usecase.AddMemberUseCase;
import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.Email;
import com.msa.user.domain.vo.IDName;
import com.msa.user.domain.vo.Password;
import com.msa.user.framework.web.dto.MemberInfoDTO;
import com.msa.user.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class AddMemberInputPort implements AddMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO addMember(MemberInfoDTO memberInfoDTO) {
        IDName idName = new
                IDName(memberInfoDTO.getId(),memberInfoDTO.getName());
        Password pwd = new
                Password(memberInfoDTO.getPassWord(),memberInfoDTO.getPassWord());
        Email email = new Email(memberInfoDTO.getEmail());
        Member addedMember = Member.registerMember(idName,pwd,email);
        Member savedMember = memberOutputPort.saveMember(addedMember);
        return MemberOutputDTO.mapToDTO(savedMember);
    }
}
