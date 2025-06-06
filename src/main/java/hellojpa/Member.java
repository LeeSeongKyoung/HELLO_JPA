package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES", // 테이블 명
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1) // pk명
public class Member {
    // Entity = 데이터베이스 테이블과 매핑되는 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "MEMBER_SEQ_GENERATOR")

    private Long id;
    // AUTO : DB 방언에 맞춰 자동 시퀀스 생성
    // mySql : auto_increment
    // 참고 : 각 테이블마다 시퀀스 부여하고 싶다면 @SequenceGenerator 사용
    // tableGenerator : 테이블 매핑 전략은 잘 사용하지 않음 / 직접 테이블을 사용하기 때문에 성능에서 부담스럽기 때문
    // 권장하는 식별자 전략 : auto_increatment나 Sequence-object 둘 중 하나 쓰는 것을 권장 / 또는 uuid


    // db 컬럼명 name과 객체 username 연결
    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
