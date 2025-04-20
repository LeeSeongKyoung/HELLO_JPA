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
//            1. 회원 등록
            // 비영속
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

            // 영속 상태
            // 영속 상태에서 db에 쿼리가 날라가지 않음
            // 트랜잭션을 커밋하는 시점에 영속성 컨텍스트에 있는 db에 쿼리가 날라감 -> tx.commit();
//            em.persist(member);

//            2.회원 삭제
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            em.remove(findMember);

            // 3. 회원 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // 4. 전체 회원 조회
            // JPQL : 조회 대상이 테이블이 아닌 entity 객체
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name: " + member.getName());
//            }

            // 5. 영속&비영속
            // 1) 비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA_11");
//
//            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");

            // 2) 조회용 sql이 실행되냐? -> select 쿼리가 실행안됨, 1차 캐시에서 저장이 됐고, 앞에 똑같은 pk(100L)를 조회하기때문
//            Member findMember = em.find(Member.class, 100L);
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.Name = " + findMember.getName());

            // 3) 영속
            // 첫번째, JPA가 DB에서 조회값 가져와서 영속성 컨텍스트에 일단 올려놓음(쿼리 한번만 실행)
            // 두번째, 똑같은 걸로 조회하니까 조회하는 시점에 먼저 있는 영속성 컨텍스트에 있는 1차 캐시부터 조회
            //    -> 첫번째에 조회를 했기 때문에 값 반환
//            Member findMember1 = em.find(Member.class, 100L);
//            Member findMember2 = em.find(Member.class, 100L);
//            // result = true
//            System.out.println("result = " + (findMember1 == findMember2));

            // 6. 엔티티 등록
            // 영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//             em.flush(); // commit 되기 전 db에 바로 반영함(직접 호출), 1차 캐시를 지우지 않음, db에 반영되는 과정

            /*플러시 모드 옵션
            1. 커밋이나 쿼리를 실행할 때 플러시 (기본값)
            em.setFlushMode(FlushModeType.AUTO);
            2. 커밋할 때만 플러시
            em.setFlushMode(FlushModeType.COMMIT);*/

//            em.persist(member1);
//            em.persist(member2);

            // 7. 엔티티 수정
            Member member = em.find(Member.class, 150L);
            member.setName("zzzz");

            // 8. 준영속 상태 -> 거의 사용안함
//            em.detach(member); // detach 이외에 em.clear(), em.close() 사용
            System.out.println("========================================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
