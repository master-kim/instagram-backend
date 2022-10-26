package com.meem.stagram.follow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.dto.RequstDTO;
import com.meem.stagram.post.IPostRepository;
import com.meem.stagram.user.IUserRepository;
import com.meem.stagram.user.UserEntity;
import com.meem.stagram.utils.CommonUtils;

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
    IPostRepository ipostrepository;
    
    @Autowired
    IFollowRepository ifollowrepository;
    
    @Autowired
    IUserRepository iuserrepository;

    public List<FollowEntity> findAll() {
        
        List<FollowEntity> resultList = ifollowrepository.findAll();
        
        return resultList;
    }
    
    public List<UserEntity> followList(String sessionUserId) throws Exception {
        
        // 결과값을 담는 배열 선언
        List<UserEntity> resultList = new ArrayList<>();
        
        // 해당 유저에 대한 followList를 가져오는 스트링 배열 (공통 함수 처리)
        List<String> strList = CommonUtils.followList(sessionUserId , ifollowrepository);
            
        // 실질적인 결과 값
        resultList = iuserrepository.findByUserIdNotIn(strList);
        
        return resultList;
    }
    
    // 2022.10.25.김요한.추가 - entity가 보호되어있으므로 UserServiceImpl에서 새로 생성 불가로 인해 Impl로 접근 시켜 save
    public void followRegister(RequstDTO.userRegister userRegister) {
        
        FollowEntity userfollow = new FollowEntity();
        userfollow.FollowRegister(userRegister);
        ifollowrepository.save(userfollow);
        
    }
    
}