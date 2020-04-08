package com.jojoldu.admin.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
//테스트를 진행할 때 junit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
//여기서는 springRunner라는 스프링 실행자를 사용하겠다.
//즉 스프링부트 테스트와 JUnit 사이의 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class)
//Web(spring MVC)에 집중할 수 있는 어노테이션
//선언할 경우 @Controller와 @ControllerAdvice 등을 사용할 수 있다.
//단@Service, @Component, @Repository 등은 사용할 수 없다.
//여기선 컨트롤러만 사용하기 때문에선언한다.
public class HelloControllerTest {

    @Autowired  //스프링이 관리하는 bean을 주입받는다.
    private MockMvc mvc;
    //web Api를 테스트 할때 사용한다.
    //스프링 mvc의 시작점
    //이 클래스를 통해 http get, post 등에 대한 api를 테스트 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello 주소로 http get을 요청한다. 체이닝이 지원되어 아래와 같이 여러검증기능 이어서 선언가능
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증. http header 의 status를 검증. 200/404/500  등의 상태. 여기서는 OK 즉 200인지 아닌지 검증
                .andExpect(content().string(hello));//응답본문의 내용을 검증한다. 컨트롤러에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDt가_리턴된댜() throws Exception{
        String name = "fuck";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)    //param: api 테스트할 때사용될 요청 파라미터 설정. 단 값은 string만 허용. 그래서 숫자 날짜 데이터도 등록할 때는 문자열로 변경해야만 가능!!
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))//jsonpath: json 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시. 여기서는 name과 amount를 검증하니 $.name , $.amount로 검증한다.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
