package com.meem.stagram.follow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.post.PostEntity;
import com.meem.stagram.post.PostServiceImpl;
import com.meem.stagram.user.UserEntity;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/follow")
public class FollowController {
    
    @Autowired
    FollowServiceImpl followserviceimpl;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    //@GetMapping("/postList")
    @PostMapping("/followList")
    public List<UserEntity> followList(HttpServletRequest request) throws Exception{
        
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
       
        List<UserEntity> followList = followserviceimpl.followList(sessionUserId);
        
        return followList;
    }
    
}