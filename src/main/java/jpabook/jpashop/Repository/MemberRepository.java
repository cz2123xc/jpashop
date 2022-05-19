package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // 스프링이 생성한 엔티티 매니저를 주입해줌 ( 직접 엔티티 매니저 주입하는 것이 아니라 스프링이 생성한 엔티티 매니저를 주입해줌 내가 생성할 필요가 없음 )
//    private EntityManager em;

    // @PersistenceContext 사용하지 않아도 RequiredArgsConstructor 에 의해 자동으로 추론해서 생성자를 생성해줌
    private final EntityManager em;


    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class , id); // 타입, PK 값을 넣어줌
    }

    public List<Member> findAll() { // JPQL 은 테이블이 아니라 엔티티 객체를 대상으로 한다
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
