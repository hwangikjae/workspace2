package com.jojoldu.admin.springboot.config.auth.dto;

import com.jojoldu.admin.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//인증된 사용자 정보만 필요로 한다. 그외에 필요한 정보들은 없으니, name, mail. picture만 필드로 선언한다.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}