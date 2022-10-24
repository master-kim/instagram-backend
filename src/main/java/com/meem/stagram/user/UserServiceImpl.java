package com.meem.stagram.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meem.stagram.dto.RequstDTO;
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
            
            //String encUserPwd = DataCipher.encryptDataToString(userId, userPwd);
            //
            //userRegister.setUserPwd(encUserPwd);
            //
            //iuserrepository.save(userRegister);
            
            result.put("resultCd", "SUCC");
            result.put("resultMsg", "가입에 성공하셨습니다.");
            
        } else {
            result.put("resultCd", "FAIL");
            result.put("resultMsg", "동일한 아이디가 존재합니다.");
        }
        
//        if (userRegister.getUserPwd().equals(userRegister.getUserPwdChk())) {
//        } else {
//          result.put("resultCd", "FAIL");
//          result.put("resultMsg", "비밀번호와 비밀번호 체크 부분이 맞지 않습니다.");
//        }
        //
        
//        UserEntity saveData = new UserEntity();
//        
//        HashMap<String, Object> result = new HashMap<>();
//        
//        String userId     = param.get("user_id").toString();
//        String userNick   = param.get("nick_name").toString();
//        String userNm     = param.get("user_name").toString();
//        String userPwd    = param.get("password").toString();
//        String userPwdChk = param.get("password_check").toString();
//        String userEmail  = param.get("user_email").toString();
//        String userPhone  = param.get("user_phone").toString();
//        
//        // 1단계 : 테이블에 동일한 ID 존재 여부 확인
//        List<UserEntity> userList = iuserrepository.findByUserId(userId);
//        
//        String chkResult = "";
//        
//        if (0 == userList.size()) {
//            
//            String[] chkInput = new String[4];
//            chkInput[0] = userId;
//            chkInput[1] = userNick;
//            chkInput[2] = userNm;
//            chkInput[3] = userPwd;
//            
//            // 2단계 : 필수 입력 값 빈값 허용 X 
//            for (int idx = 0 , Looplen = chkInput.length; idx < Looplen; idx++) {
//                chkResult = validation.strEmptyCheck(chkInput[idx]);
//                
//                if (chkResult.equals("ERROR")) {
//                    break;
//                } else {;}
//                
//            }
//            
//            if (chkResult.equals("SUCC")) {
//                
//                // 3단계 : 패스워드 , 패스워드 체크 부분 동일한지 확인
//                chkResult = validation.strCompareCheck(userPwd , userPwdChk);
//                
//                if (chkResult.equals("SUCC")) {
//                    // 4단계 : 이메일 형식 확인
//                    
//                    chkResult = validation.isValidEmail(userEmail);
//                    
//                    if (chkResult.equals("SUCC")) {
//                        
//                        chkResult = validation.isValidPhoneNumber(userPhone);
//                        
//                        if (chkResult.equals("SUCC")) {
//                            
//                            saveData.userId = userId;
//                            saveData.userNick = userNick;
//                            saveData.userNm = userNm;
//                            saveData.userPwd = userPwd;
//                            saveData.userProfile = "";
//                            saveData.userEmail = userEmail;
//                            saveData.userType = 1;
//                            saveData.userPhone = userPhone;
//                            
//                            iuserrepository.save(saveData);
//                            
//                            result.put("resultCd", "SUCC");
//                            result.put("resultMsg", "가입에 성공하셨습니다.");
//                            
//                        } else {
//                            result.put("resultCd", "FAIL");
//                            result.put("resultMsg", "핸드폰 번호를 형식에 맞춰주세요.");
//                        }
//                        
//                    } else {
//                        result.put("resultCd", "FAIL");
//                        result.put("resultMsg", "이메일 형식을 맞춰주세요.");
//                    }
//                    
//                } else {
//                    result.put("resultCd", "FAIL");
//                    result.put("resultMsg", "비밀번호와 비밀번호 체크 부분이 맞지 않습니다.");
//                }
//            } else {
//                result.put("resultCd", "FAIL");
//                result.put("resultMsg", "필수 입력 값을 입력하세요.");
//            }
//            
//        } else {
//            result.put("resultCd", "FAIL");
//            result.put("resultMsg", "동일한 아이디가 존재합니다.");
//        }
        
        return result;
    }

}










