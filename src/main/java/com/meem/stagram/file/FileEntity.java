package com.meem.stagram.file;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 설명 : PostFileEntity.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    이강현    최초작성 
 * 2022.10.27    김요한    소스정리
 * -------------------------------------------------------------
 */

@Getter                                             // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity
@Table(name = "t_file")   
public class FileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동증분
    private Integer    fileId;
    private String     commonId;
    private String     fileNm;
    private String     uuidFileNm;
    private String     fileType;
    private String     fileStatus;
    private String     fileFolderType;
    private String     createDt; 
    private String     updateDt; 
    
    public static FileEntity fileCreate(HashMap<String, Object> fileResult) {
        
        FileEntity FileEntity = new FileEntity();
        
        FileEntity.fileId         = null;
        FileEntity.commonId       = fileResult.get("common_id").toString();                                             
        FileEntity.fileNm         = fileResult.get("org_file_nm").toString();         
        FileEntity.uuidFileNm     = fileResult.get("uuid_file_nm").toString();        
        FileEntity.fileType       = fileResult.get("file_type").toString();           
        FileEntity.fileStatus     = "CREATE";                                         
        FileEntity.fileFolderType = fileResult.get("file_folder_type").toString();
        FileEntity.createDt       = LocalDate.now().toString();                       
        FileEntity.updateDt       = LocalDate.now().toString();    
        
        return FileEntity;
        
    }

    public static FileEntity fileUpdate(HashMap<String, Object> resultList, FileEntity fileList) {
        
        FileEntity FileEntity = new FileEntity();
        
        FileEntity.fileId         = fileList.getFileId();
        FileEntity.commonId       = resultList.get("common_id").toString();                                             
        FileEntity.fileNm         = resultList.get("org_file_nm").toString();         
        FileEntity.uuidFileNm     = resultList.get("uuid_file_nm").toString();        
        FileEntity.fileType       = resultList.get("file_type").toString();           
        FileEntity.fileStatus     = "UPDATE";                                         
        FileEntity.fileFolderType = resultList.get("file_folder_type").toString();
        FileEntity.createDt       = fileList.getCreateDt();                   
        FileEntity.updateDt       = LocalDate.now().toString();    
        
        return FileEntity;
        
    }
}
