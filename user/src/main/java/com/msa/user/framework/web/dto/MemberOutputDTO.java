package com.msa.user.framework.web.dto;


import com.msa.user.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberOutputDTO {
    private String id;
    private String Name;
    private String passWord;
    private String email;
    private long point;
    public static  MemberOutputDTO mapToDTO(Member member){
        MemberOutputDTO memberOutPutDTO = new MemberOutputDTO();
        memberOutPutDTO.setId(member.getIdName().getId());
        memberOutPutDTO.setName(member.getIdName().getName());
        memberOutPutDTO.setPassWord(member.getPassword().getPresentPWD());
        memberOutPutDTO.setEmail(member.getEmail().getAddress());
        memberOutPutDTO.setPoint(member.getPoint().getPointValue());
        return memberOutPutDTO;
    }
}
