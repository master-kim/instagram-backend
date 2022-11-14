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
import com.meem.stagram.postLike.IPostLikeRepository;
import com.meem.stagram.postLike.PostLikeEntity;
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
 * 2022.11.08    김요한    메인 페이지 파일 가져오기 추가 및 네이밍 변경 
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
    
    private final IPostLikeRepository ipostlikerepository;
    
    // 전체 리스트 조회
    public HashMap<String, Object> postList(String sessionUserId) throws Exception{
        // 결과값을 담는 배열 선언
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 해당 유저에 대한 followingList를 가져오는 스트링 배열 (공통 함수 처리)
        List<String> followingList = CommonUtils.followingList(sessionUserId , ifollowrepository);
        followingList.add(sessionUserId);
        // 실질적인 결과 값
        List<PostEntity> postList = ipostrepository.findByUserIdIn(followingList);
        List<String> postIdList = CommonUtils.postIdList(postList);
        List<FileEntity> postImgList = ifilerepository.findByCommonIdInAndFileFolderType(postIdList , "post");
        List<String> userIdList = CommonUtils.postAndUserIdList(postList);
        List<FileEntity> postUserImgList = ifilerepository.findByCommonIdInAndFileFolderType(userIdList , "user");
        
        List<Integer> postLikeCnt = new ArrayList<Integer>();
        // 좋아요 누가 했는지 알기 위한 리스트
        List<List<PostLikeEntity>> postLikeList = new ArrayList<List<PostLikeEntity>>();
        for (int postIdx=0; postIdx < postList.size(); postIdx++) {
            List<PostLikeEntity> postLike = ipostlikerepository.findBypostId(postList.get(postIdx).getPostId());
            postLikeList.add(postLike);
            postLikeCnt.add(postLike.size());
        } 
        
        // 좋아요 개수 알기
        resultMap.put("postList", postList);
        resultMap.put("postLikeList", postLikeList);
        resultMap.put("postLikeCnt", postLikeCnt);
        resultMap.put("postImgList", postImgList);
        resultMap.put("postUserImgList", postUserImgList);
        
        return resultMap;
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

    public HashMap<String, Object> postDoLike(String sessionUserId , RequestDTO.postLike postLikeInfo) throws Exception {
        // 결과값을 담는 해시맵
        HashMap<String, Object> resultMap = new HashMap<>();
        Integer postId = postLikeInfo.getPostId();
        
        List<PostLikeEntity> postList = ipostlikerepository.findByPostIdAndUserId(postId , sessionUserId);
        
        if (postList.size() == 0) {
            PostLikeEntity doPostLike = PostLikeEntity.doPostLike(sessionUserId , postId);
            ipostlikerepository.save(doPostLike);
        } else {
            ipostlikerepository.deleteByLikeId(postList.get(0).getLikeId());
        }
        
        resultMap.put("resultCd", "SUCC");
        resultMap.put("resultMsg", "정상작동");
        
        return resultMap;
    }
    
}