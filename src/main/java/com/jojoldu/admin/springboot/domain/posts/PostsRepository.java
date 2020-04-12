package com.jojoldu.admin.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}

//Post 클래스로 데이터베이스를 접근하게 해줄 JPA Reposytory..
// 보통 ibatis나 mybatis 등에서 dao라고 불리는 db Layer 접근자다.
// JPA에서는 Reposytory라 부르며 인터페이스로 생성한다.
// 단순히 인터페이스 생성 후, JpaRepository<클래스명, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
// @Repository츨 추가할 필요도 없다.
//주의점은 Entity 클래스와 기본 Entity Repository는 함께 위치 해야 한다는 점.
//둘은 아주 밀접한 관계이고 Entity클래스는 기본 repository 없이는 제대로 역할을 할 수 없다.