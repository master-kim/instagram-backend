package com.meem.stagram.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.dto.RequstDTO;
import com.meem.stagram.follow.FollowServiceImpl;
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
    
    @Autowired
    FollowServiceImpl followserviceimpl;
    
    //전체 리스트 조회
    public List<UserEntity> findAll() {
        
        List<UserEntity> resultList = iuserrepository.findAll();
        
        return resultList;
        
    }
    
    // 2022.10.20.김요한.수정 - 로그인 시 암호화된 데이터 확인 후 성공 여부 뿌려주기
    public HashMap<String, Object> findByUserId(RequstDTO.userLogin userLogin) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        List<UserEntity> userList = iuserrepository.findByUserId(userLogin.getUserId().toString());
        
        // 암호화 방식 : sha256 단방향 암호화 방식 (salt 함수 사용하지 않고 간단하게 구현) 
        String encUserPwd = DataCipher.encryptDataToString(userLogin.getUserId().toString(), userLogin.getUserPwd().toString());
        //String encUserPwd = param.get("password").toString();
            
        if (userList.size() == 0) {
            
            result.put("resultCd", "FAIL");
            result.put("resultMsg", "해당 아이디가 존재하지 않습니다.");
            
        } else {
            
            String userDbPwd = userList.get(0).userPwd;
            
            if (userDbPwd.equals(encUserPwd)) {
                result.put("resultCd", "SUCC");
                result.put("resultMsg" , "로그인에 성공하셨습니다.");
            } else {
                result.put("resultCd", "FAIL");
                result.put("resultMsg", "비밀번호가 맞지않습니다.");
            }
        }
        
        return result;
    }
    
    public HashMap<String, Object> userSave(RequstDTO.userRegister userRegister) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        String userId = userRegister.getUserId().toString();
        String userPwd = userRegister.getUserPwd().toString();
        
        List<UserEntity> userList = iuserrepository.findByUserId(userId);
        
        if (0 == userList.size()) {
            
            String encUserPwd = DataCipher.encryptDataToString(userId, userPwd);
            userRegister.setUserPwd(encUserPwd);
            
            UserEntity userInsert = new UserEntity();
            userInsert.UserRegister(userRegister);
            iuserrepository.save(userInsert);
            followserviceimpl.followRegister(userRegister);
            
            result.put("resultCd", "SUCC");
            result.put("resultMsg", "가입에 성공하셨습니다.");
            
        } else {
            result.put("resultCd", "FAIL");
            result.put("resultMsg", "동일한 아이디가 존재합니다.");
        }
        
        return result;
    }
}
