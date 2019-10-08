/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.admin.repo.dao.mapper.TNoticeDao;
import com.gdaas.iard.datafill.admin.service.TNoticeService;
import com.gdaas.iard.datafill.admin.service.AbstractBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TNoticeServiceImpl extends AbstractBaseService<TNoticeDao, TNoticeEntity>
        implements TNoticeService {

    public IPage queryVague(IPage page, Map<String,Object> queryMap) {
        return this.baseMapper.selectPageForVague(page, queryMap);
    }
}
