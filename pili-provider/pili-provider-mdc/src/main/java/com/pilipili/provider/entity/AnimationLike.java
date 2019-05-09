package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 用户追番实体
 *
 * @author ChenJianChuan
 * @date 2019/3/21　19:24
 */
@Data
@ToString
@Entity
@Table(name = "animation_like")
@EntityListeners(AuditingEntityListener.class)
public class AnimationLike {

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
    @JoinColumn(name = "animation_id",nullable = false)
    @ManyToOne
    private Animation animation;

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
