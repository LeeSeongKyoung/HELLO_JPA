package hellojpa;

import javax.persistence.*;

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
            em.persist(team); // id에 값이 들어감 -> 영속 상태가 되면 무조건 pk값이 세팅되고 영속 상태가 됨

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); // jpa가 알아서 pk값을 꺼내서 fk값에 insert할 때 fk값으로 사용

            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam: " + findTeam.getName());

            tx.commit(); // 시퀀스 전략은 커밋 시점에 insert문 보냄
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
