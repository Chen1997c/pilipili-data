package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧观看历史实体
 *
 * @author ChenJianChuan
 * @date 2019/3/27　16:27
 */
@Data
@ToString
@Entity
@Table(name = "animation_history")
@EntityListeners(AuditingEntityListener.class)
public class AnimationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 番剧资源实体
     */
    @JoinColumn(name = "animation_res_id", nullable = false)
    @ManyToOne
    private AnimationRes animationRes;
    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

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
