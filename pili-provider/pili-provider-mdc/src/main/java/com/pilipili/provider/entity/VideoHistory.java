package com.pilipili.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述： 视频历史实体
 *
 * @author ChenJianChuan
 * @date 2019/4/15　11:34
 */
@Data
@ToString
@Entity
@Table(name = "video_history")
@EntityListeners(AuditingEntityListener.class)
public class VideoHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 视频id
     */
    @Column(nullable = false)
    private Long videoId;

    /**
     * 用户id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @LastModifiedDate
    private Date gmtModified;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date gmtCreate;
}
