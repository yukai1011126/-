/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TJobAuditLogEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TJobAuditLogDao;
import com.gdaas.iard.datafill.wechat.service.TJobAuditLogService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-25
 */
@Service
public class TJobAuditLogServiceImpl extends AbstractBaseService<TJobAuditLogDao, TJobAuditLogEntity>
        implements TJobAuditLogService {
}
