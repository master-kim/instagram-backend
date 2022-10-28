package com.meem.stagram.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 설명 : RequstDTO.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.27    이강현    최초작성 
 * 2022.10.27    김요한    ResponseDTO 소스 정리
 * -------------------------------------------------------------
 */

@Getter
public class ResponseDTO {
    

    // 2022.10.27.이강현.추가 - FileUpdateData
    @Getter
    @AllArgsConstructor
    public static class FileUpdateData {
        private Integer fileId;
        private String fileNm;
    }
    
    @Getter
    public static class resultHash {
        
        @NotNull
        @NotEmpty(message = "결과 코드값이 존재하지않습니다.")
        private String resultCd;
        
        @NotNull
        private String resultMsg;
        
    }
}
