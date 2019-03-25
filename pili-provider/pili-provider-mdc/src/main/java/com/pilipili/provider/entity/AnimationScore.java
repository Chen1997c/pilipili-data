package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧评分实体
 *
 * @author ChenJianChuan
 * @date 2019/3/21　19:28
 */
@Data
@ToString
@Entity
@Table(name = "animation_score")
@EntityListeners(AuditingEntityListener.class)
public class AnimationScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 番剧id
     */
    @Column(nullable = false)
    private Long animationId;

    /**
     * 分数
     */
    @Column(nullable = false)
    private Integer score;

    /**
     * 评论消息
     */
    private String commentMessage;

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
