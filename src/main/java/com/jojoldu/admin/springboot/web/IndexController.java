package com.jojoldu.admin.springboot.web;

import com.jojoldu.admin.springboot.service.posts.PostsService;
import com.jojoldu.admin.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){//서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());
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