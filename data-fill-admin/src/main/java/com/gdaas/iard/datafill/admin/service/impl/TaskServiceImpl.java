/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.service.impl;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TaskEntity;
import com.gdaas.iard.datafill.admin.repo.dao.mapper.TaskDao;
import com.gdaas.iard.datafill.admin.service.TaskService;
import com.gdaas.iard.datafill.admin.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-11-12
 */
@Service
public class TaskServiceImpl extends AbstractBaseService<TaskDao, TaskEntity>
        implements TaskService {
}
