package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 视频收藏实体
 *
 * @author ChenJianChuan
 * @date 2019/3/22　9:29
 */
@Data
@ToString
@Entity
@Table(name = "video_collection")
@EntityListeners(AuditingEntityListener.class)
public class VideoCollection {

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
     * 收藏夹id 0表示默认收藏夹
     */
    @Column(nullable = false)
    private Long videoFavoriteId;

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
