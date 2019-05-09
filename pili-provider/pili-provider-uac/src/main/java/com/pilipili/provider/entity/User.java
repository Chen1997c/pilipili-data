package com.pilipili.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 用户实体
 *
 * @author ChenJianChuan
 * @date 2019/3/4　9:07
 */
@Data
@ToString
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名
     */
    @Column(length = 20, nullable = false)
    private String loginName;

    /**
     * 登录密码
     */
    @Column(length = 150, nullable = false)
    private String password;

    /**
     * 昵称
     */
    @Column(length = 10, nullable = false)
    private String nickName;

    /**
     * 头像路径
     */
    private String avatarUrl;

    /**
     * 性别 1男 2女 其他 保密
     */
    private Integer sexCode;

    @Column(columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 状态码(1 正常 0 不可用 -1 删除)
     */
    private Integer statusCd = 1;

    /**
     * 邮箱
     */
    private String email;

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
