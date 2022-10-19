package com.meem.stagram.story;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : StoryEntity.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * -------------------------------------------------------------
 */

@Data                                               // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity 
@Table(name = "t_story")                              // 메인 테이블
public class StoryEntity {
    
    @Id                     // 기본키 매핑 전략 위한 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer   storyId;
    public String    userId;
    public String    userNick;
    public String    fileId;
    public String    content;
    public String    createDt;
    
}

