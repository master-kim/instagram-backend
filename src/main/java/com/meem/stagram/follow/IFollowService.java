package com.meem.stagram.follow;

import java.util.List;

import com.meem.stagram.user.UserEntity;

/**
 * 설명 : IUserService.java ( controller 와 serviceImp을 이어주는 역할)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

public interface IFollowService {

    List<UserEntity> followList(String sessionUserId) throws Exception;
    

}