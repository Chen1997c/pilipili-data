package com.pilipili.provider.service;

import com.pilipili.provider.dto.CommentAndReplyDTO;
import com.pilipili.provider.entity.Comment;
import com.pilipili.provider.vo.CommentAndReplyPageVO;

import java.util.List;

/**
 * 描述： 评论业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/1　20:09
 */
public interface CommentService {

    /**
     * 根据refId和typeCode获取
     * @param commentAndReplyPageVO
     * @return
     */
    List<CommentAndReplyDTO> listComments(CommentAndReplyPageVO commentAndReplyPageVO);

    /**
     * 获取评论总数
     * @param refId
     * @param type
     * @return
     */
    Long listCommentsCount(Long refId, Integer type);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Long id);
}
