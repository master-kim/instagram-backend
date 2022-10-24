package com.meem.stagram.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 설명 : RequstDTO.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.20    김요한    최초작성 
 * 2022.10.24    김요한    로그인 , 회원가입 DTO 이너클래스 생성 
 * -------------------------------------------------------------
 */

@NoArgsConstructor
@Data
public class RequstDTO {
    
    // 2022.10.24.김요한.추가 - UserLogin시 담는 영역
    @Data
    public static class userLogin {
        
        @NotNull
        @NotBlank(message = "유저 아이디를 입력하셔야 합니다.")
        @NotEmpty(message = "유저 아이디에는 스페이스를 포함 하실수 없습니다.")
        String userId;
        
        @NotNull
        @NotBlank(message = "유저 비밀번호를 입력하셔야 합니다.")
        @NotEmpty(message = "유저 비밀번호에는 스페이스를 포함 하실수 없습니다.")
        String userPwd;
        
        @Builder
        public userLogin(String i_user_id, String i_user_pwd) {
            this.userId = i_user_id;
            this.userPwd = i_user_pwd;
        }
        
    }
    
    // 2022.10.24.김요한.추가 - UserRegister 시 담는 영역
    @Data
    public static class userRegister {
        
        @NotNull
        @NotBlank(message = "유저 아이디를 반드시 입력해야합니다.")
        /* @Size(min = 2, max = 16, message = "아이디를 2~16자 사이로 입력해주세요.") */
        @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "유저 아이디는 영어와 숫자로 포함해서 6~16자리 이내로 입력해주세요.")
        String userId;
        
        @NotNull
        @NotBlank(message = "유저 닉네임을 반드시 입력해야합니다.")
        @NotEmpty(message = "유저 닉네임에는 스페이스를 포함 하실수 없습니다.")
        String userNick;
        
        @NotNull
        @NotBlank(message = "유저 이름을 반드시 입력해야합니다.")
        @NotEmpty(message = "유저 이름에는 스페이스를 포함 하실수 없습니다.")
        /* @Pattern(regexp="[a-zA-Z|가-힣]", message = "유저이름에는 한글과 영어만 입력가능합니다.") */
        @Size(min = 2, max = 8, message = "유저이름은 2~8자 사이로 입력해주세요.")
        String userName;
        
        @NotNull
        @NotBlank(message = "유저 패스워드를 반드시 입력해야합니다.")
        @Pattern(regexp="[a-zA-Z1-9]{6,18}", message = "비밀번호는 영어와 숫자로 포함해서 6~18자리 이내로 입력해주세요.")
        @NotEmpty(message = "유저 패스워드에는 스페이스를 포함 하실수 없습니다.")
        String userPwd;
        
        @NotNull
        @NotBlank(message = "유저 패스워드 확인을 반드시 입력해야합니다.")
        String userPwdChk;
        
        @Email
        @NotEmpty(message = "유저 이메일에는 스페이스를 포함 하실수 없습니다.")
        String userEmail;
        
        @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
        @NotEmpty(message = "유저 핸드폰에는 스페이스를 포함 하실수 없습니다.")
        String userPhone;
        
        @Builder
        public userRegister(String i_user_id, String i_user_nick , String i_user_name, String i_user_pwd, String i_user_pwd_chk, String i_user_email, String i_user_phone) {
            this.userId = i_user_id;
            this.userNick = i_user_nick;
            this.userName = i_user_name;
            this.userPwd = i_user_pwd;
            this.userPwdChk = i_user_pwd_chk;
            this.userEmail = i_user_email;
            this.userPhone = i_user_phone;
        }
        
    }
}
