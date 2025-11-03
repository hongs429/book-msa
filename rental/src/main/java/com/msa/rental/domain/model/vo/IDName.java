package com.msa.rental.domain.model.vo;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IDName {
    private String id;
    private String name;

    public static IDName of(String id, String name) {
        return new IDName(id, name);
    }

}
