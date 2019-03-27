package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 喂食实体
 *
 * @author ChenJianChuan
 * @date 2019/3/22　9:37
 */
@Data
@ToString
@Entity
@Table(name = "feeding")
@EntityListeners(AuditingEntityListener.class)
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 投食目标id
     */
    private Long refId;

    /**
     * 投食类型 1番剧 2视频
     */
    private Integer feedType;

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
