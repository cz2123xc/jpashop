package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Rollback(false) // 롤백 false 시 롤백 안하고 커밋 해버림
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional // 테스트 코드에 트랜잭션이 있으면 테스트 후 디비 롤백 함 ( 그래야 다음 테스트를 할 수 있으므로 )
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");
        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);
        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember  = member : " + findMember + " : " + member); // 영속성 컨텍스트에서 아이디 값이 같으면 같은 인스턴스로 인식한다.
    }

}