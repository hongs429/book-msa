package com.msa.rental.framework.jpaadapter;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.RentalCardNo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardNo> {

    @Query("""
                select r
                    from RentalCard r
                    where r.member.id = :id
            """)
    Optional<RentalCard> findByMemberId(@Param("id") String memberId);

    @Query("""
                select r
                    from RentalCard r
                    where r.rentalCardNo.no = :id
            
            """)
    Optional<RentalCard> findByCardNo(@Param("id") String cardNo);
}
