/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskReleaseEntity;

/**
 * <p>任务发布表 服务类</p>
 *
 * @author like
 * @since 2019-10-08
 */
public interface TTaskReleaseService extends BaseService<TTaskReleaseEntity> {
    /**
     *  根据用户查询其所有的用户任务
     * @param userId
     * @return
     */
    JSONObject queryProcess(String userId);

    /**
     *  根据用户查询其所有的用户任务
     * @param userId
     * @return
     */
    JSONObject queryProcess2(String userId,String taskId);
}
