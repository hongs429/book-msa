package com.msa.rental.domain.model.vo;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LateFee {

    private final long point;

    public LateFee addPoint(long point) {
        return new  LateFee(this.point + point);
    }

    public LateFee subtractPoint(long point) {
        if (this.point < point) {
            throw new IllegalArgumentException("Late fee cannot be negative");
        }

        return new  LateFee(this.point - point);
    }

    public  static LateFee createLateFee(){
        return new LateFee(0);
    }
}
