package com.pilipili.provider.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述：评论和回复VO
 *
 * @author ChenJianChuan
 * @date 2019/4/2　20:30
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentAndReplyPageVO {

    /**
     * 番剧id或者视频Id
     */
    private Long refId;

    /**
     * 评论类型
     */
    private Integer commentTypeCode;

    /**
     * 评论当前页
     */
    private Integer commentPageNumber;

    /**
     * 评论页大小
     */
    private Integer commentPageSize;

    /**
     * 回复当前页
     */
    private Integer replyPageNumber;

    /**
     * 回复页大小
     */
    private Integer replyPageSize;
}
