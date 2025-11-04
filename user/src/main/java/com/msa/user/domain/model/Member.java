package com.msa.user.domain.model;


import com.msa.user.domain.vo.Authority;
import com.msa.user.domain.vo.Email;
import com.msa.user.domain.vo.IDName;
import com.msa.user.domain.vo.Password;
import com.msa.user.domain.vo.Point;
import com.msa.user.domain.vo.UserRole;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberNo;

    @Embedded
    private IDName idName;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @ElementCollection
    @CollectionTable(
            name = "authority",
            joinColumns = @JoinColumn(name = "member_no")
    )
    private List<Authority> authorities = new ArrayList<>();

    @Embedded
    private Point point;


    public static Member registerMember(IDName idName, Password pwd, Email email) {
        Member member = new Member();
        member.setIdName(idName);
        member.setPassword(pwd);
        member.setEmail(email);
        member.setPoint(Point.createPoint());
        member.addAuthority(new Authority(UserRole.USER));
        return member;
    }

    private void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public long savePoint(long point) {
        return this.point.addPoint(point);
    }

    public long usePoint(long point) {
        return this.point.removePoint(point);
    }

    public Member login(IDName idName, Password password) {
        return this;
    }

    public void logout(IDName idName) {
    }
}
