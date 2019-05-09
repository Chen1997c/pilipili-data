package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧资源实体
 *
 * @author ChenJianChuan
 * @date 2019/3/11　14:44
 */
@Data
@ToString
@Entity
@Table(name = "animation_res")
@EntityListeners(AuditingEntityListener.class)
public class AnimationRes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 播放序列
     */
    @Column(nullable = false)
    private Integer playOrder;

    /**
     * 路径
     */
    @Column(nullable = false)
    private String path;

    /**
     * 封面url
     */
    private String coverUrl;

    /**
     * 集名
     */
    private String episodeName;

    @JoinColumn(name = "animation_id", nullable = false)
    @ManyToOne
    private Animation animation;

    /**
     * 时长
     */
     private String duration;

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
