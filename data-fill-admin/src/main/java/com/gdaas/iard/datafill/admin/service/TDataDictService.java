/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TDataDictEntity;

/**
 * <p>字典表 服务类</p>
 *
 * @author like
 * @since 2019-10-04
 */
public interface TDataDictService extends BaseService<TDataDictEntity> {

    /**
     * 查询最大序号
     * @return
     */
    String findTopSequence(String field,String table);
}
