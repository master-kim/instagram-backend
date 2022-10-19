package com.meem.stagram.story;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public List<StoryEntity> storyList(HttpSession session , HttpServletRequest request) throws Exception{
        
        // 추후 session 통해 로그인 id 확인
        // String sessionUserId = session.getAttribute("user_id").toString();
        String sessionUserId = "kimyohan";
       
        List<StoryEntity> storyList = storyserviceimpl.findByUserIdIn(sessionUserId);
        
        return storyList;
        // HttpStatus 값을 넣어줄 필요가 없음 (성공 시 200으로 매핑되어 들어가며 , 실패시 그에 맞는 코드 전송)
        //return new ResponseEntity<List<StoryEntity>>(storyList , HttpStatus.OK);
    }
    
}