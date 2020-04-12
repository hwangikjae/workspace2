package com.jojoldu.admin.springboot.web;

import com.jojoldu.admin.springboot.config.auth.dto.SessionUser;
import com.jojoldu.admin.springboot.service.posts.PostsService;
import com.jojoldu.admin.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){//서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");//CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했다.
        //즉 로그인 성공시, httpSession.getAttribute("user")에서 값을 가져올 수 있다.
        if(user != null){   //세션에 저장된 값이 있을 때만 모델에 userName으로 등록한다.
            //세션에 저장된 값이 없으면 모델에 아무런 값이 없는 상태이므로 로그인 버튼이 보이게 된다!
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
//페이지에 관련된 컨트롤러는 모두 indexcontroller를 이용한다.
//머스테치 스타터 덕분에 컨트롤러에서 문자열을 반활할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
//앞의 경로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙는 것이다.
//즉 여기서는 "index"를 반환하므로 src/main/resources/templates/index.mustache로 전환되어 view resolver가 처리하게 된다.
//view resolver는 URL 요청의 경과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.