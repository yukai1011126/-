/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import com.gdaas.iard.datafill.wechat.service.TTemplateJobService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateJobEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTemplateJobDao;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-24
 */
@Service
public class TTemplateJobServiceImpl extends AbstractBaseService<TTemplateJobDao, TTemplateJobEntity>
        implements TTemplateJobService {

//    根据用户ID查询用户任务数量
    @Override
    public JSONObject findUserJobCounts(TUserEntity entity) {



        return null;
    }
}
