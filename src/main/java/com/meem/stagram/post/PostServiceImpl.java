package com.meem.stagram.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.meem.stagram.follow.FollowEntity;
import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.story.IStoryRepository;
import com.meem.stagram.story.StoryEntity;
import com.meem.stagram.user.IUserRepository;

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
         * 
         * */
        
        List<FollowEntity> followerList = ifollowrepository.findByUserId(sessionUserId);
        
        List<String> strList = new ArrayList<String>();
        
        List<PostEntity> resultList = null;
        
        try {
            
            JSONArray jsonArr = new JSONArray(followerList.get(0).followerList);
            
            strList.add(sessionUserId);
            
            for (int idx = 0 , listCnt = jsonArr.length(); idx < listCnt; idx++) {   // JSONArray 내 json 개수만큼 for문 동작
                JSONObject jsonObject = jsonArr.getJSONObject(idx);                  // index번째 Json데이터를 가져옴
                strList.add(jsonObject.getString("user_id").toString());             // user_id 값을 사용하여 팔로우 인원을 가져온다.
            }
            
            resultList = ipostrepository.findByUserIdIn(strList);
            
            // 1. 추후 닉네임을 가져오기 위해 로직을 추가 할지 컬럼을 추가할지 논의
            // 2. 조인 테이블 컬럼을 하는 방법으로 로직을 짜보기
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
    
    
}