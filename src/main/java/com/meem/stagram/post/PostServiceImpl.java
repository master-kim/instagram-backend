package com.meem.stagram.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.file.FileEntity;
import com.meem.stagram.file.IFileRepository;
import com.meem.stagram.follow.IFollowRepository;
import com.meem.stagram.utils.CommonUtils;
import com.meem.stagram.utils.FileUtils;

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
public class PostServiceImpl implements IPostService {
    
    private final IPostRepository ipostrepository;
    
    private final IFollowRepository ifollowrepository;
    
    private final IFileRepository ifilerepository;
    
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
    
    // 게시글 상세 페이지 조회
    public HashMap<String, Object> postDetail(Integer postId) throws Exception{
        
        //선택한 게시글에 대한 postId로 post 데이터 가져와서 리턴
        PostEntity postInfo = ipostrepository.findByPostId(postId);
        
        String commonId = Integer.toString(postId);
        
        List<String> commonIdList = new ArrayList<String>();
        
        commonIdList.add(commonId);
        
        List<FileEntity> fileInfo = ifilerepository.findByCommonIdIn(commonIdList);
        
        HashMap<String, Object> resultMap  = new HashMap<String, Object>();
        
        resultMap .put("postInfo", postInfo);
        resultMap .put("fileInfo", fileInfo);
        
        return resultMap ;
    }
    
    // 게시글 작성
    public HashMap<String, Object> postCreate(MultipartFile fileInfo , RequestDTO.postCreate postCreateInfo) throws Exception{
        
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 파일 생성에 대한 결과값을 담는 해시맵
        HashMap<String, Object> fileResult = new HashMap<>();
        
        // 파일 생성 시 필요한 String 값
        String folderType = postCreateInfo.getFileFolderType().toString();
        
        // 게시글 테이블 (t_post) 에 데이터 넣기 위한 정보
        PostEntity fileSaveInfo = PostEntity.postCreate(postCreateInfo);
        
        // 반환하는 값은 int 이지만 FileUtils.fileCreate > kind_id 값을 통해 구분해야하므로 String으로 변경
        String postResult = ipostrepository.save(fileSaveInfo).getPostId().toString();
        
        if (postResult.equals(null) || postResult.equals("")) {
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "게시글 생성이 오류");
        } else {
            fileResult = FileUtils.fileCreate( folderType ,postResult , fileInfo , ifilerepository);
            
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

    @Override
    public HashMap<String, Object> postUpdate(MultipartFile fileInfo, RequestDTO.postUpdate postUpdateInfo) throws Exception {
        
        // 결과값을 담는 해시맵
        HashMap<String, Object> resultList = new HashMap<>();
        
        // 파일 생성에 대한 결과값을 담는 해시맵
        HashMap<String, Object> fileResult = new HashMap<>();
        
        // 파일 생성 시 필요한 String 값
        String folderType = postUpdateInfo.getFileFolderType().toString();
        
        /**
         * int     : 산술 연산이 가능하다. null로 초기화 할 수 없다.
         * integer : null 값 처리가 용이하기 때문에 SQL과 연동할 경우 처리가 용이하다.
         * */
        
        // 기존 데이터 CreateDt 가져오기위해 Select
        PostEntity postList = ipostrepository.findByPostId(Integer.parseInt(postUpdateInfo.getPostId().toString()));
        
        // 게시글 테이블 (t_post) 에 데이터 넣기 위한 정보
        PostEntity postUpdate = PostEntity.postUpdate(postUpdateInfo , postList);
        
        // 반환하는 값은 int 이지만 FileUtils.fileCreate > kind_id 값을 통해 구분해야하므로 String으로 변경
        String postId = ipostrepository.save(postUpdate).getPostId().toString();
        
        if (postId.equals(null) || postId.equals("")) {
            resultList.put("resultCd" , "FAIL");
            resultList.put("resultMsg" , "게시글 생성이 오류");
        } else {
            fileResult = FileUtils.fileUpdate( folderType , postId , fileInfo , ifilerepository);
            
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