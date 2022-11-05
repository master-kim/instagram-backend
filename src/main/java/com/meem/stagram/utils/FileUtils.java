package com.meem.stagram.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.file.FileEntity;
import com.meem.stagram.file.IFileRepository;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : FileUtils.java   (파일 공통 작업 처리)
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    김요한    최초작성 
 * -------------------------------------------------------------
 */

@RequiredArgsConstructor
public class FileUtils {
    
    // 작동안함...
    //@Autowired
    //public final static IFileRepository ifilerepository2;
    //public static IFileRepository ifilerepository2;
    //private final IFileRepository ifilerepository;
    
    /**
     *  전역변수 선언(객체변수)   - 같은 클래스에서 호출 가능  -> 예제 : String test = "";
     *  전역변수 선언(클래스변수)  - 다른 클래스에서 호출 가능  -> 예제 : static String test = "";
     * */
    private final static String fileFolderPath = "C:\\dev\\workspace\\instagram-frontend\\public\\";
    
    // 2022.10.27.김요한.추가 - 파일 생성
    public static HashMap<String, Object> fileCreate(String i_folder, String i_common_id, MultipartFile i_file_info , IFileRepository ifilerepository) throws Exception{
        
        /**
         * 변수 정보 
         * -> i_common_id     : post_id , user_id , story_id
         * -> i_file_info     : 멀티파트로 가져오는 이미지 파일 .. 등등 
         * -> ifilerepository : 레포지토리 선언 동작 안함으로 변수로 넘김
         * */
        
        // 결과를 뿌려주는 해시맵
        HashMap<String, Object> resultList = new HashMap<>();
        
        try {
            
            // 1.실제 파일 저장되는 경로 지정
            String saveFileLocation = fileFolderPath + i_folder;
            
            // 2. 저장 경로에 해당 폴더 존재하지 않을 경우 폴더 생성
            File fileDir = new File(saveFileLocation);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            } else {;}
            
            // 3. UUID 랜덤 생성 (저장되는 파일 이름 암호화)
            UUID uuid = UUID.randomUUID();
            
            // 4. 실제 파일 오리지널 이름
            String fileOrgNm = i_file_info.getOriginalFilename();
            
            // 5. 파일의 확장자 구하기.
            int pos = fileOrgNm.lastIndexOf( "." );
            String fileType = fileOrgNm.substring( pos + 1 );
            
            // 6. 파일 저장 경로 + uuid로 변환된 파일명
            File fileCreate = new File(saveFileLocation + "\\" + uuid + "." + fileType);
            String folderType = i_folder.toLowerCase();
            String uuidFileNm = folderType + "\\" + uuid + "." + fileType;
            // 7. 실제 파일을 저장
            i_file_info.transferTo(fileCreate);
            
            // 8. 저장된 파일에 대한 정보 돌려주기
            resultList.put("resultCd", "SUCC");
            resultList.put("resultMsg", "에러 X");
            resultList.put("uuid_file_nm", uuidFileNm);
            resultList.put("org_file_nm", fileOrgNm);
            resultList.put("file_location", fileCreate);
            resultList.put("file_folder_type", folderType);
            resultList.put("file_type", fileType);
            resultList.put("common_id", i_common_id);
            
            FileEntity fileSaveInfo = FileEntity.fileCreate(resultList);
            
            // 9. DB 파일 테이블에 저장
            ifilerepository.save(fileSaveInfo).getFileId();
            
        } catch(IOException ex){
            resultList.put("resultCd", "FAIL");
            resultList.put("resultMsg", ex.getMessage());
        }
        
        return resultList;
    }

 // 2022.10.27.김요한.추가 - 파일 생성
    public static HashMap<String, Object> fileUpdate(String i_folder, String i_common_id, MultipartFile i_file_info , IFileRepository ifilerepository) throws Exception{
        
        /**
         * 변수 정보 
         * -> i_folder        : story , user , post ...
         * -> i_file_info     : 멀티파트로 가져오는 이미지 파일 .. 등등 
         * -> ifilerepository : 레포지토리 선언 동작 안함으로 변수로 넘김
         * 
         * */
        
        // 결과를 뿌려주는 해시맵
        HashMap<String, Object> resultList = new HashMap<>();
        
        try {
            
            // 1.실제 파일 저장되는 경로 지정
            String saveFileLocation = fileFolderPath + i_folder;
            
            // 2. 저장 경로에 해당 폴더 존재하지 않을 경우 폴더 생성
            File fileDir = new File(saveFileLocation);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            } else {;}
            
            // 3. UUID 랜덤 생성 (저장되는 파일 이름 암호화)
            UUID uuid = UUID.randomUUID();
            
            // 4. 실제 파일 오리지널 이름
            String fileOrgNm = i_file_info.getOriginalFilename();
            
            // 5. 파일의 확장자 구하기.
            int pos = fileOrgNm.lastIndexOf( "." );
            String fileType = fileOrgNm.substring( pos + 1 );
            
            // 6. 파일 저장 경로 + uuid로 변환된 파일명
            File fileCreate = new File(saveFileLocation + "\\" + uuid + "." + fileType);
            String folderType = i_folder.toLowerCase();
            String uuidFileNm = folderType + "\\" + uuid + "." + fileType;
            // 7. 실제 파일을 저장
            i_file_info.transferTo(fileCreate);
            
            // 8. 저장된 파일에 대한 정보 돌려주기
            resultList.put("resultCd", "SUCC");
            resultList.put("resultMsg", "에러 X");
            resultList.put("uuid_file_nm", uuidFileNm);
            resultList.put("org_file_nm", fileOrgNm);
            resultList.put("file_location", fileCreate);
            resultList.put("file_folder_type", folderType);
            resultList.put("file_type", fileType);
            resultList.put("common_id", i_common_id);
            
            // 9. t_file에 대한 id로 구분값 두기위함
            //if (i_folder.toUpperCase().equals("STORY")) {
            //    resultList.put("story_id", Integer.parseInt(i_common_id));
            //} else if (i_folder.toUpperCase().equals("USER")) {
            //} else {
            //    resultList.put("post_id", Integer.parseInt(i_common_id));
            //}
            
            FileEntity fileList = ifilerepository.findByCommonIdAndFileFolderType(i_common_id , i_folder);
            
            FileEntity fileSaveInfo = FileEntity.fileUpdate(resultList , fileList);
            
            // 10. DB 파일 테이블에 저장
            //ifilerepository2.save(fileSaveInfo).getFileId();
            ifilerepository.save(fileSaveInfo).getFileId();
            
        } catch(IOException ex){
            resultList.put("resultCd", "FAIL");
            resultList.put("resultMsg", ex.getMessage());
        }
        
        return resultList;
    }
    
}