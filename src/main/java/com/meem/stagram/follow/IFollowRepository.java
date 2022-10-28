package com.meem.stagram.follow;


import java.util.List;

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
public interface IFollowRepository extends JpaRepository<FollowEntity, Long> {

    List<FollowEntity> findByUserId(String userId) throws Exception;
    
}
