package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Member {
    // Entity = 데이터베이스 테이블과 매핑되는 클래스
    @Id
    private long id;
    @Column(unique=true, length = 10)
    private String name;

    // JPA는 기본적으로 내부적으로 리플렉션 등을 사용하기 때문에 동적으로 객체 생성 필요
    // 무조건 public일 필요는 없음
    public Member() {
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
