package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 视频标签关联实体
 *
 * @author ChenJianChuan
 * @date 2019/3/25　14:57
 */
@Data
@ToString
@Entity
@Table(name = "video_label_rel")
@EntityListeners(AuditingEntityListener.class)
public class VideoLabelRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 视频id
     */
    @Column(nullable = false)
    private Long videoId;

    /**
     * 标签实体
     */
    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false)
    private Label label;

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
