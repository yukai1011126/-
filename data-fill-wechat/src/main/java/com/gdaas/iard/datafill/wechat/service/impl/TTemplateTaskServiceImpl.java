/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateTaskEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTemplateTaskDao;
import com.gdaas.iard.datafill.wechat.service.TTemplateTaskService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>模板任务 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TTemplateTaskServiceImpl extends AbstractBaseService<TTemplateTaskDao, TTemplateTaskEntity>
        implements TTemplateTaskService {
}
