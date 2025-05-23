package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Member {
    // Entity = 데이터베이스 테이블과 매핑되는 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // AUTO : DB 방언에 맞춰 자동 시퀀스 생성
    // mySql : auto_increment
    private Long id;

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
