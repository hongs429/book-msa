package com.msa.user.framework.web;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.msa.user.application.usecase.AddMemberUseCase;
import com.msa.user.application.usecase.InquiryMemberUseCase;
import com.msa.user.framework.web.dto.MemberInfoDTO;
import com.msa.user.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final AddMemberUseCase addMemberUsecase;
    private final InquiryMemberUseCase inquiryMemberUsecase;

    @PostMapping("/members")
    public ResponseEntity<MemberOutputDTO> addMember(@RequestBody MemberInfoDTO
                                                             memberInfoDTO) {
        MemberOutputDTO addedMember = addMemberUsecase.addMember(memberInfoDTO);
        return new ResponseEntity<>(addedMember, CREATED);
    }

    @GetMapping("/members/{no}")
    public ResponseEntity<MemberOutputDTO> getMember(@PathVariable("no") Long no) {
        MemberOutputDTO member = inquiryMemberUsecase.getMember(no);
        return member != null ? new ResponseEntity<>(member, OK) : new ResponseEntity<>(NOT_FOUND);
    }
}
