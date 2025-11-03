package com.msa.rental.domain.model.vo;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {

    private Integer id;
    private String title;

    public static Item of(Integer id, String title) {
        return new Item(id, title);
    }
}
