package com.meem.stagram.user;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.meem.stagram.dto.RequstDTO.userRegister;
import com.meem.stagram.follow.FollowEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : UserEntity.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.05    김요한    설명 추가 및 소스 정리
 * 2022.10.20    김요한    FollowEntity 조인 완료
 * -------------------------------------------------------------
 */

@Data                                               // 롬복 제공 : getter, setter 만들어줌
@AllArgsConstructor                                 // 롬복 제공 : 필드에 사용한 모든생성자만 만들어주는 역할
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 롬복 제공 : 기본 생성자를 만들어 준다
@Entity                      
@Table(name = "t_user_info")                        // < 테이블 연결 즉 테이블 명 작성
public class UserEntity {
    
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 자동증분

    @Id                     // 기본키 매핑 전략 위한 어노테이션
    public String  userId;
    public String  userNm;
    public String  userNick;
    public String  userPwd;
    public String  userProfile;
    public String  userPhone;
    public String  userEmail;
    public Integer userType;
    public String  createDt;   
    public String  updateDt;
    
    @ManyToOne
    @JoinColumn(name = "userId" , insertable=false, updatable=false)
    public FollowEntity followentity;
    
    @Builder
    public void UserRegister(userRegister userRegister) {
        this.userId = userRegister.getUserId();
        this.userNm = userRegister.getUserName();
        this.userNick = userRegister.getUserNick();
        this.userPwd = userRegister.getUserPwd();
        this.userProfile = "";
        this.userPhone = userRegister.getUserPhone();
        this.userEmail = userRegister.getUserEmail();
        this.userType = 1;
        this.createDt = LocalDate.now().toString();
        this.updateDt = LocalDate.now().toString();
    }
}
