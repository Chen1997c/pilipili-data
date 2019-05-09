package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.SearchResultEntity;
import com.pilipili.provider.entity.SearchWord;
import com.pilipili.provider.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 描述： 搜索control
 *
 * @author ChenJianChuan
 * @date 2019/4/23　16:47
 */
@RestController
@RequestMapping("/search")
public class SearchFeignController {

    @Autowired
    private SearchService searchService;

    @GetMapping("")
    public ResultWrapper search(@RequestParam String keyword,
                                @RequestParam(required = false) String type,
                                @RequestParam Integer pageNumber,
                                @RequestParam Integer pageSize) {
        Page<SearchResultEntity> resultEntities = searchService.search(keyword, type, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(resultEntities);
    }

    @PostMapping("/keyword")
    public ResultWrapper saveSearchWord(@RequestBody SearchWord searchWord) {
        searchService.saveSearchWord(searchWord);
        return ResultWrapper.responseSuccess();
    }

    @GetMapping("/keyword-hot")
    public ResultWrapper getHotWords(@RequestParam Integer pageCount) {
        List<Map> hotWords = searchService.getHotWords(pageCount);
        return ResultWrapper.responseSuccess(hotWords);
    }

    @GetMapping("/keyword-history")
    public ResultWrapper getSearchHistory(@RequestParam Long userId,
                                          @RequestParam Integer pageNumber,
                                          @RequestParam Integer pageSize) {
        Page<SearchWord> searchHistory = searchService.getSearchHistory(pageNumber, pageSize, userId);
        return ResultWrapper.responseSuccess(searchHistory);
    }
}
