/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TTemplateEntity;
import com.gdaas.iard.datafill.admin.service.TDataDictService;
import com.gdaas.iard.datafill.admin.service.TTemplateService;
import com.gdaas.iard.datafill.admin.util.MyUtil;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> 前端控制器</p>
 *
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-10-05
 */
@Log4j2
@CrossOrigin
@Api(tags = "TTemplateController")
@RestController
@RequestMapping("/TTemplateController")
public class TTemplateController {
    @Autowired
    private TTemplateService targetService;
    @Autowired
    private TDataDictService targetDictService;
    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<TTemplateEntity> param) {
        Page page = MyUtil.pageDecorate(param);
        String vague = param.getVague();
        boolean flag = StringUtils.isNotEmpty(vague);
        if(flag){
            LambdaQueryWrapper<TTemplateEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.and(x-> x.like(TTemplateEntity::getTemplateName, vague).or()
                    .like(TTemplateEntity::getConfigUser, vague ).or()
                    .like(TTemplateEntity::getMemo, vague));
            log.info("查询参数为：{}",param.getParam().toString());
            targetService.page(page, queryWrapper);
        }else{
            targetService.page(page, null);
        }
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
        List<TTemplateEntity> models = targetService.list();
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TTemplateEntity> baseRequest) {
        TTemplateEntity entity = targetService.getById(baseRequest.getParam().getId());
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
    public BaseResp addItem(@RequestBody TTemplateEntity entity){
        boolean isOk = StringUtils.isEmpty(entity.getId());
        try {
            if(isOk){
                entity.setTemplateNumber(targetDictService.findTopSequence("template_number","t_template"));
            }
            entity = (TTemplateEntity) MyUtil.addOrEditDecorate(entity, isOk);
            isOk = isOk ? targetService.save(entity) : targetService.updateById(entity);
            log.info("保存数据结束：{}，保存结果：{}",entity.toString(),isOk);
        } catch (Exception e) {
            log.info("数据保存异常：{}",e);
        }
        if (isOk) {
            return BaseResp.success(entity);
        }
        return BaseResp.fail("数据添加失败");
    }

    /**
     * 删除数据
     *
     * @author jerryniu
     */
    @ApiOperation("删除记录")
    @PostMapping("/del")
    public BaseResp deleteItems(@RequestBody TTemplateEntity tTemplateEntity) {
        boolean isOk = targetService.removeById(tTemplateEntity.getId());
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }
}
