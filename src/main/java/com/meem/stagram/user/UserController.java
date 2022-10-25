package com.meem.stagram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meem.stagram.dto.RequstDTO;



/**
 * 설명 : UserController.java > 최초 react.js > axios 이용 연결 컨트롤러    
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.24    김요한    RequstDTO를 통해 Exception 관리 및 공통 데이터 관리 추가
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserServiceImpl userserviceimpl;
    
    /**
     * 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
     * 2022.10.24.김요한.추가 - @Valid 추가 - 잘못 입력시 Exception 오류 처리
     * */
    @PostMapping("/userLogin")
    public HashMap<String, Object> userLogin(HttpServletRequest request , @RequestBody @Valid RequstDTO.userLogin userLogin) throws Exception{
        
        // 세션에 유저 아이디 저장
        HttpSession session = request.getSession();
        String userId = userLogin.getUserId().toString();
        session.setAttribute("user_id", userId);
        
        // 해당 유저 데이터가 맞는지 확인
        HashMap<String, Object> result = userserviceimpl.findByUserId(userLogin);
        
        return result;
    }
    
    /**
     * 2022.10.21.김요한.추가 - 유저 회원가입
     * 2022.10.24.김요한.추가 - @Valid 추가 - 잘못 입력시 Exception 오류 처리
     * */
    @PostMapping("/userRegister")
    public HashMap<String, Object> userRegister(HttpServletRequest request , @RequestBody @Valid RequstDTO.userRegister userRegister) throws Exception{
        
        HttpSession session = request.getSession();
        
        HashMap<String, Object> result = userserviceimpl.userSave(userRegister);
        
        return result;
    }
}