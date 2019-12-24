/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.OpinionEntity;
import com.gdaas.iard.datafill.admin.service.OpinionService;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 前端控制器</p>
 * 反馈表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "OpinionController")
@RestController
@RequestMapping("/OpinionController")
public class OpinionController {
    @Autowired
    private OpinionService targetService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<OpinionEntity> param) {
        LambdaQueryWrapper<OpinionEntity> queryWrapper = new LambdaQueryWrapper<>();
        //任务名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getTaskName()),OpinionEntity::getTaskName,param.getParam().getTaskName());
        //机构名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getOrganizationName()),OpinionEntity::getOrganizationName,param.getParam().getOrganizationName());
        Page page = new Page(param.getPage(), param.getRows());
        targetService.page(page, queryWrapper);
        return BaseResp.success(page);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @PostMapping("/all")
    public BaseResp findAll() {
        LambdaQueryWrapper<OpinionEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<OpinionEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<OpinionEntity> param) {
        Integer id = param.getParam().getOpinionId();
        OpinionEntity entity = targetService.getById(id);
        if (entity == null) {
            return BaseResp.fail("尚未查询到此ID");
        }
        return BaseResp.success(entity);
    }

    /**
     * 添加数据
     *
     * @author jerryniu
     */
    @ApiOperation(value = "添加单条记录", notes = "id自增")
    @PostMapping(value = "/add")
    public BaseResp addItem(@RequestBody OpinionEntity param) {
        boolean isOk = targetService.save(param);
        if (isOk) {
            return BaseResp.success("数据添加成功");
        }
        return BaseResp.fail("数据添加失败");
    }

    /**
     * 更新数据
     *
     * @author jerryniu
     */
    @ApiOperation("更新单条记录")
    @PostMapping(value = "/update")
    public BaseResp updateItem(@RequestBody OpinionEntity param) {
        boolean isOk = targetService.updateById(param);
        if (isOk) {
            return BaseResp.success("数据更改成功");
        }
        return BaseResp.fail("数据更改失败");
    }

    /**
     * 删除数据
     *
     * @author jerryniu
     */
    @ApiOperation("删除记录")
    @PostMapping("/del")
    public BaseResp deleteItems(@RequestBody OpinionEntity param) {
        List<Integer> ids = new ArrayList<>();
        ids.add(param.getOpinionId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }
}
