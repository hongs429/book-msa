package com.msa.user.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Password {
    public String presentPWD;
    public String pastPWD;
    public static Password sample(){
        return new Password("12345","abcde");
    } }
