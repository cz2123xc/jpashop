package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 이 클래스는 엔드바이드 클래스이다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


}
