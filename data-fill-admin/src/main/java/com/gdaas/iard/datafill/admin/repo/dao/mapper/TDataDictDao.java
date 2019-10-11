/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao.mapper;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TDataDictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>字典表 Mapper 接口</p>
 *
 * @author like
 * @since 2019-10-04
 */
public interface TDataDictDao extends BaseMapper<TDataDictEntity> {

    String findTopSequence( @Param(value="field") String field, @Param(value="table")String table);
}
