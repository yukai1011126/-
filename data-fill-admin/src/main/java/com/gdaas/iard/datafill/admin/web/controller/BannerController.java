/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.service.BannerService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.BannerEntity;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.cloud.openfeign.encoding.BaseRequestInterceptor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 前端控制器</p>
 * 公告表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "BannerController")
@RestController
@RequestMapping("/BannerController")
public class BannerController {
    @Autowired
    private BannerService targetService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<BannerEntity> param){
        LambdaQueryWrapper<BannerEntity> queryWrapper = new LambdaQueryWrapper<BannerEntity>();
        queryWrapper.like(StringUtils.isNotEmpty(param.getVague()),BannerEntity::getTitle,param.getVague());
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
        LambdaQueryWrapper<BannerEntity> queryWrapper = new LambdaQueryWrapper<BannerEntity>();
        List<BannerEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<BannerEntity> param) {
        Integer id = param.getParam().getBannerId();
        BannerEntity entity = targetService.getById(id);
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
    public BaseResp addItem(@RequestBody BannerEntity param) {
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
    public BaseResp updateItem(@RequestBody BannerEntity param) {
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
    public BaseResp deleteItems(@RequestBody BannerEntity param) {
        List<Integer> ids = new ArrayList<>();
        ids.add(param.getBannerId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }
}
