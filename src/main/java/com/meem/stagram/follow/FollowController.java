package com.meem.stagram.follow;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meem.stagram.dto.RequestDTO;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.11.08    김요한    팔로잉 리스트 -> 팔로우 추천 리스트로 변경 
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    
    private final IFollowService ifollowservice;
    
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    // 2022.11.08.김요한.수정 - 팔로잉 리스트 -> 팔로우 추천 리스트로 변경 
    @PostMapping("/followSuggList")
    public HashMap<String, Object> followSuggList(HttpServletRequest request) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
       
        resultMap = ifollowservice.followSuggList(sessionUserId);
        
        return resultMap;
    }
    
    // 2022.11.08.김요한.수정 - 실질적인 유저에 팔로잉 리스트를 가져오는 클래스로 변경
    @PostMapping("/followingList")
    public HashMap<String, Object> followingList(HttpServletRequest request) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
       
        resultMap = ifollowservice.followingList(sessionUserId);
        
        return resultMap;
    }
    
    // 2022.11.07.김요한.추가 - 팔로우리스트 인서트
    @PostMapping("/doFollow")
    public HashMap<String, Object> doFollow(HttpServletRequest request , @RequestBody RequestDTO.followInfo followInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        String followId = followInfo.getUserId();
        
        try {
            resultMap = ifollowservice.doFollow(followId , sessionUserId);
        } catch (Exception e) {
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        return resultMap;
    }
    
    @PostMapping("/endFollow")
    public HashMap<String, Object> endFollow(HttpServletRequest request , @RequestBody RequestDTO.followInfo followInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        // 추후 session 통해 로그인 id 확인
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        String followId = followInfo.getUserId();
        
        try {
            resultMap = ifollowservice.endFollow(followId , sessionUserId);
        } catch (Exception e) {
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        return resultMap;
    }
    
    
}