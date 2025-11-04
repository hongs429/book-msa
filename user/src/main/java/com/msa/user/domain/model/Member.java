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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
        member.idName = idName;
        member.password = pwd;
        member.email = email;
        member.point = Point.createPoint();
        member.authorities = new ArrayList<>();
        member.authorities.add(new Authority(UserRole.USER));
        return member;
    }

    private void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public long savePoint(long point) {
        this.point = this.point.addPoint(point);
        return this.point.getPointValue();
    }

    public long usePoint(long point) {
        this.point = this.point.removePoint(point);
        return this.point.getPointValue();
    }

    public Member login(IDName idName, Password password) {
        return this;
    }

    public void logout(IDName idName) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Member)) return false;

        Member member = (Member) obj;
        return member.MemberNo != null && this.MemberNo.equals(member.MemberNo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
