/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.admin.service.TNoticeService;
import com.gdaas.iard.datafill.admin.util.MyUtil;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.util.IDGenerate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>通知消息表 前端控制器</p>
 * <p>
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-10-05
 */
@Log4j2
@CrossOrigin
@Api(tags = "TNoticeController")
@RestController
@RequestMapping("/TNoticeController")
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
    public BaseResp findListByPage(@RequestBody BaseRequest<TNoticeEntity> baseResp) {
        String vague = baseResp.getVague();
        String status = baseResp.getStatus();
        LambdaQueryWrapper<TNoticeEntity> queryWrapper = new LambdaQueryWrapper();
        Page page = MyUtil.pageDecorate(baseResp);
        // 支持模糊查询
        queryWrapper = StringUtils.isEmpty(status) ? queryWrapper : queryWrapper.eq(TNoticeEntity::getStatus, status);
        queryWrapper = StringUtils.isEmpty(vague) ? queryWrapper : queryWrapper
                .and(x->x.like(TNoticeEntity::getContent, vague).or()
                .like(TNoticeEntity::getSummary, vague).or()
                .like(TNoticeEntity::getTitle, vague).or()
                .like(TNoticeEntity::getUserName, vague));
        targetService.page(page, queryWrapper);
        log.info("查询结果：{}", page.toString());
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
        List<TNoticeEntity> models = targetService.list(null);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TNoticeEntity> baseResp) {
        TNoticeEntity entity = targetService.getById(baseResp.getParam().getId());
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
    public BaseResp addItem(@RequestBody TNoticeEntity entity) {
        boolean isOk = StringUtils.isEmpty(entity.getId());
        try {
            entity = (TNoticeEntity) MyUtil.addOrEditDecorate(entity, isOk);
            isOk = isOk ? targetService.save(entity) : targetService.save(entity);
            log.info("数据：{},保存结果:{}", entity, isOk);
        } catch (ParseException e) {
            log.error("新增异常：{}", e);
        }
        if (isOk) {
            return BaseResp.success("数据添加成功");
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
    public BaseResp deleteItems(List<Long> ids) {
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }
}
