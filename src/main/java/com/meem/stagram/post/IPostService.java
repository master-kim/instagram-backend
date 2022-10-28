package com.meem.stagram.post;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;

/**
 * 설명 : IPostService.java ( controller 와 serviceImp을 이어주는 역할)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

public interface IPostService {
    
    List<PostEntity> postList(String sessionUserId) throws Exception;
    
    HashMap<String, Object> postDetail(Integer postId) throws Exception;
    
    HashMap<String, Object> postCreate(MultipartFile fileInfo, RequestDTO.postCreate postCreateInfo) throws Exception;

    HashMap<String, Object> postUpdate(MultipartFile fileInfo, RequestDTO.postUpdate postUpdateInfo) throws Exception;



}