package com.meem.stagram.user;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;

/**
 * 설명 : IUserService.java ( controller 와 serviceImp을 이어주는 역할)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

public interface IUserService {
    // 유저 로그인 시 해당 유저 맞는지 체크
    HashMap<String, Object> findByUserId(@Valid RequestDTO.userLogin userLogin) throws Exception;
    // 유저 회원가입
    HashMap<String, Object> userSave(MultipartFile fileInfo , @Valid RequestDTO.userRegister userRegister) throws Exception;
    // 유저 개인 프로필 페이지
    HashMap<String, Object> findByPersnolPage(String userId) throws Exception;
    

}