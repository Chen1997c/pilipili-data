package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;

/**
 * 描述： 搜索结果实体
 *
 * @author ChenJianChuan
 * @date 2019/4/23　16:52
 */
@Data
@ToString
@Document(indexName = "pilipili_data", type = "doc")
public class SearchResultEntity {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 名称
     */
    @Field
    private String name;

    /**
     * 图片地址
     */
    private String cover_url;

    /**
     * 类型
     */
    @Field
    private String type;

    @Field
    private Double field1;

    @Field
    private String field2;

    @Field
    private String field3;

    @Field
    private String field4;
}
