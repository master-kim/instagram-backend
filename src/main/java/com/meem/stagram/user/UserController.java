package com.meem.stagram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 설명 : TestController.java > 최초 react.js > axios 이용 연결 컨트롤러
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserServiceImpl userserviceimpl;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    //@GetMapping("/userLogin")
    @PostMapping("/userLogin")
    public HashMap<String, Object> userLogin(HttpSession session , HttpServletRequest request 
                             , @RequestBody Map<String, Object> param
                           ) throws Exception{
        
        String userId = param.get("userid").toString();
        
        // 세션에 유저 아이디 저장
        session.setAttribute("user_id", userId);
        
        HashMap<String, Object> result = userserviceimpl.findByUserId(param);
        
        return result;
    }
    
}