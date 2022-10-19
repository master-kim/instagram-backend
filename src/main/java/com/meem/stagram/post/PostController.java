package com.meem.stagram.post;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.17    김요한    스토리 게시판 가져오는 컨트롤러 생성
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/post")
public class PostController {
    
    @Autowired
    PostServiceImpl postserviceimpl;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    //@GetMapping("/postList")
    @PostMapping("/postList")
    public List<PostEntity> postList(HttpSession session , HttpServletRequest request) throws Exception{
        
        // 추후 session 통해 로그인 id 확인
        // String sessionUserId = session.getAttribute("user_id").toString();
        String sessionUserId = "kimyohan";
       
        List<PostEntity> postList = postserviceimpl.postList(sessionUserId);
        
        return postList;
    }
    
}