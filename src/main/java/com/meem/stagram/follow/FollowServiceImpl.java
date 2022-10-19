package com.meem.stagram.follow;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<FollowEntity> followList(String sessionUserId) {
        List<FollowEntity> test = ifollowrepository.findByUserId(sessionUserId);
        return test;
    }
    
}