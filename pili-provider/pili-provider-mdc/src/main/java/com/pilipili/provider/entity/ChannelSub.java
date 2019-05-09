package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 子分区实体
 *
 * @author ChenJianChuan
 * @date 2019/4/13　20:47
 */
@Data
@ToString
@Entity
@Table(name = "channel_sub")
@EntityListeners(AuditingEntityListener.class)
public class ChannelSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上级分区id
     */
    @Column(nullable = false)
    private Long channelId;

    /**
     * 子分区名
     */
    @Column(nullable = false, length = 20)
    private String channelSubName;

    /**
     * 分区描述
     */
    @Column(nullable = false, length = 50)
    private String channelDescription;

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
