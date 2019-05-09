package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： Channel dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface ChannelDAO extends JpaRepository<Channel, Long> {
}
