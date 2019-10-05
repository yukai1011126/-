/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.gdaas.iard.datafill.wechat.repo.dao.entity.TMyReportEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TMyReportDao;
import com.gdaas.iard.datafill.wechat.service.TMyReportService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p>我的报表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Service
public class TMyReportServiceImpl extends AbstractBaseService<TMyReportDao, TMyReportEntity>
        implements TMyReportService {
}
