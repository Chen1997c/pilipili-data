package com.pilipili.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述： 分页实体
 *
 * @author ChenJianChuan
 * @date 2019/3/30　14:51
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageObject<T> {

    /**
     * 数据
     */
    private T content;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 总数据条数
     */
    private Long totalElements;
}
