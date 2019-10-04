/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import com.gdaas.iard.datafill.wechat.service.TRoleService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TRoleEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TRoleDao;
import org.springframework.stereotype.Service;

/**
 * <p>角色表 服务实现类</p>
 *
 * @author like
 * @since 2019-09-30
 */
@Service
public class TRoleServiceImpl extends AbstractBaseService<TRoleDao, TRoleEntity>
        implements TRoleService {
}
