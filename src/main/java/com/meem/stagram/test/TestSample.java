package com.meem.stagram.test;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

/**
 * 설명 : ITestServiceInterface.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.05    김요한    설명 추가 및 소스 정리
 * -------------------------------------------------------------
 */

@Data                                               // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity(name = "test")                              // < 테이블 연결 즉 테이블 명 작성
public class TestSample {
    @Id                     // 기본키 매핑 전략 위한 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String email;
    
    // @Builder 아래 문구를 통해 vo를 따로 선언 없이 바로 사용 할 수 있음
    // lombok을 이용하여 builder 불필요
    //@Builder
    //private TestSample(String name, String email) {
    //    this.name = name;
    //    this.email = email;
    //}
    
}
