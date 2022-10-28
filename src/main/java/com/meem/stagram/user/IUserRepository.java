package com.meem.stagram.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    // native 쿼리 사용 방법
    //@Query(value="SELECT t1.*\r\n"
    //        + "     , t2.follower_list \r\n"
    //        + "  FROM t_user_info AS t1\r\n"
    //        + "LEFT OUTER JOIN t_follow AS t2 \r\n"
    //        + "    ON (\r\n"
    //        + "       t1.user_id = t2.user_id \r\n"
    //        + "       )", nativeQuery= true)
    //List<UserEntity> findAll();
    
    // 추가
    List<UserEntity> findByUserId(String userId) throws Exception;
    
    // not In
    List<UserEntity> findByUserIdNotIn(List<String> strList) throws Exception;
    
}
