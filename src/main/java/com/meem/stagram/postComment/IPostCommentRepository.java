package com.meem.stagram.postComment;

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
public interface IPostCommentRepository extends JpaRepository<PostCommentEntity, Long>{
    // 댓글조회
    List<PostCommentEntity> findBypostId(Integer postId) throws Exception;
    
    // 댓글삭제
    @Transactional 
    void deleteByCommentId(Integer commentId) throws Exception;

}
