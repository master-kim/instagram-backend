package com.meem.stagram.story;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;

/**
 * 설명 : IStoryService.java ( controller 와 serviceImp을 이어주는 역할)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

public interface IStoryService {
    // 메인페이지 - 스토리 리스트
    HashMap<String, Object> storyList(String sessionUserId) throws Exception;
    // 스토리 작성
    HashMap<String, Object> storyCreate(MultipartFile fileInfo, RequestDTO.storyCreate storyCreateInfo) throws Exception;

}