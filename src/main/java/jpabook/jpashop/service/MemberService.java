package jpabook.jpashop.service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 기본적으로 JPA 데이터 처리시 트랜잭션을 사용해야 한다. (스프링이 제공하는걸로 쓴다) 클래스에 걸면 모두 적용된다. 리드온니 트루는 읽기전용 성능 최적화.
@RequiredArgsConstructor // 생성자 주입 자동으로
public class MemberService {

    private final MemberRepository memberRepository; // 파이널로 해야 컴파일 시점에 체크 된다.

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional // 읽기전용이 아니라 save 메서드면 리드온니 적용을 하지 않는다. 그래서 따로 설정을 해줘서 우선권을 준다.
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증 동시에 같은이름으로 가입 될수 있으므로 컬럼을 유니크로 지정 해 줘야 안전하다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List <Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }

    // 회원 전체 조회
    @Transactional(readOnly = true) // 검색 쿼리는 리드온니 읽기전용으로 설정해준다.(성능 최적화)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 하나만 조회
    @Transactional(readOnly = true) // 검색 쿼리는 리드온니 읽기전용으로 설정해준다.(성능 최적화)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
