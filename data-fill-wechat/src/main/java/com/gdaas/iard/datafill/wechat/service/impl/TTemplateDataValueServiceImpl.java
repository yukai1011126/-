/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateDataValueEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTemplateDataValueDao;
import com.gdaas.iard.datafill.wechat.service.TTemplateDataValueService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>模板任务数据项值 服务实现类</p>
 *
 * @author like
 * @since 2019-10-05
 */
@Service
public class TTemplateDataValueServiceImpl extends AbstractBaseService<TTemplateDataValueDao, TTemplateDataValueEntity>
        implements TTemplateDataValueService {
}
