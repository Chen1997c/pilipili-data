package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.Reply;
import com.pilipili.provider.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 回复control
 *
 * @author ChenJianChuan
 * @date 2019/4/8　15:03
 */
@RestController
public class ReplyFeignController {

    @Autowired
    private ReplyService replyService;

    @RequestMapping("/reply")
    public ResultWrapper addReply(@RequestBody Reply reply) {
        Reply saveReply = replyService.addReply(reply);
        return ResultWrapper.responseSuccess(saveReply);
    }

    @RequestMapping("/replies")
    public ResultWrapper listReply(@RequestParam Long commentId,
                                       @RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize) {
        Page<Reply> replies = replyService.listReply(commentId, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(replies);
    }
}
