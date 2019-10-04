/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service.impl;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TRoleEntity;
import com.gdaas.iard.datafill.admin.repo.dao.mapper.TRoleDao;
import com.gdaas.iard.datafill.admin.service.TRoleService;
import com.gdaas.iard.datafill.admin.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>角色表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TRoleServiceImpl extends AbstractBaseService<TRoleDao, TRoleEntity>
        implements TRoleService {
}
