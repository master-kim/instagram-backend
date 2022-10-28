package com.meem.stagram.story;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.user.UserEntity;

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
 * 2022.10.20    김요한    스토리테이블에 UserEntity 조인 
 * 2022.10.24    김요한    스토리테이블에 StoryFileEntity 조인 
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
    public String    storyContent;
    public String    createDt;
    
    @ManyToOne
    @JoinColumn(name = "userId" , insertable=false, updatable=false)
    public UserEntity userentity;

    public static StoryEntity storyCreate(RequestDTO.storyCreate storyCreateInfo) {
        
        StoryEntity StoryEntity = new StoryEntity();
        
        StoryEntity.storyId        = null;
        StoryEntity.userId         = storyCreateInfo.getUserId().toString();
        StoryEntity.storyContent   = storyCreateInfo.getStoryContent().toString();
        StoryEntity.createDt       = LocalDate.now().toString();   
        
        return StoryEntity;
        
    }
    
//    @ManyToOne
//    @JoinColumn(name = "storyId" , insertable=false, updatable=false)
//    public StoryFileEntity storyfileentity;
    
}

