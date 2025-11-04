package com.msa.user.application.usecase;

import com.msa.user.domain.vo.IDName;
import com.msa.user.framework.web.dto.MemberOutputDTO;

public interface UsePointUseCase {
    MemberOutputDTO userPoint(IDName idName, long point);
}
