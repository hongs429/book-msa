package com.msa.rental.domain.model.vo;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * RentalCard 고유 번호를 나타내는 Value Object
 * 불변 객체이며, 값 기반 동등성 비교를 제공합니다.
 * 
 * JPA @Embeddable 사용 또는 분산 시스템(예: Kafka)에서 직렬화가 필요한 경우를 위해
 * Serializable을 구현합니다.
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalCardNo {
    
    private final String no;

    /**
     * 새로운 RentalCardNo를 생성합니다.
     * 형식: {year}-{uuid}
     * 예: 2024-550e8400-e29b-41d4-a716-446655440000
     */
    public static RentalCardNo of() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String rentalCardNo = year + "-" + uuid;
        return new RentalCardNo(rentalCardNo);
    }

    /**
     * 기존 번호 문자열로부터 RentalCardNo를 생성합니다.
     * 
     * @param no RentalCard 번호 문자열
     * @return RentalCardNo 인스턴스
     */
    public static RentalCardNo of(String no) {
        return new RentalCardNo(no);
    }

//    public static void main(String[] args) {
//        RentalCardNo rentalCardNo = RentalCardNo.of();
//        System.out.println(rentalCardNo);
//        System.out.println(rentalCardNo.getNo());
//    }
}
