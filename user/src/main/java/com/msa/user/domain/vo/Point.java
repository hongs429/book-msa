package com.msa.user.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@EqualsAndHashCode
@Embeddable
public class Point {
    public final long pointValue;

    public Point addPoint(long point) {
        return new Point(this.getPointValue() + point);
    }

    public Point removePoint(long point) {
        if (point > this.pointValue) {
            throw new RuntimeException("기존 보유 Point보다 적어 삭제할 수 없습니다.");
        }
        return new Point(this.getPointValue() - point);
    }

    public static Point createPoint() {
        return new Point(0L);
    }
}
