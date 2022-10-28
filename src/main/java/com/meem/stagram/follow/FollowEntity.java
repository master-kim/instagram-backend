package com.meem.stagram.follow;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meem.stagram.dto.RequestDTO.userRegister;
import com.meem.stagram.post.PostEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : PostEntity.java 
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
@Entity
@Table(name = "t_follow")   
public class FollowEntity {
    
    @Id                     // 기본키 매핑 전략 위한 어노테이션
    public String    userId;
    public String    followerList;
    public String    createDt;
    public String    updateDt;
    
    public static FollowEntity followCreate(userRegister userRegister) {
        
        FollowEntity FollowEntity = new FollowEntity();
        
        FollowEntity.userId          = userRegister.getUserId().toString();
        FollowEntity.followerList    = "[{}]";
        FollowEntity.createDt        = LocalDate.now().toString();   
        FollowEntity.updateDt        = LocalDate.now().toString();   
        
        return FollowEntity;
        
    }
    
}
