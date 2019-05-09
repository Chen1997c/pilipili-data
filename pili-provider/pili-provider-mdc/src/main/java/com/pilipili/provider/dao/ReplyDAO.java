package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： Comment dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　14:51
 */
public interface ReplyDAO extends JpaRepository<Reply, Long> {

    /**
     * 根据commentIdList统计数量
     * @param commentIdList
     * @param isDelete
     * @return
     */
    Long countAllByCommentIdInAndIsDelete(List<Long> commentIdList, Integer isDelete);


    /**
     *  根据commentId分页查询
     * @param commentId
     * @param pageable
     * @return
     */
    Page<Reply> findAllByCommentIdOrderByPostTime(Long commentId, Pageable pageable);
}
