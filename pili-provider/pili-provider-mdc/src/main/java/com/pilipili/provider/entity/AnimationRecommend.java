package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧推荐实体
 *
 * @author ChenJianChuan
 * @date 2019/3/26　20:30
 */
@Data
@ToString
@Entity
@Table(name = "animation_recommend")
@EntityListeners(AuditingEntityListener.class)
public class AnimationRecommend {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 封面url
     */
    private String recommendCoverUrl;

    /**
     * 番剧实体
     */
    @JoinColumn(name = "animation_id", nullable = false)
    @OneToOne
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
