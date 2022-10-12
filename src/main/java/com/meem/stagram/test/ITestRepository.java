package com.meem.stagram.test;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 설명 : ITestServiceInterface.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * 2022.10.05    김요한    인터페이스에 JpaRepository 연장 추가 
 * -------------------------------------------------------------
 */

// 2022.10.05.김요한 : JpaRepository는 인터페이스를 준비하기만 하면, 자동으로 클래스를 만들고 Bean을 생성하는 것
@Repository
public interface ITestRepository extends JpaRepository<TestSample, Long> {
    
    // 안에 내용은 JpaRepository 를 이용하므로 작성 불필요
    
    // 특정 ID 한개만 찾아옴
    //public List<TestSample> findById(String id);
    // 특정 이름 검색 
    //public List<TestSample> findByName(String name);
    //like 검색 기능
    //public List<TestSample> findByNameLike(String keyword);
    
    
}
