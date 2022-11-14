package com.meem.stagram.post;

import java.util.HashMap;

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
    
    // 게시글 리스트
    HashMap<String, Object> postList(String sessionUserId) throws Exception;
    // 게시글 상세보기
    HashMap<String, Object> postDetail(Integer postId) throws Exception;
    // 게시글 작성
    HashMap<String, Object> postCreate(MultipartFile fileInfo, RequestDTO.postCreate postCreateInfo) throws Exception;
    // 게시글 수정
    HashMap<String, Object> postUpdate(MultipartFile fileInfo, RequestDTO.postUpdate postUpdateInfo) throws Exception;
    // 게시글 좋아요
    HashMap<String, Object> postDoLike(String sessionUserId , RequestDTO.postLike postLikeInfo) throws Exception;



}