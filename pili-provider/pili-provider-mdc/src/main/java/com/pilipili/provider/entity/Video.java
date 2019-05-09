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
 * 描述： 视频实体
 *
 * @author ChenJianChuan
 * @date 2019/3/8　17:14
 */
@Data
@ToString
@Entity
@Table(name = "video")
@EntityListeners(AuditingEntityListener.class)
public class Video implements Serializable {

    private static final long serialVersionUID = -718422300676625763L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 题目
     */
    @Column(length = 30, nullable = false)
    private String title;

    /**
     * 投稿人id
     */
    @Column(nullable = false)
    private Long postUserId;

    /**
     * 状态 1通过审核 0待审核 -1未通过审核 -2删除
     */
    private Integer statusCd = 1;

    /**
     * 简介
     */
    private String profiles;

    /**
     * 播放量
     */
    private Long playAmount = 0L;

    /**
     * 时长
     */
    private String duration;

    /**
     * 资源路径
     */
    @Column(nullable = false)
    private String resUrl;

    /**
     * 封面路径
     */
    @Column(nullable = false)
    private String coverUrl;

    /**
     * 发布时间
     */
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date postDate;

    /**
     * 是否推荐
     */
    private Boolean recommend = false;

    /**
     * 分区id
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "channel_sub_id")
    private ChannelSub channelSub;

    /**
     * 是否需要授权 1是 0否
     */
    private Integer authRequired;

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
