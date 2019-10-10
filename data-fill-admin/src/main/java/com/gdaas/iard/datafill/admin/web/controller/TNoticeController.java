/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TNoticeEntity;
import com.gdaas.iard.datafill.admin.service.TNoticeService;
import com.gdaas.iard.datafill.admin.util.SingletonEnum;
import com.gdaas.iard.datafill.common.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>通知消息表 前端控制器</p>
 *
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
    private SimpleDateFormat slmf = SingletonEnum.SIMPLEDATE.getSimpleDate();
    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @GetMapping("/list")
    public BaseResp findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,
                                   @RequestParam(name = "rows", defaultValue = "20") int step,
                                   @RequestParam(name = "sort", defaultValue = "+id") String sort,
                                   @RequestParam(name = "vague", defaultValue = "") String vague,
                                   @RequestParam(name = "status", defaultValue = "") String status) {
        Page page = new Page(pageIndex, step);
        String asc = null;
        // 支持排序
        if(sort.contains("+")){
            sort = sort.replace("+","");
            page.setAsc(sort);
            asc = "asc";
        }else if (sort.contains("-")){
            sort = sort.replace("-","");
            page.setDesc(sort);
            asc = "desc";
        }
        // 支持模糊查询
        if(!StringUtils.isEmpty(vague)){
            log.info("查询参数status:{},sort:{},vague:{},asc:{}",status,sort,vague,asc);
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("vague",vague);
            queryMap.put("status",status);
            queryMap.put("sort",sort);
            queryMap.put("asc",asc);
            targetService.queryVague(page, queryMap);
            log.info("查询结果：{}",page.toString());
        }else{
            if(StringUtils.isEmpty(status)){
                targetService.page(page);
                return BaseResp.success(page);
            }
            QueryWrapper<TNoticeEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("status", status);
            targetService.page(page, wrapper);
        }
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
        List<TNoticeEntity> models = targetService.list(null);
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
    public BaseResp addItem(@RequestBody TNoticeEntity entity) throws ParseException {
        Date time = slmf.parse(slmf.format(new Date()));
        entity.setCreateTime(time);
        entity.setUpdateTime(time);
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
