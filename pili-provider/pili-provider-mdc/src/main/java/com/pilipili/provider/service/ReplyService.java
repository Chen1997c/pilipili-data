package com.pilipili.provider.service;

import com.pilipili.provider.entity.Reply;
import org.springframework.data.domain.Page;

/**
 * 描述： 回复业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface ReplyService {

    /**
     * 添加回复
     * @param reply
     */
    Reply addReply(Reply reply);

    /**
     * 根据commentId获取分页数据
     * @param commentId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Reply> listReply(Long commentId, Integer pageNumber, Integer pageSize);
}
