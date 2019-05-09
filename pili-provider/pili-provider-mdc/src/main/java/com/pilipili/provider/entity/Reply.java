package com.pilipili.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 回复实体
 *
 * @author ChenJianChuan
 * @date 2019/4/1　14:30
 */
@Data
@ToString
@Entity
@Table(name = "reply")
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 回复类型 1 评论的回复 2回复的回复
     */
    private Integer replyTypeCode;

    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 目标用户id
     */
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    /**
     * 是否删除 1是 0否
     */
    private Integer isDelete = 0;


    /**
     * 用户昵称 非数据库字段
     */
    @Transient
    private String nickName;

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
