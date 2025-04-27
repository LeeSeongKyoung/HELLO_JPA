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
    private Long id;

    // db 컬럼명 name과 객체 username 연결
    @Column(name = "name")
    private String username;

    private Integer age;

    // db enum 타입
    // ORIGINAL을 사용하면 순서 보장이 되지 않을 수 있어 STRING 권장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    // varchar를 넘어서는 큰 컨텐츠를 사용하고 싶을 때
    // String type일 때 clob
    @Lob
    private String description;

    // @Transient()
    // 필드 매핑 x
    // private String temp;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
