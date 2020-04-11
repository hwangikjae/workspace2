package com.jojoldu.admin.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}

//머스테치 스타터 덕분에 컨트롤러에서 문자열을 반활할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
//앞의 경로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙는 것이다.
//즉 여기서는 "index"를 반환하므로 src/main/resources/templates/index.mustache로 전환되어 view resolver가 처리하게 된다.
//view resolver는 URL 요청의 경과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.