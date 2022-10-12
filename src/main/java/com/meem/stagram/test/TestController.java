package com.meem.stagram.test;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api")
public class TestController {
    
    @Autowired
    TestServiceImpl testServiceimpl;
    
    @PostMapping("/ip")
    public ResponseEntity<List<TestSample>> ip(HttpServletRequest request) {
        // 요청을 보낸 클라이언트의 IP주소를 반환합니다.
        //String aa = itestinterface.test();
        // request.getRemoteAddr()
        
        List<TestSample> listTest = testServiceimpl.findAll();
        
        //Long cc = (long) 2;
        //Optional<TestSample> selectTest = testServiceimpl.findById(cc);
        //Optional<TestSample> deleteTest = testServiceimpl.findById(cc);
        //Optional<TestSample> updateTest = testServiceimpl.updateById(cc);
        //Optional<TestSample> insertTest = testServiceimpl.save();
        
        return new ResponseEntity<List<TestSample>>(listTest , HttpStatus.OK);
    }
    
}