package com.msa.rental.domain.model.vo;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class LateFee {

    private final long point;

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee subtractPoint(long point) {
        if (this.point < point) {
            throw new IllegalArgumentException("Late fee cannot be negative");
        }

        return new LateFee(this.point - point);
    }

    public static LateFee createLateFee() {
        return new LateFee(0);
    }
}
