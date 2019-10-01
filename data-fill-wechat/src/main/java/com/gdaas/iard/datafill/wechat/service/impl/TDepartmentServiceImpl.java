/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TDepartmentEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TDepartmentDao;
import com.gdaas.iard.datafill.wechat.service.TDepartmentService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-30
 */
@Service
public class TDepartmentServiceImpl extends AbstractBaseService<TDepartmentDao, TDepartmentEntity>
        implements TDepartmentService {
}
