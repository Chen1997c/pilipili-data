package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 角色权限关联实体
 *
 * @author ChenJianChuan
 * @date 2019/3/5　10:26
 */
@Data
@ToString
@Entity
@Table(name = "role_privilege_rel")
@EntityListeners(AuditingEntityListener.class)
public class RolePrivilegeRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "privilege_id")
    private Privilege privilege;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

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
