package com.meem.stagram.test;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * 설명 : TestServiceImpl.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * -------------------------------------------------------------
 */

/*
 * @RequiredArgsConstructor 이용 해석
 * 기존 방법
 * (1) Autowired
 * @Autowired
 * TestSampleRepository TestSampleRepository;
 * 
 * (2) 생성자 함수 생성
 * 
 * TestSampleRepository TestSampleRepository;
 * 
 * @Autowired
 * public TestServiceImpl(TestSampleRepository TestSampleRepository) {
 *     this.TestSampleRepository = TestSampleRepository;
 * }
 * 
 * (3) 롬복 이용
 * TestSampleRepository TestSampleRepository;
 * 
 * */

@Service("testserviceimpl")
@RequiredArgsConstructor
public class TestServiceImpl {
    
    @Autowired
    ITestRepository itestrepository;

    // 전체 리스트 조회
    public List<TestSample> findAll() {
        
        List<TestSample> test = itestrepository.findAll();
        
        return test;
        
    }
    
    // 특정 ID 조회
    public Optional<TestSample> findById(Long id) {
        
        Optional<TestSample> test = itestrepository.findById(id);
        
        return test;
        
    }
    
    // 특정 ID 삭제
    public void deleteById(Long mbrNo) {
        itestrepository.deleteById(mbrNo);
    }
    
    // 특정 데이터 저장
    //public MemberVO save(MemberVO member) {
    //    itestrepository.save(member);
    //    return member;
    //}
    
    // 특정 데이터 수정
    //public void updateById(Long mbrNo, MemberVO member) {
    //    Optional<MemberVO> e = itestrepository.findById(mbrNo);
    //
    //    if (e.isPresent()) {
    //        e.get().setMbrNo(member.getMbrNo());
    //        e.get().setId(member.getId());
    //        e.get().setName(member.getName());
    //        itestrepository.save(member);
    //    }
    //}
    
}