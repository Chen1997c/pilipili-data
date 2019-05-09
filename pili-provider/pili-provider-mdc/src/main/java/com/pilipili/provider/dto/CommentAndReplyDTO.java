package com.pilipili.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pilipili.provider.entity.Reply;
import com.pilipili.provider.entity.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * 描述： 评论和回复dto
 *
 * @author ChenJianChuan
 * @date 2019/4/1　20:05
 */
@Data
@ToString
public class CommentAndReplyDTO {

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户实体
     */
    private User user;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;

    /**
     * 回复实体列表
     */
    private Page<Reply> replyList;

    /**
     * 页数
     */
    private Integer totalPage;

    /**
     * 是否删除
     */
    private Integer isDelete;

}
