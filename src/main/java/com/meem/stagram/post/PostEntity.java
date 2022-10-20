package com.meem.stagram.post;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meem.stagram.file.FileEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : PostEntity.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.05    김요한    설명 추가 및 소스 정리
 * 2022.10.17    김요한    테이블 매핑처리 완료
 * -------------------------------------------------------------
 */

@Data                                               // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity
@Table(name = "t_post")                              // 메인 테이블
public class PostEntity {

    @Id                     // 기본키 매핑 전략 위한 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer   postId;
    public String    userId;
    public String    postContent;
    public String    postLocation;
    public String    postCommentYn;
    public String    postLikeYn;
    public String    createDt;
    public String    updateDt;
    
    @ManyToOne
    @JoinColumn(name = "postId" , insertable=false, updatable=false)
    public FileEntity fileentity;
    
}
