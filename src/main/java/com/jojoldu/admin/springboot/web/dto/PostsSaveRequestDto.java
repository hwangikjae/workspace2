package com.jojoldu.admin.springboot.web.dto;

import com.jojoldu.admin.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

//여기서는 Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했다. 하지만 절대 Entity 클래스를 Request/response 클래스로 사용해서는 안된다.
//Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스다. Entity 클래스를 기준으로 테이블이 생성되고 스키마가 변경된다.