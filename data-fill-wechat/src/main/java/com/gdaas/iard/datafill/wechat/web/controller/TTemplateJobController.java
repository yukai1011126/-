/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.web.controller;

import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateJobEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.TTemplateJobService;
import com.gdaas.iard.datafill.wechat.service.TUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p> 前端控制器</p>
 *
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-09-24
 */
@Api(tags = "TTemplateJobController")
@RestController("TTemplateJobController")
@RequestMapping("/job")
public class TTemplateJobController {
    @Autowired
    private TTemplateJobService targetService;

    @Autowired
    private TUserService userService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @GetMapping("/list")
    public BaseResp findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,
                                   @RequestParam(name = "rows", defaultValue = "20") int step) {
        Page page = new Page(pageIndex, step);
        targetService.page(page, null);
        return BaseResp.success(page);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @GetMapping("/all")
    public BaseResp findAll() {
        List<TTemplateJobEntity> models = targetService.list(null);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @GetMapping("/find")
    public BaseResp find(Long id) {
        TTemplateJobEntity entity = targetService.getById(id);
        if (entity == null) {
            return BaseResp.fail("尚未查询到此ID");
        }
        return BaseResp.success(entity);
    }

    /**
     * 根据用户ID和用户查询我的任务（DO 填报任务，audit 审核任务，history 历史任务）
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @GetMapping("/findMyJobs")
    public BaseResp findMyJobs(@RequestBody BaseRequest<TUserEntity> user ) {
        BaseResp baseResp=  BaseResp.success("asdf");

        return BaseResp.success("asdf");
    }





}
