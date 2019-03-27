package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 番剧实体
 *
 * @author ChenJianChuan
 * @date 2019/3/9　8:47
 */
@Data
@ToString
@Entity
@Table(name = "animation")
@EntityListeners(AuditingEntityListener.class)
public class Animation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(length = 50, nullable = false)
    private String name;


    /**
     * 类型 1正片 2剧场版 3其他
     */
    @Column(nullable = false)
    private Integer type;

    /**
     * 播放状态 1完结 2连载
     */
    @Column(nullable = false)
    private Integer playStatus;

    /**
     * 地区代码 1:国产 2日本 3美国 4其他
     */
    @Column(nullable = false)
    private Integer districtCd;

    /**
     * 开播季度
     */
    private Integer startSeason;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

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
