package com.meem.stagram.follow;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.dto.RequstDTO;
import com.meem.stagram.dto.RequstDTO.userRegister;
import com.meem.stagram.user.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * -------------------------------------------------------------
 */

/*
 * @RequiredArgsConstructor 이용 해석
 * 기존 방법
 * (1) Autowired
 * @Autowired
 * TestSampleRepository TestSampleRepository;
 * 
 * (2) 생성자 함수 생성
 * 
 * TestSampleRepository TestSampleRepository;
 * 
 * @Autowired
 * public TestServiceImpl(TestSampleRepository TestSampleRepository) {
 *     this.TestSampleRepository = TestSampleRepository;
 * }
 * 
 * (3) 롬복 이용
 * TestSampleRepository TestSampleRepository;
 * 
 * */

@Service("followserviceimpl")
@RequiredArgsConstructor
public class FollowServiceImpl {
    
    @Autowired
    IFollowRepository ifollowrepository;

    public List<FollowEntity> findAll() {
        
        List<FollowEntity> resultList = ifollowrepository.findAll();
        
        return resultList;
    }
    
    public List<FollowEntity> followList(String sessionUserId) {
        List<FollowEntity> test = ifollowrepository.findByUserId(sessionUserId);
        return test;
    }
    
    // 2022.10.25.김요한.추가 - entity가 보호되어있으므로 UserServiceImpl에서 새로 생성 불가로 인해 Impl로 접근 시켜 save
    public void followRegister(RequstDTO.userRegister userRegister) {
        
        FollowEntity userfollow = new FollowEntity();
        userfollow.FollowRegister(userRegister);
        ifollowrepository.save(userfollow);
        
    }
    
}