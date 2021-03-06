/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TNoticeEntity;

import java.util.Map;

/**
 * <p> 服务类</p>
 *
 * @author like
 * @since 2019-10-04
 */
public interface TNoticeService extends BaseService<TNoticeEntity> {
    IPage queryVague(IPage page, Map<String,Object> queryMap);
}
