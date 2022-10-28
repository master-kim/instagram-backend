package com.meem.stagram.post;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.websocket.Session;

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
 * 2022.10.01    김요한    최초작성 
 * 2022.10.17    김요한    게시판 가져오는 컨트롤러 생성
 * 2022.10.27    김요한    게시글 저장 시 파일 생성 + 게시글 데이터 생성
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor 
public class PostController {
    
    private final IPostService ipostservice;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    @PostMapping("/postList")
    public List<PostEntity> postList(HttpServletRequest request) throws Exception{
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        
        return ipostservice.postList(sessionUserId);
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/postCreate")
    public HashMap<String, Object> postCreate(HttpServletRequest request , 
                                                @RequestPart("fileInfo") @Valid @NotNull @NotEmpty MultipartFile fileInfo ,
                                                @RequestPart @Valid RequestDTO.postCreate postCreateInfo) throws Exception{
        
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 1. 파일이 먼저 없을 경우 리턴
        if(fileInfo.isEmpty()) {
            
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "이미지 파일을 첨부하세요.");
            
        } else {
            //HttpSession session = request.getSession();
            //String sessionId = session.getAttribute("user_id").toString();
            resultList = ipostservice.postCreate(fileInfo , postCreateInfo);
        }
        
        return resultList;
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/postUpdate")
    public HashMap<String, Object> postUpdate(HttpServletRequest request , 
                                                @RequestPart("fileInfo") @Valid @NotNull @NotEmpty MultipartFile fileInfo ,
                                                @RequestPart @Valid RequestDTO.postUpdate postUpdateInfo) throws Exception{
        
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 1. 파일이 먼저 없을 경우 리턴
        if(fileInfo.isEmpty()) {
            
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "이미지 파일을 첨부하세요.");
            
        } else {
            //HttpSession session = request.getSession();
            //String sessionId = session.getAttribute("user_id").toString();
            resultList = ipostservice.postUpdate(fileInfo , postUpdateInfo);
        }
        
        return resultList;
    }
    
}