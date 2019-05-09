package com.pilipili.provider.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 描述： 搜索词实体
 *
 * @author ChenJianChuan
 * @date 2019/4/24　14:35
 */
@Data
@ToString
@Document(collection = "search_word")
public class SearchWord {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 搜索用户
     */
    private Long userId;

    /**
     * 搜索时间
     */
    private Date searchTime;
}
