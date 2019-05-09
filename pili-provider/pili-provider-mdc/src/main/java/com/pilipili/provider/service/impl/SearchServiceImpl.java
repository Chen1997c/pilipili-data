package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.SearchWordDAO;
import com.pilipili.provider.entity.SearchResultEntity;
import com.pilipili.provider.entity.SearchWord;
import com.pilipili.provider.service.SearchService;
import com.pilipili.provider.util.MyResultMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 描述： 搜索业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/23　16:59
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private MyResultMapper myResultMapper;
    @Autowired
    private SearchWordDAO searchWordDAO;
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String MAGIC_TYPE_A = "a";

    @Override
    public Page<SearchResultEntity> search(String keyword, String type, Integer pageNumber, Integer pageSize) {
        try {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.preTags("<span style='color:rgb(251, 114, 153)'>");
            highlightBuilder.postTags("</span>");
            highlightBuilder.field("name");
            QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", keyword);
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                    .withQuery(queryBuilder)
                    .withPageable(pageable)
                    .withHighlightBuilder(highlightBuilder)
                    .withHighlightFields(highlightBuilder.fields().get(0));
            if (!StringUtils.isEmpty(type)) {
                // 直接添加filter type为a的时候什么也搜索不到
                if (MAGIC_TYPE_A.equals(type)) {
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    boolQueryBuilder.mustNot(QueryBuilders.fuzzyQuery("type", "v"));
                    boolQueryBuilder.mustNot(QueryBuilders.fuzzyQuery("type", "u"));
                    builder.withFilter(boolQueryBuilder);
                } else {
                    builder.withFilter(QueryBuilders.fuzzyQuery("type", type));
                }
            }
            return elasticsearchTemplate.queryForPage(builder.build(), SearchResultEntity.class, myResultMapper);
        } catch (Exception e) {
            throw new BusinessException("搜索失败", e);
        }
    }

    @Override
    public void saveSearchWord(SearchWord searchWord) {
        try {
            SearchWord querySearchWord = searchWordDAO.findByUserIdAndAndKeyword(searchWord.getUserId(), searchWord.getKeyword());
            if (querySearchWord == null) {
                searchWord.setSearchTime(new Date());
                searchWordDAO.save(searchWord);
            } else {
                querySearchWord.setSearchTime(new Date());
                searchWordDAO.save(querySearchWord);
            }
        } catch (Exception e) {
            throw new BusinessException("保存搜索词失败", e);
        }
    }

    @Override
    public List<Map> getHotWords(Integer pageCount) {
        try {
            // 分组
            GroupOperation groupOperation = Aggregation.group("keyword").count().as("count");
            // 排序
            SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "count");
            // 限量
            LimitOperation limit = Aggregation.limit(pageCount);
            Aggregation aggregation = Aggregation.newAggregation(SearchWord.class, groupOperation, sort, limit);
            return mongoTemplate.aggregate(aggregation, SearchWord.class, Map.class).getMappedResults();
        } catch (Exception e) {
            throw new BusinessException("查询热搜词失败", e);
        }
    }

    @Override
    public Page<SearchWord> getSearchHistory(Integer pageNumber, Integer pageSize, Long userId) {
        try {
            return searchWordDAO.findByUserIdOrderBySearchTimeDesc(userId, PageRequest.of(pageNumber, pageSize));
        } catch (Exception e) {
            throw new BusinessException("查询搜索记录失败", e);
        }
    }
}
