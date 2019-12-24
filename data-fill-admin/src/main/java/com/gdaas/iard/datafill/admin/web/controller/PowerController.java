/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.admin.service.PowerService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.PowerEntity;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 权限表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "PowerController")
@RestController
@RequestMapping("/PowerController")
public class PowerController {
    @Autowired
    private PowerService targetService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<PowerEntity> param) {
        LambdaQueryWrapper<PowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(param.getVague()),PowerEntity::getPowerName,param.getVague());
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
        LambdaQueryWrapper<PowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<PowerEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<PowerEntity> param) {
        Integer id = param.getParam().getPowerId();
        PowerEntity entity = targetService.getById(id);
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
    public BaseResp addItem(@RequestBody PowerEntity param) {
//        PowerEntity entity = param.getParam();getParam
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
    public BaseResp updateItem(@RequestBody PowerEntity param) {
//        PowerEntity entity = param.getParam();
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
    public BaseResp deleteItems(@RequestBody PowerEntity params) {
        List<Integer> ids = new ArrayList<>();
        ids.add(params.getPowerId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }

    @ApiOperation("获取权限")
    @PostMapping("/getInfo")
    public BaseResp login(@RequestBody(required=false) String role){
        String newRole = role.replace("=","");

        //查询权限菜单
        LambdaQueryWrapper<PowerEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(newRole),PowerEntity::getRole,newRole);
        List<PowerEntity> powerEntityList = targetService.list(queryWrapper);
        if(!powerEntityList.isEmpty()){
            System.out.println(powerEntityList);
            return BaseResp.success(powerEntityList);
        }else{
            return BaseResp.fail("查询权限失败");
        }
    }
}
