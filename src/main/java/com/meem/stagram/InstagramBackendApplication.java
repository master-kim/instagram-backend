package com.meem.stagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 설명 : 스프링 부트 실행 위한 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자 작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한  최초작성 
 * 2022.10.01    김요한  같은 패키지가 아닌 곳에 컨트롤러 실행 하는 설정
 * -------------------------------------------------------------
 */

/*
 * 2022.10.01.김요한.추가 : 같은 패키지가 아닌 곳에 컨트롤러 실행 하는 설정
 */
@SpringBootApplication(scanBasePackages = "com.meem.stagram.*")
public class InstagramBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramBackendApplication.class, args);
    }
}
