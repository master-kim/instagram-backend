package com.meem.stagram.story;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.post.IPostService;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor 
public class StoryController {
    
    @Autowired
    private final IStoryService istoryservice;
    
    // 2022.10.14.김요한.추가 - 메인 페이지 스토리 리스트 영역
    @PostMapping("/storyList")
    public List<StoryEntity> storyList(HttpServletRequest request) throws Exception{
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        
        return istoryservice.storyList(sessionUserId);
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/storyCreate")
    public HashMap<String, Object> storyCreate(HttpServletRequest request , 
                                                @RequestPart("fileInfo") @Valid @NotNull @NotEmpty MultipartFile fileInfo ,
                                                @RequestPart @Valid RequestDTO.storyCreate storyCreateInfo) throws Exception{
        
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 1. 파일이 먼저 없을 경우 리턴
        if(fileInfo.isEmpty()) {
            
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "이미지 파일을 첨부하세요.");
            
        } else {
            //HttpSession session = request.getSession();
            //String sessionId = session.getAttribute("user_id").toString();
            resultList = istoryservice.storyCreate(fileInfo , storyCreateInfo);
        }
        
        return resultList;
    }
    
}