package com.msa.rental.framework.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserItemInputDTO {
    public String userId;
    public String userNm;
    public Integer itemId;
    public String itemTitle;
}
