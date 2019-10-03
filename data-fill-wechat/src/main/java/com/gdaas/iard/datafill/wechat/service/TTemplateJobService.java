/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateJobEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;

/**
 * <p> 服务类</p>
 *
 * @author like
 * @since 2019-09-24
 */
public interface TTemplateJobService extends BaseService<TTemplateJobEntity> {


    /**
     *  查询用户的任务数量
     */
    JSONObject findUserJobCounts(TUserEntity entity );

}
