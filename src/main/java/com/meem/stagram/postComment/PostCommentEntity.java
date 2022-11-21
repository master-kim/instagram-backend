package com.meem.stagram.postComment;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.dto.RequestDTO.updateComment;
import com.meem.stagram.user.UserEntity;

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
@Table(name = "t_comment")   
public class PostCommentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동증분
    private Integer    commentId;
    private Integer    postId;
    private Integer    parentId;
    private String     userId;
    private String     content;
    private String     createDt; 
    private String     updateDt; 
    
    @ManyToOne
    @JoinColumn(name = "userId" , insertable=false, updatable=false)
    public UserEntity userentity;
    
    public static PostCommentEntity doPostComment(String sessionUserId, RequestDTO.postComment postCommentInfo) {
        
        PostCommentEntity PostCommentEntity = new PostCommentEntity();
        
        PostCommentEntity.commentId     = null;
        PostCommentEntity.postId        = postCommentInfo.getPostId();                                        
        PostCommentEntity.parentId      = 0;                                        
        PostCommentEntity.userId        = sessionUserId.toString();                                        
        PostCommentEntity.content       = postCommentInfo.getPostComment().toString();                                        
        PostCommentEntity.createDt      = LocalDate.now().toString();                       
        PostCommentEntity.updateDt      = LocalDate.now().toString();                       
        
        return PostCommentEntity;
        
    }

    public static PostCommentEntity updateComment(String sessionUserId, updateComment updateCommentInfo) {
        
        PostCommentEntity PostCommentEntity = new PostCommentEntity();
        
        PostCommentEntity.commentId     = updateCommentInfo.getCommentId();
        PostCommentEntity.postId        = updateCommentInfo.getPostId();                                        
        PostCommentEntity.parentId      = 0;                                        
        PostCommentEntity.userId        = sessionUserId.toString();                                        
        PostCommentEntity.content       = updateCommentInfo.getCommentContent().toString();                                        
        PostCommentEntity.createDt      = LocalDate.now().toString();                       
        PostCommentEntity.updateDt      = LocalDate.now().toString();                       
        
        return PostCommentEntity;
    }
}
