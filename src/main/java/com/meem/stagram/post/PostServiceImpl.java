package com.meem.stagram.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.user.IUserRepository;
import com.meem.stagram.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : PostServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
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

@Service("postserviceimpl")
@RequiredArgsConstructor
public class PostServiceImpl {
    
    @Autowired
    IPostRepository ipostrepository;
    
    @Autowired
    IFollowRepository ifollowrepository;
    
    @Autowired
    IUserRepository iuserrepository;
    
    
    // 전체 리스트 조회
    public List<PostEntity> findAll() {
        
        List<PostEntity> test = ipostrepository.findAll();
        
        return test;
        
    }
    
    // 전체 리스트 조회
    public List<PostEntity> postList(String sessionUserId) throws Exception{
        /* 1. kimyohan
         * 2. t_follow 테이블에 where = "kimyohan" 데이터가 > (jsonb) [{"user_id" : "원명준"}]
         * 3. [{"user_id" : "원명준"}]
         * 4. t_story 테이블에 가서 
         *    - "원명준" > 데이터가 있으면 가져오기
         * 5. 추후 스토리를 봤는지 안봤는지 표시를 위해 컬럼 추가 예정 (json) 
         * */
        
        // 결과값을 담는 배열 선언
        List<PostEntity> resultList = new ArrayList<>();
        
        // 해당 유저에 대한 followList를 가져오는 스트링 배열 (공통 함수 처리)
        List<String> strList = CommonUtils.followList(sessionUserId , ifollowrepository);
        
        // 실질적인 결과 값
        resultList = ipostrepository.findByUserIdIn(strList);
        
        return resultList;
    }
    
    
}