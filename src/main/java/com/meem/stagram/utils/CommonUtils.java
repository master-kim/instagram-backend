package com.meem.stagram.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.meem.stagram.follow.FollowEntity;
import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.post.PostEntity;

/**
 * 설명 : CommonUtils.java   (공통 작업 utils 모아두기)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.25    김요한    최초작성 
 * 2022.11.03    김요한    나의 게시글 조회 (post_id) 
 * 2022.11.04    김요한    followingList Json Length 비교 Obj 값 비교로 변경
 * 2022.11.04    김요한    followerList 추가
 * -------------------------------------------------------------
 */

public class CommonUtils {
    
    // 2022.10.25.김요한     - 유저에 대한 팔로잉하는 리스트를 뽑는 함수
    public static List<String> followingList(String i_str_user_id, IFollowRepository i_follow_repository) throws Exception{
        
        List<FollowEntity> followingList = i_follow_repository.findByUserId(i_str_user_id);
        
        List<String> strList = new ArrayList<String>();
        
        try {
            
            // 4.팔로우 리스트 데이터를 JSONArray로 데이터를 뽑아 결과 값을 맞추어 작성
            JSONArray jsonArr = new JSONArray(followingList.get(0).followerList);
            
            if (0 < jsonArr.getJSONObject(0).length()) {
                for (int idx = 0 , listCnt = jsonArr.length(); idx < listCnt; idx++) {   // JSONArray 내 json 개수만큼 for문 동작
                    JSONObject jsonObject = jsonArr.getJSONObject(idx);                  // index번째 Json데이터를 가져옴
                    strList.add(jsonObject.getString("user_id").toString());             // user_id 값을 사용하여 팔로우 인원을 가져온다.
                }
            
            } else {;}
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return strList;
    }

    // 2022.11.04.김요한 - 팔로워한 유저를 찾는거
    public static List<String> followerList(String i_str_user_id, IFollowRepository i_follow_repository) throws Exception{
        
        List<FollowEntity> dbUserList = i_follow_repository.findByUserIdNot(i_str_user_id);
        
        List<String> strList = new ArrayList<String>();
        
        try {
            if (0 < dbUserList.size()) {
                for (int userIdx = 0 , listCnt = dbUserList.size(); userIdx < listCnt; userIdx++) {   // JSONArray 내 json 개수만큼 for문 동작
                    
                    JSONArray jsonArr = new JSONArray(dbUserList.get(userIdx).followerList);
                    
                    for (int followIdx = 0 , arrCnt = jsonArr.length(); followIdx < arrCnt; followIdx++) {   
                        
                        JSONObject jsonObject = jsonArr.getJSONObject(followIdx);
                        
                        String userId = jsonObject.getString("user_id").toString();
                        
                        if (i_str_user_id.equals(userId)) {
                            strList.add(dbUserList.get(followIdx).getUserId());            
                        } else {;}
                        
                    }
                }
            
            } else {;}
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strList;
    }
    
    // 2022.11.03.김요한 - postIdList 뽑는 함수
    public static List<String> postIdList(List<PostEntity> postList) {
        
        List<String> strList = new ArrayList<String>();
        
        for (int postIdx = 0 , listCnt = postList.size(); postIdx < listCnt; postIdx++) {  
            String postId = postList.get(postIdx).getPostId().toString();
            strList.add(postId);             
        }
        
        return strList;
    }
    
    // 2022.11.03.김요한 - postIdList 뽑는 함수
    public static List<String> postAndUserIdList(List<PostEntity> postList) {
        
        List<String> strList = new ArrayList<String>();
        
        for (int postIdx = 0 , listCnt = postList.size(); postIdx < listCnt; postIdx++) {  
            String userId = postList.get(postIdx).getUserentity().getUserId().toString();
            strList.add(userId);             
        }
        
        return strList;
    }
    
}