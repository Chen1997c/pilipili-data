package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 描述： 分区实体
 *
 * @author ChenJianChuan
 * @date 2019/4/13　20:45
 */
@Data
@ToString
@Entity
@Table(name = "channel")
@EntityListeners(AuditingEntityListener.class)
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分区名
     */
    @Column(nullable = false, length = 5)
    private String channelName;

    @JoinColumn(name = "channelId")
    @OneToMany
    private List<ChannelSub> channelSubList;

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
