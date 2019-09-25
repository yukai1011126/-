/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.web.controller;

import com.gdaas.iard.datafill.wechat.service.TNoticeService;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.wechat.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @since 2019-09-24
 */
//@Api(tags = "TNoticeController")
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
    @GetMapping("/list")
    public BaseResp findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,
            @RequestParam(name = "rows", defaultValue = "20") int step) {
        Page page = new Page(pageIndex, step);
        targetService.page(page, null);
        System.out.println(page.getRecords().get(0));
        return BaseResp.success(page);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @GetMapping("/all")
    @ResponseBody
    public BaseResp findAll() {
        List<TNoticeEntity> models = targetService.list(null);
        System.out.println(models.get(0).getContent());
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
        TNoticeEntity entity = targetService.getById(id);
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
        boolean isOk = targetService.save(entity);
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
    @PutMapping(value = "/update")
    public BaseResp updateItem(@RequestBody TNoticeEntity entity) {
        boolean isOk = targetService.updateById(entity);
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
    @DeleteMapping("/del")
    public BaseResp deleteItems(List<Long> ids) {
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }
}