package com.jojoldu.admin.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //JPA Entity클래스들이 BaseTimeEntity를 상속할 경우 필드들(createDate, modifiedDate)도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate    //엔티티가 생성되어 저장될 때 시간이 자동저장 된다.
    private LocalDateTime createDate;

    @LastModifiedDate   //조회한 엔티티의 값을 변경할 떄 시간이 자동으로 저장된다.
    private LocalDateTime modifiedDate;
}

//BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 엔티티의 createDate, modifiedDate를 자동으로 관리하는 역할을 한다.

