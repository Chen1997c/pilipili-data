package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.CommentAndReplyDTO;
import com.pilipili.provider.entity.Comment;
import com.pilipili.provider.service.CommentService;
import com.pilipili.provider.vo.CommentAndReplyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：评论control
 *
 * @author ChenJianChuan
 * @date 2019/4/1　20:07
 */
@RestController
public class CommentFeignController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResultWrapper listComments(@RequestParam Long refId,
                                      @RequestParam Integer type,
                                      @RequestParam Integer commentPageNumber,
                                      @RequestParam Integer commentPageSize,
                                      @RequestParam Integer replyPageNumber,
                                      @RequestParam Integer replyPageSize) {
        CommentAndReplyPageVO commentAndReplyPageVO =
                new CommentAndReplyPageVO(refId,type,commentPageNumber,commentPageSize,replyPageNumber,replyPageSize);
        List<CommentAndReplyDTO> commentAndReplyDTOS = commentService.listComments(commentAndReplyPageVO);
        return ResultWrapper.responseSuccess(commentAndReplyDTOS);
    }

    @GetMapping("/comments/count")
    public ResultWrapper listComments(@RequestParam Long refId, @RequestParam Integer type) {
        Long count = commentService.listCommentsCount(refId, type);
        return ResultWrapper.responseSuccess(count);
    }

    @PostMapping("/comment")
    public ResultWrapper addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResultWrapper.responseSuccess();
    }

    @PutMapping("/comment")
    public ResultWrapper deleteComment(@RequestParam Long id) {
        commentService.deleteComment(id);
        return ResultWrapper.responseSuccess();
    }
}
