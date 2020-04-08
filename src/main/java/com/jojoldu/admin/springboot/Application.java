package com.jojoldu.admin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
//앞으로 만들 프로젝트의 메인클래스.
//@SpringBootApplication 으로 인해 스프링 부트의 자동설정, 스프링 bean 읽기와 생성 모두 자동으로 설정.
//특히 @SpringBootApplication 이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 상단에 위치한다.
//메인메소드에서 실행하는 SpringApplication.run으로 인해 내장 was를 실행한다.
//내장 was란 별도로 외부에 was를 두지 않고 애플맄메이션을 실행할 때 내부에서 was를 실행한다.
//이렇게 되면 톰캣을 설치할 필요가 없게 되고, 스프링부트로 만들어진 jar파일(실행가능한 자바 패키징 파일)로 실행하면 된다.