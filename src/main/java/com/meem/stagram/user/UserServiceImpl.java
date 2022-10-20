package com.meem.stagram.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.utils.DataCipher;

import lombok.RequiredArgsConstructor;

/**
 * 설명 : UserServiceImpl
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.20    김요한    데이터 암호화 추가 (login) 
 * -------------------------------------------------------------
 */


@Service("userserviceimpl")
@RequiredArgsConstructor
public class UserServiceImpl {
    
    @Autowired
    IUserRepository iuserrepository;

    
    //전체 리스트 조회
    public List<UserEntity> findAll() {
        
        List<UserEntity> resultList = iuserrepository.findAll();
        
        return resultList;
        
    }
    
    // 특정 ID 조회
    public HashMap<String, Object> findByUserId(Map<String, Object> param) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        List<UserEntity> userList = iuserrepository.findByUserId(param.get("userid").toString());
        
        // 암호화 방식 : sha256 단방향 암호화 방식 (salt 함수 사용하지 않고 간단하게 구현) 
        String encUserPwd = DataCipher.encryptDataToString(param.get("userid").toString(), param.get("password").toString());
        //String encUserPwd = param.get("password").toString();
            
        if (userList.size() == 0) {
            
            result.put("result", "FAIL");
            result.put("resultMsg", "해당 아이디가 존재하지 않습니다.");
            
        } else {
            
            String userDbPwd = userList.get(0).userPwd;
            
            if (userDbPwd.equals(encUserPwd)) {
                result.put("resultCd", "SUCC");
                result.put("resultMsg" , "성공");
            } else {
                result.put("resultCd", "FAIL");
                result.put("resultMsg", "비밀번호가 맞지않습니다.");
            }
        }
        
        return result;
    }

}










