package com.msa.user.framework.jpaadapter;

import com.msa.user.application.outputport.MemberOutputPort;
import com.msa.user.application.usecase.UsePointUseCase;
import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.IDName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class MemberAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Member loadMember(Long memberNo) {
        return memberRepository.findById(memberNo).orElse(null);
    }

    @Override
    public Member loadMemberByIdName(IDName idName) {
        return  memberRepository.findByIdName(idName).orElse(null);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
