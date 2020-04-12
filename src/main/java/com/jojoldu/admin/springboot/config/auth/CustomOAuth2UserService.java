package com.jojoldu.admin.springboot.config.auth;

import com.jojoldu.admin.springboot.config.auth.dto.OAuthAttributes;
import com.jojoldu.admin.springboot.config.auth.dto.SessionUser;
import com.jojoldu.admin.springboot.domain.user.User;
import com.jojoldu.admin.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

//구글 로그인 이후 가져온 사용자의 정보(email, name, picture)들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원한다.
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //현재 로그인 진행중인 서비스를 구분하는 코드
        //지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동시에 네이버로그인인지 구글로그인인지 구분하기 위해 사용!!
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();   //OAuth2 로그인 진행시 키가 되는 필드값이다. PK와 같은 의미
        //구글의 경우 기본적으로 코드를 지원하지만, 네이버, 카카오 등은 기본 지원하지 않는다.
        //구글의 기본코드는 sub 이다.
        //이후 네이버 로그인과 구글 로그인 동시지원할 때 사용된다.

        OAuthAttributes attributes =  OAuthAttributes.of(registrationId, userNameAttributeName,oAuth2User.getAttributes());  //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담는 클래스이다.
        //이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용한다.

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));    //세션에 사용자 정보를 저장하기 위한 dto 클래스이다.
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),attributes.getAttributes(), attributes.getNameAttributeKey());
    }
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
