package com.meem.stagram.file;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meem.stagram.post.PostEntity;

/**
 * 설명 : IFileRepository.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    이강현    최초작성 
 * 2022.10.27    김요한    소스정리
 * -------------------------------------------------------------
 */

@Repository
public interface IFileRepository extends JpaRepository<FileEntity, Long>{

    FileEntity save(HashMap<String, Object> resultList) throws Exception;
    
    FileEntity findByCommonIdAndFileFolderType(String commonId , String folderType) throws Exception;
    
    List<FileEntity> findByCommonIdIn(List<String> commonIdList) throws Exception;
}
