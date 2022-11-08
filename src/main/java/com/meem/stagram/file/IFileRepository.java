package com.meem.stagram.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 설명 : IFileRepository.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    이강현    최초작성 
 * 2022.10.27    김요한    소스정리
 * 2022.11.08    김요한    파일 가져오기 조건문 추가
 * -------------------------------------------------------------
 */

@Repository
public interface IFileRepository extends JpaRepository<FileEntity, Long>{
    
    // 파일 정보 가져오기 (단일)
    FileEntity findByCommonIdAndFileFolderType(String commonId , String folderType) throws Exception;
    // 파일 정보 가져오기 (여러개)
    List<FileEntity> findByCommonIdIn(List<String> commonIdList) throws Exception;
    // 파일 정보 가져오기 (여러개 + 폴더타입 추가)
    List<FileEntity> findByCommonIdInAndFileFolderType(List<String> commonIdList, String folderType)throws Exception;
    // 파일 정보 가져오기 (여러개 포함 x + 폴더타입 추가)
    List<FileEntity> findByCommonIdNotInAndFileFolderType(List<String> strList, String string)throws Exception;
}
