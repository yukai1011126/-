/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TBaseConfigDao;
import com.gdaas.iard.datafill.wechat.service.TBaseConfigService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TBaseConfigEntity;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>系统配置表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-01
 */
@Service
public class TBaseConfigServiceImpl extends AbstractBaseService<TBaseConfigDao, TBaseConfigEntity>
        implements TBaseConfigService {
}
