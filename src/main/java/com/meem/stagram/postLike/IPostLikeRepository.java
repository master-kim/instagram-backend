package com.meem.stagram.postLike;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 설명 : IFileRepository.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.11.14    김요한    최초작성 
 * -------------------------------------------------------------
 */

@Repository
public interface IPostLikeRepository extends JpaRepository<PostLikeEntity, Long>{

    List<PostLikeEntity> findBypostId(Integer postId) throws Exception;

    List<PostLikeEntity> findByPostIdAndUserId(Integer postId, String sessionUserId) throws Exception;
    
    @Transactional 
    void deleteByLikeId(Integer likeId);
    

}
