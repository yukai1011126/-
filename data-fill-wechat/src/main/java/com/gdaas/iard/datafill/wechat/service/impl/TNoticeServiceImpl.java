/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import com.gdaas.iard.datafill.wechat.service.TNoticeService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TNoticeDao;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-24
 */
@Service
public class TNoticeServiceImpl extends AbstractBaseService<TNoticeDao, TNoticeEntity>
        implements TNoticeService {
}
