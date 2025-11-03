package com.msa.rental.domain.model.vo;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LateFee {

    private long point;

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
