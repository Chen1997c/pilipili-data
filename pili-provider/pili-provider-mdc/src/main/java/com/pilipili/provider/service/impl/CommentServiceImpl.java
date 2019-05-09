package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.CommentDAO;
import com.pilipili.provider.dao.ReplyDAO;
import com.pilipili.provider.dto.CommentAndReplyDTO;
import com.pilipili.provider.entity.Comment;
import com.pilipili.provider.entity.Reply;
import com.pilipili.provider.service.CommentService;
import com.pilipili.provider.vo.CommentAndReplyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述： 评论业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/1　20:10
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private ReplyDAO replyDAO;

    @Override
    public List<CommentAndReplyDTO> listComments(CommentAndReplyPageVO commentAndReplyPageVO) {
        try {
            List<CommentAndReplyDTO> commentAndReplyDTOS = new ArrayList<>();
            Pageable pageable = PageRequest.of(commentAndReplyPageVO.getCommentPageNumber(), commentAndReplyPageVO.getCommentPageSize());
            // 查询评论
            Page<Comment> comments = commentDAO.
                    findAllByRefIdAndCommentTypeCode(commentAndReplyPageVO.getRefId(), commentAndReplyPageVO.getCommentTypeCode(), pageable);
            if (comments.getTotalPages() <= 0) {
                return commentAndReplyDTOS;
            }
            comments.getContent().forEach(comment -> {
                CommentAndReplyDTO commentAndReplyDTO = new CommentAndReplyDTO();
                commentAndReplyDTO.setTotalPage(comments.getTotalPages());
                commentAndReplyDTO.setCommentId(comment.getId());
                commentAndReplyDTO.setContent(comment.getContent());
                commentAndReplyDTO.setUser(comment.getUser());
                commentAndReplyDTO.setPostTime(comment.getPostTime());
                commentAndReplyDTO.setIsDelete(comment.getIsDelete());
                // 查询回复
                Pageable replyPageable = PageRequest.of(commentAndReplyPageVO.getReplyPageNumber(), commentAndReplyPageVO.getReplyPageSize());
                Page<Reply> replyList = replyDAO.findAllByCommentIdOrderByPostTime(comment.getId(), replyPageable);
                commentAndReplyDTO.setReplyList(replyList);
                commentAndReplyDTOS.add(commentAndReplyDTO);
            });
            return commentAndReplyDTOS;
        } catch (Exception e) {
            throw new BusinessException("查询评论失败", e);
        }
    }

    @Override
    public Long listCommentsCount(Long refId, Integer type) {
        try {
            List<Comment> comments = commentDAO.findAllByRefIdAndCommentTypeCodeAndIsDelete(refId, type, 0);
            if (CollectionUtils.isEmpty(comments)) {
                return 0L;
            } else {
                List<Long> commentIdList = new ArrayList<>();
                comments.forEach(comment -> commentIdList.add(comment.getId()));
                Long replyCount = replyDAO.countAllByCommentIdInAndIsDelete(commentIdList, 0);
                return comments.size() + replyCount;
            }
        } catch (Exception e) {
            throw new BusinessException("查询评论和回复总数失败", e);
        }
    }

    @Override
    public void addComment(Comment comment) {
        try {
            comment.setPostTime(new Date());
            commentDAO.save(comment);
        } catch (Exception e) {
            throw new BusinessException("保存评论失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void deleteComment(Long id) {
        try {
           Comment comment = commentDAO.findById(id).orElseThrow(() -> new BusinessException("删除评论失败, 未查询到该评论"));
           comment.setIsDelete(1);
           commentDAO.save(comment);
        } catch (Exception e) {
            throw new BusinessException("删除评论失败", e);
        }
    }
}
