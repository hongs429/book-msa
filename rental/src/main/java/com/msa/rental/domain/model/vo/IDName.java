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
public class IDName {
    private final String id;
    private final String name;

    public static IDName of(String id, String name) {
        return new IDName(id, name);
    }

}
