package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 视频收藏夹实体
 *
 * @author ChenJianChuan
 * @date 2019/4/16　22:24
 */
@Data
@ToString
@Entity
@Table(name = "video_favorite")
@EntityListeners(AuditingEntityListener.class)

public class VideoFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 简介
     */
    private String profiles;

    /**
     * 是否公开 1是 0否
     */
    @Column(nullable = false)
    private Integer isPublic;

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
