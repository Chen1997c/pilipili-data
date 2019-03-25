package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述： 属性值实体
 *
 * @author ChenJianChuan
 * @date 2019/3/21　10:50
 */
@Data
@ToString
@Entity
@Table(name = "attr_value")
@EntityListeners(AuditingEntityListener.class)
public class AttrValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 属性值代码
     */
    @Column(nullable = false)
    private String valueCode;

    /**
     * 属性值名称
     */
    @Column(length = 20, nullable = false)
    private String valueName;

    /**
     * 属性id
     */
    @Column(nullable = false)
    private Long attrId;

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
