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
public class Item {

    private final Integer id;
    private final String title;

    public static Item of(Integer id, String title) {
        return new Item(id, title);
    }
}
