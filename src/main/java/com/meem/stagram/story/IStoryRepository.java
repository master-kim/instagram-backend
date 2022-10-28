package com.meem.stagram.story;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meem.stagram.post.PostEntity;

/**
 * 설명 : IStoryRepository.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.14    김요한    최초작성 
 * 2022.10.14    김요한    story에 등록된 아이디를 가져오는 부분추가
 * -------------------------------------------------------------
 */

@Repository
public interface IStoryRepository extends JpaRepository<StoryEntity, Long> {

    // 2022.10.14.김요한.추가 - findByUserIdIn : str배열을 통해 쿼리문 (IN 처럼 사용 할 수 있는 JPA 문법)
    // NativQuery : SQL문을 이용하여 테이블 조인 가능
    //@Query(value="    SELECT t1.*                     \r\n"
    //           + "         , t2.user_nick             \r\n"
    //           + "      FROM t_story AS t1            \r\n"
    //           + "LEFT OUTER JOIN t_user_info AS t2   \r\n"
    //           + "        ON (                        \r\n"
    //           + "           t1.user_id = t2.user_id  \r\n"
    //           + "           )", nativeQuery= true)
    List<StoryEntity> findByUserIdIn(List<String> userId);
    
}
