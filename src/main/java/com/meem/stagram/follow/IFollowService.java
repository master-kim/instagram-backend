package com.meem.stagram.follow;

import java.util.HashMap;

/**
 * 설명 : IUserService.java ( controller 와 serviceImp을 이어주는 역할)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

public interface IFollowService {

    // 메인페이지 (팔로우 추천 영역에 뿌려지는 곳)
    HashMap<String, Object> followSuggList(String sessionUserId) throws Exception;
    // 실질적인 해당 유저가 팔로잉 한 리스트를 가져오는 부분
    HashMap<String, Object> followingList(String sessionUserId) throws Exception;
    // 팔로우 맺기
    HashMap<String, Object> doFollow(String followId, String sessionUserId) throws Exception;
    // 팔로우 끊기
    HashMap<String, Object> endFollow(String followId, String sessionUserId) throws Exception;

    

}