package jpabook.jpashop.service;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class) // JUNIT5 에서는 @ExtendWith 이용해서 테스트 컨텍스트를 설정해줌
@SpringBootTest // 스프링 부트 테스트 컨텍스트를 생성해줌
@Transactional // 같은 PK는 같은 객체로 인식한다 ( 같은 객체를 여러번 사용하면 에러가 발생한다 그래서 롤백을 해준다 -> 테스트 케이스 에서만 )
//@Rollback(false) // 롤백 false 시 롤백 안하고 커밋 해버림
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false) // 롤백 false 시 롤백 안하고 커밋 해버림(상단 @Transactional 이 있으면 자동으로 롤백 false 시 롤백 안하고 커밋 해버림)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("memberA");
        // when
        Long saveId = memberService.join(member);
        // then
//        em.flush(); // Rollback false 안하고 쿼리 확인하고 싶을때 쿼리로 디비에 반영
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception { // throws Exception 에 의해서 join 에서 발생한 예외를 던져버린다
        // given
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberA");

        // when
        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> { // 예외가 발생하면 실행되는 메소드 JUNIT5 에서는 assertThrows 사용
            memberService.join(member2);
        });

//        try {
//            memberService.join(member2); // 예외가 발생 해야한다.
//        } catch(IllegalStateException e) {
//            return;
//        }
        // then
//        Assertions.fail("예외가 발생해야 한다.");
    }



}