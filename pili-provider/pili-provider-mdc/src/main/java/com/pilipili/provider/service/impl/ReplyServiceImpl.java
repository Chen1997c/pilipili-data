package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.provider.dao.ReplyDAO;
import com.pilipili.provider.entity.Reply;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.feign.UserFeignService;
import com.pilipili.provider.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 描述： 回复业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/8　15:06
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;
    @Autowired
    private UserFeignService userFeignService;

    @Override
    public Reply addReply(Reply reply) {
        try {
            reply.setPostTime(new Date());
            Reply savedReply = replyDAO.save(reply);
            Optional.of(savedReply).map(Reply::getUser).map(u -> {
                        ResultWrapper resultWrapper = userFeignService.getUser(u.getId());
                        if (resultWrapper.getData() != null) {
                            savedReply.setUser(ObjectUtil.objectConvertToClass(resultWrapper.getData(), User.class));
                        }
                        return u;
                    }
            );
            return savedReply;
        } catch (Exception e) {
            throw new BusinessException("添加回复失败", e);
        }
    }

    @Override
    public Page<Reply> listReply(Long commentId, Integer pageNumber, Integer pageSize) {
        try {
            return replyDAO.findAllByCommentIdOrderByPostTime(commentId, PageRequest.of(pageNumber, pageSize));
        } catch (Exception e) {
            throw new BusinessException("获取回复失败", e);
        }
    }
}
