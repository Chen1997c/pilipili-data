package com.pilipili.provider.dao;

import com.pilipili.provider.entity.SearchWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * 描述： searchWord dao
 *
 * @author ChenJianChuan
 * @date 2019/34/24　14:24
 */
public interface SearchWordDAO extends MongoRepository<SearchWord, String> {

    /**
     * 根据userId和keyword查询
     * @param userId
     * @param keyword
     * @return
     */
    SearchWord findByUserIdAndAndKeyword(Long userId, String keyword);

    /**
     * 根据userId查询
     * @param userId
     * @param pageable
     * @return
     */
    Page<SearchWord> findByUserIdOrderBySearchTimeDesc(Long userId, Pageable pageable);
}
