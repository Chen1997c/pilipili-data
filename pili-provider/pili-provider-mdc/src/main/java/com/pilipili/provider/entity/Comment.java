package com.pilipili.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述： 评论实体
 *
 * @author ChenJianChuan
 * @date 2019/3/8　17:16
 */
@Data
@ToString
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Serializable {

    private static final long serialVersionUID = 6181952018369519835L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 评论指向
     */
    @Column(nullable = false)
    private Long refId;

    /**
     * 评论类型 1番剧评论 2视频评论
     */
    private Integer commentTypeCode;


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
     * 是否删除 1是 0否
     */
    private Integer isDelete = 0;

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
