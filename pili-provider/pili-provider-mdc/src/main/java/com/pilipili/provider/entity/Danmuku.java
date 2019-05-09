package com.pilipili.provider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

/**
 * 描述： 弹幕实体
 *
 * @author ChenJianChuan
 * @date 2019/4/21　15:13
 */
@TypeAlias("danmuku")
@Data
@ToString
public class Danmuku {

    /**
     * id
     */
    private String id;

    /**
     * 内容
     */
    private String text;

    /**
     * 颜色
     */
    private String color;

    /**
     * 时间
     */
    private Double time;

    /**
     * 关联视频表示v/a + id
     */
    private String refId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
