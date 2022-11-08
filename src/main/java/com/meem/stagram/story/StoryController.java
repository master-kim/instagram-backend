package com.meem.stagram.story;

import java.util.HashMap;

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

import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.10.14    김요한    메인 스토리에서 영역 가져오기 
 * 2022.11.08    김요한    스토리 리스트 가져오기 변경 (파일 가져오기 위해)
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor 
public class StoryController {
    
    @Autowired
    private final IStoryService istoryservice;
    
    // 2022.10.14.김요한.추가 - 메인 페이지 스토리 리스트 영역
    // 2022.11.08.김요한.수정 - 파일데이터 추가 위해 변경
    @PostMapping("/storyList")
    public HashMap<String, Object> storyList(HttpServletRequest request) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        
        try {
            resultMap = istoryservice.storyList(sessionUserId);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
            
        }
        
        return resultMap ;
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/storyCreate")
    public HashMap<String, Object> storyCreate(HttpServletRequest request , 
                                                @RequestPart("fileInfo") @Valid @NotNull @NotEmpty MultipartFile fileInfo ,
                                                @RequestPart @Valid RequestDTO.storyCreate storyCreateInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 1. 파일이 먼저 없을 경우 리턴
        if(fileInfo.isEmpty()) {
            
            resultMap.put("resultCd" , "FAIL");
            resultMap.put("resultMsg" , "이미지 파일을 첨부하세요.");
            
        } else {
            //HttpSession session = request.getSession();
            //String sessionId = session.getAttribute("user_id").toString();
            resultMap = istoryservice.storyCreate(fileInfo , storyCreateInfo);
        }
        
        return resultMap;
    }
    
}