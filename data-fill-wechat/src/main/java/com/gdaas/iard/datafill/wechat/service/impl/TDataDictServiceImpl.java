/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import com.gdaas.iard.datafill.wechat.service.TDataDictService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TDataDictEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TDataDictDao;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-30
 */
@Service
public class TDataDictServiceImpl extends AbstractBaseService<TDataDictDao, TDataDictEntity>
        implements TDataDictService {
}
