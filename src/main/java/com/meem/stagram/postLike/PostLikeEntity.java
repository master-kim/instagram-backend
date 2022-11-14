package com.meem.stagram.postLike;

import java.time.LocalDate;

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
 * 2022.11.14    김요한    최초작성 
 * -------------------------------------------------------------
 */

@Getter                                             // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity
@Table(name = "t_post_like")   
public class PostLikeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동증분
    private Integer    likeId;
    private Integer    postId;
    private String     userId;
    private String     createDt; 
    
    public static PostLikeEntity doPostLike(String sessionUserId, Integer i_postId) {
        
        PostLikeEntity PostLikeEntity = new PostLikeEntity();
        
        PostLikeEntity.likeId         = null;
        PostLikeEntity.postId         = i_postId;                                        
        PostLikeEntity.userId         = sessionUserId.toString();                                        
        PostLikeEntity.createDt       = LocalDate.now().toString();                       
        
        return PostLikeEntity;
        
    }
}
