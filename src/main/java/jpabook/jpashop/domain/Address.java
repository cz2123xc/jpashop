package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 이 클래스는 엔드바이드 클래스이다.
@Getter 
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 값 타입은 변경 불가능 하게 설계한다.
    // 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만듬.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // JPA 스펙상 기본 생성자는 존재해야 함
    // public 보다는 protected 가 조금 더 안전
    protected Address() {
        //
    }
    
}
