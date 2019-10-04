/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskAuditLogEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTaskAuditLogDao;
import com.gdaas.iard.datafill.wechat.service.TTaskAuditLogService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>任务审核记录表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TTaskAuditLogServiceImpl extends AbstractBaseService<TTaskAuditLogDao, TTaskAuditLogEntity>
        implements TTaskAuditLogService {
}
