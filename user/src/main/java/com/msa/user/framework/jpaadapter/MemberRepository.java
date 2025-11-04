package com.msa.user.framework.jpaadapter;

import com.msa.user.domain.model.Member;
import com.msa.user.domain.vo.IDName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdName(IDName idName);
}
