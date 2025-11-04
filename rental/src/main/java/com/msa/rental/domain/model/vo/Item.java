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
public class Item {

    private final Integer id;
    private final String title;

    public static Item of(Integer id, String title) {
        return new Item(id, title);
    }
}
