package com.msa.user.application.outputport;

import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.IDName;

public interface MemberOutputPort {
    Member loadMember(Long memberNo);
    Member loadMemberByIdName(IDName idName);
    Member saveMember(Member member);
}
