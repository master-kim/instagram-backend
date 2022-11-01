package com.meem.stagram.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meem.stagram.dto.RequestDTO;
import com.meem.stagram.post.IPostService;

import lombok.RequiredArgsConstructor;



/**
 * 설명 : UserController.java > 최초 react.js > axios 이용 연결 컨트롤러    
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.24    김요한    RequstDTO를 통해 Exception 관리 및 공통 데이터 관리 추가
 * 2022.10.26    김요한    로그아웃 추가 (세션 삭제)
 * -------------------------------------------------------------
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor 
public class UserController {
    
    private final IUserService iuserservice;
    
    /**
     * 2022.10.17.김요한.추가 - 스토리 게시판 가져오는 컨트롤러 생성
     * 2022.10.24.김요한.추가 - @Valid 추가 - 잘못 입력시 Exception 오류 처리
     * */
    @PostMapping("/userLogin")
    public List<HashMap<String, Object>> userLogin(HttpServletRequest request , @RequestBody @Valid RequestDTO.userLogin userLogin) throws Exception{
        
        // 세션에 유저 아이디 저장
        HttpSession session = request.getSession();
        String userId = userLogin.getUserId().toString();
        session.setAttribute("user_id", userId);
        
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        try {
            // 해당 유저 데이터가 맞는지 확인
            resultMap = iuserservice.findByUserId(userLogin);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        resultList.add(resultMap);
        
        return resultList;
    }
    
    /**
     * 2022.10.26.김요한.추가 - 로그아웃
     * */
    @PostMapping("/userLogout")
    public HashMap<String, Object> userLogout(HttpServletRequest request) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        // 세션에 유저 아이디 저장
        HttpSession session = request.getSession();
        session.removeAttribute("user_id");
        
        resultMap.put("resultCd", "SUCC");
        resultMap.put("resultMsg", "로그아웃에 성공하였습니다.");
        
        return resultMap;
    }
    
    /**
     * 2022.10.21.김요한.추가 - 유저 회원가입
     * 2022.10.24.김요한.추가 - @Valid 추가 - 잘못 입력시 Exception 오류 처리
     * */
    @PostMapping("/userRegister")
    public List<HashMap<String, Object>> userRegister(HttpServletRequest request , @RequestBody @Valid RequestDTO.userRegister userRegister) throws Exception{
        
        HttpSession session = request.getSession();
        
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        
        HashMap<String, Object> resultMap = new HashMap<>();
        
        try {
            resultMap = iuserservice.userSave(userRegister);
        } catch (Exception e) {
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        resultList.add(resultMap);
        
        return resultList;
    }
    
    /**
     * 2022.10.27.김요한.추가 - 개인페이지 (personal-page)
     * */
    @PostMapping("/personalPage")
    public HashMap<String, Object> personalPage(HttpServletRequest request) throws Exception{
        
        HashMap<String, Object> resultMap = new HashMap<>();
        // 세션에 유저 아이디 저장
        HttpSession session = request.getSession();
        String userId = session.getAttribute("user_id").toString();
        
        try {
            resultMap = iuserservice.findByPersnolPage(userId);
        } catch (Exception e) {
            resultMap.put("resultCd", "FAIL");
            resultMap.put("resultMsg", e.getMessage().toString());
        }
        
        return resultMap;
    }
    
}