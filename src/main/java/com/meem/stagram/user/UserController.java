package com.meem.stagram.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meem.stagram.follow.FollowEntity;
import com.meem.stagram.follow.FollowServiceImpl;


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
    
    @Autowired
    FollowServiceImpl followserviceimpl;
    
    // 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
    //@GetMapping("/userLogin")
    @PostMapping("/userLogin")
    public HashMap<String, Object> userLogin(HttpServletRequest request , @RequestBody Map<String, Object> param ) throws Exception{
        
        // 세션에 유저 아이디 저장
        HttpSession session = request.getSession();
        String userId = param.get("userid").toString();
        session.setAttribute("user_id", userId);
        
        // 해당 유저 데이터가 맞는지 확인
        HashMap<String, Object> result = userserviceimpl.findByUserId(param);
        
        return result;
    }
    
    // 2022.10.21.김요한.추가 - 유저 회원가입
    @GetMapping("/userRegister")
    //@PostMapping("/userRegister")
    public List<FollowEntity> userRegister(HttpServletRequest request //, @RequestBody Map<String, Object> param 
            ) throws Exception{
        
        HttpSession session = request.getSession();
        //HashMap<String, Object> test = new HashMap<String, Object>();
        List<UserEntity> test = userserviceimpl.findAll();
        
        List<FollowEntity> test2 = followserviceimpl.findAll();
        //HashMap<String, Object> result = userserviceimpl.findByUserId(param);
        
        return test2;
    }
    
}