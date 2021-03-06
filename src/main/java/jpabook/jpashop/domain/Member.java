package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") // mappedBy 연관관계 설정 : 맵핑을 한게 아니고 order 테이블에 있는 member 필드 에 의해서 맵핑이 되어진거임 (거울) (읽기전용)
    private List<Order> orders = new ArrayList<>();

}
