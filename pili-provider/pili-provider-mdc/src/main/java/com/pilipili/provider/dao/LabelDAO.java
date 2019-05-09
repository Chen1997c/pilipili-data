package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： Label dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　14:51
 */
public interface LabelDAO extends JpaRepository<Label, Long> {

    /**
     * 根据labelName查询
     * @param labelName
     * @return
     */
    Label findByLabelName(String labelName);
}
