package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧详细信息实体
 *
 * @author ChenJianChuan
 * @date 2019/3/9　9:04
 */
@Data
@ToString
@Entity
@Table(name = "animation_details")
@EntityListeners(AuditingEntityListener.class)
public class AnimationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 原名
     */
    @Column(length = 50, nullable = false)
    private String originName;

    /**
     * 集数
     */
    private Integer episodes;

    /**
     * 简介
     */
    private String profiles;

    /**
     * 更新信息
     */
    @Column(length = 50)
    private String updateInfo;

    /**
     * 播放量
     */
    private Long playAmount = 0L;

    /**
     * 番剧实体
     */
    @JoinColumn(name = "animation_id", nullable = false)
    @OneToOne
    private Animation animation;

    /**
     * 封面url
     */
    private String coverUrl;

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
