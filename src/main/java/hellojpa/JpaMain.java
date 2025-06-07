package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactory는 애플리케이션 로딩 시점에 딱 하나만 만들어야함
        // persistenceUnitName은 persistence.xml에 설정한 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // entityManager 호출
        // 한 일관적인 단위를 할 때마다 EntityManager 만들어야함
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야함
        // 영속성 컨텍스트랑 트랜잭션의 주기를 맞춰서 설계를 해야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 호출 후 실제 code 작성
        try {
            // 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
