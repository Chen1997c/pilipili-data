package com.pilipili.provider.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述： 番剧索引排序VO
 *
 * @author ChenJianChuan
 * @date 2019/4/11　17:07
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnimationIndexSortVO {

    /**
     * 排序类型 1按照追番人数 2按照更新时间 3按照站内评分
     */
    private Integer sortType;

    /**
     * 排序规则 1升序 2降序
     */
    private Integer sortRule;

    /**
     * 类型规则  1正片 2剧场版 3其他
     */
    private Integer typeRule;

    /**
     * 风格规则
     */
    private Integer styleRule;

    /**
     * 状态规则  1完结 2连载
     */
    private Integer statusRule;

    private Integer pageNumber;

    private Integer pageSize;
}
