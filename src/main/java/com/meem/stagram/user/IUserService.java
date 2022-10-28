package com.meem.stagram.user;

import java.util.HashMap;

import javax.validation.Valid;

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

    HashMap<String, Object> findByUserId(@Valid RequestDTO.userLogin userLogin) throws Exception;

    HashMap<String, Object> userSave(@Valid RequestDTO.userRegister userRegister) throws Exception;

    HashMap<String, Object> findByPersnolPage(String userId) throws Exception;
    

}