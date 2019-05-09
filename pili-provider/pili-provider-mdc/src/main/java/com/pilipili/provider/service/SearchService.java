package com.pilipili.provider.service;

import com.pilipili.provider.entity.SearchResultEntity;
import com.pilipili.provider.entity.SearchWord;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 描述： 搜索业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface SearchService {

    /**
     * 搜索
     * @param keyword
     * @param type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<SearchResultEntity> search(String keyword, String type, Integer pageNumber, Integer pageSize);

    /**
     * 保存搜索词
     * @param searchWord
     */
    void saveSearchWord(SearchWord searchWord);

    /**
     * 获取热搜词
     * @param pageCount
     * @return
     */
    List<Map> getHotWords(Integer pageCount);

    /**
     * 获取搜索历史
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Page<SearchWord> getSearchHistory(Integer pageNumber, Integer pageSize, Long userId);
}
