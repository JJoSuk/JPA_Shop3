package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

// JPA 예외를 스프링 기반 예외로 예외 변환하기 위해 사용
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        // 멤버 엔티티가 커밋되는 시점에 정보 반영
        em.persist(member);
    }

    public Member findOne(Long id) {
        // 단건조회로 첫 번째는 타입과 두 번째는 키 설정할 코드를 넣어주면 된다.
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // jpql 프롬이 테이블이 아닌 엔티티로 설정
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        // // jpql 프롬이 테이블이 아닌 엔티티로 설정
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
