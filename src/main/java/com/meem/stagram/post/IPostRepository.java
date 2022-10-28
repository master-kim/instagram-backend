package com.meem.stagram.post;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meem.stagram.file.FileEntity;
import com.meem.stagram.story.StoryEntity;
import com.meem.stagram.user.UserEntity;

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
public interface IPostRepository extends JpaRepository<PostEntity, Long> {
    
    // NativQuery : SQL문을 이용하여 테이블 조인 가능
    //@Query(value="    SELECT t1.*                     \r\n"
    //           + "         , t2.user_nick             \r\n"
    //           + "      FROM t_post AS t1             \r\n"
    //           + "LEFT OUTER JOIN t_user_info AS t2   \r\n"
    //           + "        ON (                        \r\n"
    //           + "           t1.user_id = t2.user_id  \r\n"
    //           + "           )", nativeQuery= true)
    List<PostEntity> findByUserIdIn(List<String> strList) throws Exception;

    List<PostEntity> findByUserId(String userId) throws Exception;

    PostEntity findByPostId(Integer postId) throws Exception;
    

}
