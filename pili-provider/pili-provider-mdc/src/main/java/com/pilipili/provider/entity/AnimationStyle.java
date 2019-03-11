package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧风格实体
 *
 * @author ChenJianChuan
 * @date 2019/3/9　9:16
 */
@Data
@ToString
@Entity
@Table(name = "animation_style")
@EntityListeners(AuditingEntityListener.class)
public class AnimationStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 风格名称
     */
    @Column(length = 10, nullable = false)
    private String styleName;

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
