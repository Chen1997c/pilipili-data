package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 描述： video dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoDAO extends JpaRepository<Video, Long> {

    /**
     * 根据是否推荐查询全部
     * @param recommend
     * @return
     */
    List<Video> findAllByRecommend(boolean recommend);

    /**
     * 根据播放量和发布日期查询全部
     * @param postDate
     * @return
     */
    List<Video> findAllByPostDateAfterOrderByPlayAmountDesc(Date postDate);

}
