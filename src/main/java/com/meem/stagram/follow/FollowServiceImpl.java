package com.meem.stagram.follow;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.meem.stagram.file.FileEntity;
import com.meem.stagram.file.IFileRepository;
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
 * 2022.11.08    김요한    메인페이지 팔로우 추천 영역에 뿌려지는 클래스 추가
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
public class FollowServiceImpl implements IFollowService {
    
    private final IFollowRepository ifollowrepository;
    
    private final IUserRepository iuserrepository;
    
    private final IFileRepository ifilerepository;

    // 2022.11.08.김요한.추가 - 메인페이지 팔로우 추천영역에 뿌려지는 클래스
    public HashMap<String, Object> followSuggList(String sessionUserId) throws Exception {
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 해당 유저에 대한 followingList를 가져오는 스트링 배열 (공통 함수 처리)
        List<String> strList = CommonUtils.followingList(sessionUserId , ifollowrepository);
        strList.add(sessionUserId);
        // 실질적인 결과 값
        List<UserEntity> followSuggList = iuserrepository.findByUserIdNotIn(strList);
        
        // 파일가져오기 작성하면 되는상황
        List<FileEntity> followSuggImgList = ifilerepository.findByCommonIdNotInAndFileFolderType(strList , "user");
        resultMap.put("followSuggList", followSuggList);
        resultMap.put("followSuggImgList", followSuggImgList);
        
        return resultMap;
    }
    
    // 2022.11.08.김요한.추가 - 실질적으로 해당 유저가 팔로잉한 리스트를 가져오는 영역
    public HashMap<String, Object> followingList(String sessionUserId) throws Exception {
        
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 해당 유저에 대한 followingList를 가져오는 스트링 배열 (공통 함수 처리)
        List<String> strList = CommonUtils.followingList(sessionUserId , ifollowrepository);
        // 실질적인 결과 값
        List<UserEntity> followingList = iuserrepository.findByUserIdIn(strList);
        
        // 파일가져오기 작성하면 되는상황
        List<FileEntity> followingImgList = ifilerepository.findByCommonIdNotInAndFileFolderType(strList , "user");
        resultMap.put("followingList", followingList);
        resultMap.put("followingImgList", followingImgList);
        
        return resultMap;
    }
    
    // 2022.11.08.김요한.추가 - 팔로우 맺기
    public HashMap<String, Object> doFollow(String followId, String sessionUserId) throws Exception {
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 실질적인 결과 값
        List<FollowEntity> followList = ifollowrepository.findByUserId(sessionUserId);
        JSONArray jsonArr = new JSONArray(followList.get(0).followerList);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("user_id", followId);
        jsonArr.put(jsonObj);
        
        FollowEntity doFollow = FollowEntity.followUpdate(followList , jsonArr);
        ifollowrepository.save(doFollow);
        
        resultMap.put("resultCd", "SUCC");
        resultMap.put("resultMsg", "성공");
        
        return resultMap;
    }
    
    // 2022.11.08.김요한.추가 - 팔로우 끊기
    public HashMap<String, Object> endFollow(String followId, String sessionUserId) throws Exception {
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 실질적인 결과 값
        List<FollowEntity> followList = ifollowrepository.findByUserId(sessionUserId);
        JSONArray jsonArr = new JSONArray(followList.get(0).followerList);
        
        if (0 < jsonArr.getJSONObject(0).length()) {
            for (int idx = 0 , listCnt = jsonArr.length(); idx < listCnt; idx++) {   // JSONArray 내 json 개수만큼 for문 동작
                JSONObject jsonObject = jsonArr.getJSONObject(idx);                  // index번째 Json데이터를 가져옴
                
                if (jsonObject.get("user_id").equals(followId)) {
                    jsonArr.remove(idx);
                } else {;}
            }
        
        } else {;}
        
        FollowEntity doFollow = FollowEntity.followUpdate(followList , jsonArr);
        ifollowrepository.save(doFollow);
        
        resultMap.put("resultCd", "SUCC");
        resultMap.put("resultMsg", "성공");
        
        return resultMap;
    }
    
}