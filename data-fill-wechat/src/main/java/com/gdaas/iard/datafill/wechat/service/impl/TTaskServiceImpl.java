/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTaskDao;
import com.gdaas.iard.datafill.wechat.service.TTaskService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>我的待处理工作任务表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TTaskServiceImpl extends AbstractBaseService<TTaskDao, TTaskEntity>
        implements TTaskService {
}
