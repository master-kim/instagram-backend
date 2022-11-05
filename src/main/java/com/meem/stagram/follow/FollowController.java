package com.meem.stagram.follow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meem.stagram.post.IPostService;
import com.meem.stagram.user.UserEntity;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class FollowController {
    
    private final IFollowService ifollowservice;
    
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    @PostMapping("/followingList")
    public List<UserEntity> followingList(HttpServletRequest request) throws Exception{
        
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
       
        List<UserEntity> followingList = ifollowservice.followingList(sessionUserId);
        
        return followingList;
    }
    
}