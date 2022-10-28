package com.meem.stagram.story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.file.IFileRepository;
import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.post.IPostRepository;
import com.meem.stagram.post.IPostService;
import com.meem.stagram.post.PostEntity;
import com.meem.stagram.user.IUserRepository;
import com.meem.stagram.utils.CommonUtils;
import com.meem.stagram.utils.FileUtils;

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
public class StoryServiceImpl implements IStoryService {
    
    private final IStoryRepository istoryrepository;
    
    private final IFollowRepository ifollowrepository;
    
    private final IFileRepository ifilerepository;
    
    /**
     * 2022.10.14.김요한 - 비즈니스 로직 (스토리 리스트 가져오는 로직) - 유저에 대한 팔로우인원에 대한 스토리 올린 리스트 불러오기
     * 2022.10.25.김요한 - followlist를 공통함수로 가져오게 처리 
     * */
    public List<StoryEntity> storyList(String sessionUserId) throws Exception{
        
        // 결과값을 담는 배열 선언
        List<StoryEntity> resultList = new ArrayList<>();
        
        // 해당 유저에 대한 followList를 가져오는 스트링 배열 (공통 함수 처리) --> 사용방법 (user_id , ifollowrepository) 를 넘겨주면 가져온다.
        List<String> strList = CommonUtils.followList(sessionUserId , ifollowrepository);
        
        // 내 스토리 올린거 빼기 (다르게 다룰 예정)
        strList.remove(sessionUserId);
        // 실질적인 결과 값
        resultList = istoryrepository.findByUserIdIn(strList);
        
        return resultList;
    }
    
    public HashMap<String, Object> storyCreate(MultipartFile fileInfo , RequestDTO.storyCreate storyCreateInfo) throws Exception{
        
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 파일 생성에 대한 결과값을 담는 해시맵
        HashMap<String, Object> fileResult = new HashMap<>();
        
        // 파일 생성 시 필요한 String 값
        String folderType = storyCreateInfo.getFileFolderType().toString();
        
        // 게시글 테이블 (t_post) 에 데이터 넣기 위한 정보
        StoryEntity fileSaveInfo = StoryEntity.storyCreate(storyCreateInfo);
        
        // 반환하는 값은 int 이지만 FileUtils.fileCreate > kind_id 값을 통해 구분해야하므로 String으로 변경
        String storyResult = istoryrepository.save(fileSaveInfo).getStoryId().toString();
        
        if (storyResult.equals(null) || storyResult.equals("")) {
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "게시글 생성이 오류");
        } else {
            fileResult = FileUtils.fileCreate( folderType , storyResult , fileInfo , ifilerepository);
            
            if (fileResult.get("resultCd").toString().toUpperCase().equals("FAIL")) {
                resultList.put("resultCd" , fileResult.get("resultCd"));
                resultList.put("resultMsg" , fileResult.get("resultMsg"));
            } else {
                resultList.put("resultCd" , "SUCC");
                resultList.put("resultMsg" , "정상작동");
            }
        }
        
        return resultList;
    }


}