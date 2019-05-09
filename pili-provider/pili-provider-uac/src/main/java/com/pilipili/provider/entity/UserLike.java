package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 用户关注实体
 *
 * @author ChenJianChuan
 * @date 2019/4/19　17:27
 */
@Data
@ToString
@Entity
@Table(name = "user_like")
@EntityListeners(AuditingEntityListener.class)
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关注用户
     */
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    /**
     * 被关注用户
     */
    @ManyToOne
    @JoinColumn(nullable = false, name = "like_user_id")
    private User likeUser;

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
