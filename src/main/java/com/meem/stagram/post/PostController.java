package com.meem.stagram.post;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.follow.IFollowService;
import com.meem.stagram.story.IStoryService;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.17    김요한    게시판 가져오는 컨트롤러 생성
 * 2022.10.27    김요한    게시글 저장 시 파일 생성 + 게시글 데이터 생성
 * 2022.11.08    김요한    게시글 리스트 가져올때 필요 파일 리스트 추가 및 1번호출로 변경
 * 2022.11.19    김요한    게시글 디테일 페이지 소스 정리 (파일 이미지 , 유저 이미지 댓글 좋아요 수 가져오기 추가)
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor 
public class PostController {
    
    private final IPostService   ipostservice;
    private final IFollowService ifollowservice;
    private final IStoryService  istoryservice;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    @PostMapping("/postList")
    public HashMap<String, Object> postList(HttpServletRequest request) throws Exception{
       
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        
        try {
            // 2022.11.08.김요한.수정 - 각 영역을 1번만 호출하기 위해 변경
            HashMap<String, Object> storyInfo = istoryservice.storyList(sessionUserId);
            HashMap<String, Object> postInfo = ipostservice.postList(sessionUserId);
            HashMap<String, Object> followInfo = ifollowservice.followSuggList(sessionUserId);
            
            resultMap.put("resultCd", "SUCC");
            resultMap.put("resultMsg", "성공");
            
            resultMap.put("storyList",  storyInfo.get("storyList"));
            resultMap.put("storyUserImgList",  storyInfo.get("storyUserImgList"));
            
            resultMap.put("postList",  postInfo.get("postList"));
            resultMap.put("postLikeList",  postInfo.get("postLikeList"));
            resultMap.put("postLikeCnt",  postInfo.get("postLikeCnt"));
            resultMap.put("postCommentList",  postInfo.get("postCommentList"));
            resultMap.put("commentUserImg",  postInfo.get("commentUserImg"));
            resultMap.put("postCommentCnt",  postInfo.get("postCommentCnt"));
            resultMap.put("postImgList",  postInfo.get("postImgList"));
            resultMap.put("postUserImgList",  postInfo.get("postUserImgList"));
            
            resultMap.put("followSuggList",  followInfo.get("followSuggList"));
            resultMap.put("followSuggImgList",  followInfo.get("followSuggImgList"));
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
            
        }
        return resultMap;
    }
    
    // 2022.10.28.이강현.추가 - 게시글 상세보기 가져오는 컨트롤러 생성
    @PostMapping("/postDetail")
    public HashMap<String, Object> postDetail(@RequestBody RequestDTO.postDetail postDetail) throws Exception {
        
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Integer postId = postDetail.getPostId();
        try {
            resultMap  = ipostservice.postDetail(postId);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        return resultMap;
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/postCreate")
    public HashMap<String, Object> postCreate(HttpServletRequest request , 
            @RequestPart("fileInfo") @Valid @NotNull(message = "파일을 넣어주세요.") MultipartFile fileInfo ,
            @RequestPart("postInfo") @Valid RequestDTO.postCreate postCreateInfo) throws Exception{
        
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
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/doLike")
    public HashMap<String, Object> doLike(HttpServletRequest request , @RequestBody @Valid RequestDTO.postLike postLikeInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        try {
            resultMap = ipostservice.postDoLike(sessionUserId , postLikeInfo);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        return resultMap;
    }
    
    // 2022.10.27.김요한.추가 - 게시글 저장 시 파일 생성 + 게시글 데이터 생성
    @PostMapping("/doComment")
    public HashMap<String, Object> doComment(HttpServletRequest request , @RequestBody @Valid RequestDTO.postComment postCommentInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        try {
            resultMap = ipostservice.postDoComment(sessionUserId , postCommentInfo);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        return resultMap;
    }
    
    // 2022.11.21.김요한.추가 - 댓글 삭제
    @PostMapping("/updateComment")
    public HashMap<String, Object> updateComment(HttpServletRequest request , @RequestBody @Valid RequestDTO.updateComment updateCommentInfo) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        String sessionUserId = request.getSession().getAttribute("user_id").toString();
        try {
            resultMap = ipostservice.updateComment(sessionUserId , updateCommentInfo);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        return resultMap;
    }
    
    
    
}