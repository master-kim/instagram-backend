package com.meem.stagram.post;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.dto.RequestDTO.postUpdate;
import com.meem.stagram.user.UserEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : PostEntity.java
 * ------------------------------------------------------------- 작업일 작업자 작업내용
 * ------------------------------------------------------------- 2022.10.01 김요한
 * 최초작성 2022.10.05 김요한 설명 추가 및 소스 정리 2022.10.17 김요한 테이블 매핑처리 완료 2022.10.20 김요한
 * PostFileEntity 조인완료
 * -------------------------------------------------------------
 */

@Data // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity
@Table(name = "t_post") // 메인 테이블
public class PostEntity {

    @Id // 기본키 매핑 전략 위한 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer postId;
    public String  userId;
    public String  postContent;
    public String  postLocation;
    public String  postCommentYn;
    public String  postLikeYn;
    public String  createDt;
    public String  updateDt;

    
     @ManyToOne
     @JoinColumn(name = "userId" , insertable=false, updatable=false) 
     public UserEntity userentity;
     
     public static PostEntity postCreate(RequestDTO.postCreate postInfo) {
         
         PostEntity PostEntity = new PostEntity();
         
         PostEntity.postId          = null;
         PostEntity.userId          = postInfo.getUserId().toString();
         PostEntity.postContent     = postInfo.getPostContent().toString();
         PostEntity.postLocation    = postInfo.getPostLocation().toString();
         PostEntity.postCommentYn   = postInfo.getPostCommentYn().toString();
         PostEntity.postLikeYn      = postInfo.getPostLikeYn().toString();
         PostEntity.createDt        = LocalDate.now().toString();   
         PostEntity.updateDt        = LocalDate.now().toString();   
         
         return PostEntity;
         
     }

    public static PostEntity postUpdate(RequestDTO.postUpdate postUpdateInfo , PostEntity orgList) {
        
        PostEntity PostEntity = new PostEntity();
        
        PostEntity.postId          = Integer.parseInt(postUpdateInfo.getPostId().toString());
        PostEntity.userId          = postUpdateInfo.getUserId().toString();
        PostEntity.postContent     = postUpdateInfo.getPostContent().toString();
        PostEntity.postLocation    = postUpdateInfo.getPostLocation().toString();
        PostEntity.postCommentYn   = postUpdateInfo.getPostCommentYn().toString();
        PostEntity.postLikeYn      = postUpdateInfo.getPostLikeYn().toString();
        PostEntity.createDt        = orgList.getCreateDt();
        PostEntity.updateDt        = LocalDate.now().toString();   
        
        return PostEntity;
    }

}
