package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： Comment dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface CommentDAO extends JpaRepository<Comment, Long> {

    /**
     * 根据refId和typeCode查询
     * @param refId
     * @param typeCode
     * @param isDelete
     * @return
     */
    List<Comment> findAllByRefIdAndCommentTypeCodeAndIsDelete(Long refId, Integer typeCode, Integer isDelete);

    /**
     * 根据refId和typeCode分页查询
     * @param refId
     * @param typeCode
     * @param pageable
     * @return
     */
    Page<Comment> findAllByRefIdAndCommentTypeCode(Long refId, Integer typeCode, Pageable pageable);

    /**
     * 根据refId和typeCode查询
     * @param refId
     * @param typeCode
     * @return
     */
    List<Comment> findAllByRefIdAndCommentTypeCode(Long refId, Integer typeCode);
}
