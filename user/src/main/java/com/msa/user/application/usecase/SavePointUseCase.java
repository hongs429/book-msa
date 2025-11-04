package com.msa.user.application.usecase;

import com.msa.user.domain.vo.IDName;
import com.msa.user.framework.web.dto.MemberOutputDTO;

public interface SavePointUseCase {
    MemberOutputDTO savePoint(IDName idName, Long point);
}
