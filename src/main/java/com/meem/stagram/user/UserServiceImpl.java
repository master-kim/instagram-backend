package com.meem.stagram.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.story.StoryEntity;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * -------------------------------------------------------------
 */


@Service("userserviceimpl")
@RequiredArgsConstructor
public class UserServiceImpl {
    
    @Autowired
    IUserRepository iuserrepository;

    
    //전체 리스트 조회
    public List<UserEntity> findAll() {
        
        List<UserEntity> resultList = iuserrepository.findAll();
        
        return resultList;
        
    }
    
    // 특정 ID 조회
    public List<UserEntity> findByUserId(String id) {
        List<UserEntity> resultList = iuserrepository.findByUserId(id);
        return resultList;
    }

}










