package com.meem.stagram.story;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.meem.stagram.follow.FollowEntity;
import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.user.IUserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : StoryServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.10.14    김요한    메인페이지 스토리 리스트 영역 가져오기  
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

    // 2022.10.14.김요한 - 비즈니스 로직 (스토리 리스트 가져오는 로직) - 유저에 대한 팔로우인원에 대한 스토리 올린 리스트 불러오기
    public List<StoryEntity> findByUserIdIn(String sessionUserId) throws Exception{
        
        List<FollowEntity> followerList = ifollowrepository.findByUserId(sessionUserId);
        
        List<String> strList = new ArrayList<String>();
        
        List<StoryEntity> resultList = null;
        
        try {
            
            JSONArray jsonArr = new JSONArray(followerList.get(0).followerList);
            
            for (int idx = 0 , listCnt = jsonArr.length(); idx < listCnt; idx++) {   // JSONArray 내 json 개수만큼 for문 동작
                JSONObject jsonObject = jsonArr.getJSONObject(idx);                  // index번째 Json데이터를 가져옴
                strList.add(jsonObject.getString("user_id").toString());             // user_id 값을 사용하여 팔로우 인원을 가져온다.
            }
            
            resultList = istoryrepository.findByUserIdIn(strList);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
    
}