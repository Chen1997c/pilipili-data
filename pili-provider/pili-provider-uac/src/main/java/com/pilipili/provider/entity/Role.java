package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 角色实体
 *
 * @author ChenJianChuan
 * @date 2019/3/5　10:20
 */
@Data
@ToString
@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(length = 10, nullable = false)
    private String roleName;

    /**
     * 状态码(1 正常 0 不可用 -1 删除)
     */
    private Integer statusCd = 1;

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
