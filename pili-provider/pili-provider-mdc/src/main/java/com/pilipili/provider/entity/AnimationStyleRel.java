package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧与番剧风格关联实体
 *
 * @author ChenJianChuan
 * @date 2019/3/9　9:17
 */
@Data
@ToString
@Entity
@Table(name = "animation_style_rel")
@EntityListeners(AuditingEntityListener.class)
public class AnimationStyleRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "animation_id")
    @ManyToOne
    private Animation animation;

    @JoinColumn(name = "animation_style_id")
    @ManyToOne
    private AnimationStyle animationStyle;

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
