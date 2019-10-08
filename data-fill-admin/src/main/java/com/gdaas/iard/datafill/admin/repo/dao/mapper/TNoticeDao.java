/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TNoticeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p> Mapper 接口</p>
 *
 * @author like
 * @since 2019-10-04
 */
public interface TNoticeDao extends BaseMapper<TNoticeEntity> {
    IPage selectPageForVague (IPage page, @Param("query") Map<String,Object> queryMap);
}
