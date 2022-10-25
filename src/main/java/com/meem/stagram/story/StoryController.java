package com.meem.stagram.story;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.10.14    김요한    메인 스토리에서 영역 가져오기 
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/story")
public class StoryController {
    
    @Autowired
    StoryServiceImpl storyserviceimpl;
    
    // 2022.10.14.김요한.추가 - 메인 페이지 스토리 리스트 영역
    //@GetMapping("/storyList")
    @PostMapping("/storyList")
    public List<StoryEntity> storyList(HttpServletRequest request) throws Exception{
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        
        return storyserviceimpl.findByUserIdIn(sessionUserId);
    }
    
    
}