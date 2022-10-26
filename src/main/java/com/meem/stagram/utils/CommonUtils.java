package com.meem.stagram.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.meem.stagram.follow.FollowEntity;
import com.meem.stagram.follow.IFollowRepository;

/**
 * 설명 : CommonUtils.java   (공통 작업 utils 모아두기)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.25    김요한    최초작성 
 * -------------------------------------------------------------
 */

public class CommonUtils {
    
    // 2022.10.25.김요한 - 유저에 대한 팔로우인원 리스트를 뽑는 함수
    public static List<String> followList(String i_str_user_id, IFollowRepository i_follow_repository) throws Exception{
        
        List<FollowEntity> followerList = i_follow_repository.findByUserId(i_str_user_id);
        
        List<String> strList = new ArrayList<String>();
        
        try {
            
            // 4.팔로우 리스트 데이터를 JSONArray로 데이터를 뽑아 결과 값을 맞추어 작성
            JSONArray jsonArr = new JSONArray(followerList.get(0).followerList);
            
            if (0 < jsonArr.length()) {
                for (int idx = 0 , listCnt = jsonArr.length(); idx < listCnt; idx++) {   // JSONArray 내 json 개수만큼 for문 동작
                    JSONObject jsonObject = jsonArr.getJSONObject(idx);                  // index번째 Json데이터를 가져옴
                    strList.add(jsonObject.getString("user_id").toString());             // user_id 값을 사용하여 팔로우 인원을 가져온다.
                }
            
            } else {;}
            
            // 5. 내가 올린 스토리, 게시글도 가져오기 위해 결과값에 추가
            strList.add(i_str_user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return strList;
    }

}