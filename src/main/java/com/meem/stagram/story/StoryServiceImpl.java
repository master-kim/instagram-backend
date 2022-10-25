package com.meem.stagram.story;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.post.PostEntity;
import com.meem.stagram.user.IUserRepository;
import com.meem.stagram.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : StoryServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.10.14    김요한    메인페이지 스토리 리스트 영역 가져오기  
 * 2022.10.25    김요한    공통함수 추가 (팔로우리스트)
 * -------------------------------------------------------------
 */

@Service("storyserviceimpl")
@RequiredArgsConstructor
public class StoryServiceImpl {
    
    @Autowired
    IStoryRepository istoryrepository;
    
    @Autowired
    IFollowRepository ifollowrepository;
    
    @Autowired
    IUserRepository iuserrepository;
    
    public List<StoryEntity> findAll() {
        
        // JpaRepository 기본적으로 제공해주는 findAll 기능
        List<StoryEntity> resultList = istoryrepository.findAll();
        
        return resultList;
        
    }

    /**
     * 2022.10.14.김요한 - 비즈니스 로직 (스토리 리스트 가져오는 로직) - 유저에 대한 팔로우인원에 대한 스토리 올린 리스트 불러오기
     * 2022.10.25.김요한 - followlist를 공통함수로 가져오게 처리 
     * */
    public List<StoryEntity> findByUserIdIn(String sessionUserId) throws Exception{
        
        // 결과값을 담는 배열 선언
        List<StoryEntity> resultList = new ArrayList<>();
        
        // 해당 유저에 대한 followList를 가져오는 스트링 배열 (공통 함수 처리) --> 사용방법 (user_id , ifollowrepository) 를 넘겨주면 가져온다.
        List<String> strList = CommonUtils.followList(sessionUserId , ifollowrepository);
        
        // 실질적인 결과 값
        resultList = istoryrepository.findByUserIdIn(strList);
        
        return resultList;
    }

}