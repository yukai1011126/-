/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service.impl;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TDataDictEntity;
import com.gdaas.iard.datafill.admin.repo.dao.mapper.TDataDictDao;
import com.gdaas.iard.datafill.admin.service.TDataDictService;
import com.gdaas.iard.datafill.admin.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>字典表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TDataDictServiceImpl extends AbstractBaseService<TDataDictDao, TDataDictEntity>
        implements TDataDictService {
}