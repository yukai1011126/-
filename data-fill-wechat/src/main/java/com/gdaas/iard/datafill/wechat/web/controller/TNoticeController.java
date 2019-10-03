/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdaas.iard.datafill.wechat.service.TNoticeService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.wechat.web.common.BaseRequest;
import com.gdaas.iard.datafill.wechat.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@Api(tags = "TNoticeController")
@RestController("TNoticeController")
@RequestMapping("/notice")
public class TNoticeController {
    @Autowired
    private TNoticeService targetService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<HashMap> param) {
        Page page = new Page(param.getPage(), param.getRows());
        QueryWrapper<TNoticeEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(TNoticeEntity::getNoticeDate);
        targetService.page(page, queryWrapper);
        return BaseResp.success(page);
    }


    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping ("/find")
    public BaseResp find(@RequestBody BaseRequest<TNoticeEntity> param) {
        TNoticeEntity entity = targetService.getById(param.getParam().getId());
        if (entity == null) {
            return BaseResp.fail("尚未查询到此ID");
        }
        return BaseResp.success(entity);
    }


}
