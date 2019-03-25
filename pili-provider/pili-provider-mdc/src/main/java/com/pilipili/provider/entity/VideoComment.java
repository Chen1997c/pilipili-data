package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 视频评论实体
 *
 * @author ChenJianChuan
 * @date 2019/3/8　17:16
 */
@Data
@ToString
@Entity
@Table(name = "video_comment")
@EntityListeners(AuditingEntityListener.class)
public class VideoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 视频id
     */
    @Column(nullable = false)
    private Long videoId;

    /**
     * 上级id
     */
    private Long parentCommentId;

    /**
     * 发表时间
     */
    private Date postTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date gmtModified;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date gmtCreate;
}
