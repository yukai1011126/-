package com.gdaas.iard.datafill.wechat.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.*;
import com.gdaas.iard.datafill.wechat.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务 管理
 */
@Log4j2
@Api(tags = "ReportController")
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private TMyReportService  targetService;
    @Autowired
    private TTemplateTaskService tTemplateTaskService;
    @Autowired
    private TTaskReleaseService taskReleaseService;


    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TMyReportEntity> request) {
        TMyReportEntity entity = targetService.getById(request.getParam().getId());
        if (entity == null) {
            return BaseResp.fail("尚未查询到此ID");
        }
        return BaseResp.success(entity);
    }
    /**
     * 获取数据列表
     *
     * @author  LIKE
     */
    @ApiOperation("查询任务分页")
    @PostMapping("/queryMyReport")
    public BaseResp queryMyReport(@RequestBody BaseRequest<TMyReportEntity> request) {
        BaseResp baseResp= BaseResp.success();
       try{
           log.info(request);
           Page page = new Page(request.getPage(), request.getRows());
           QueryWrapper<TMyReportEntity> wrapper=new QueryWrapper<>();
           wrapper.lambda().eq(TMyReportEntity::getUserId,request.getParam().getUserId())
           .orderByDesc(TMyReportEntity::getCreateTime);
           targetService.page(page, wrapper);
           baseResp.setData(page);
           return baseResp;
       }catch (Exception e){
           e.printStackTrace();
           log.error(e.getMessage());
           return BaseResp.fail(e.getLocalizedMessage());
       }
    }




    /**
     * 查询我的进度
     *
     * @author Like
     */
    @ApiOperation("查询分页")
    @PostMapping("/queryProcess")
    public BaseResp queryProcess(@RequestBody  BaseRequest<TMyReportEntity> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            JSONObject list= taskReleaseService.queryProcess(request.getParam().getUserId());
            baseResp.setData(list);
            return baseResp;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return BaseResp.fail(e.getLocalizedMessage());
        }
    }


    /**
     * 根据用户查询每个数据填报的审核情况
     *
     * @author Like
     */
    @ApiOperation("根据用户查询每个数据填报的审核情况")
    @PostMapping("/queryProcess2")
    public BaseResp queryProcess2(@RequestBody  BaseRequest<TTaskEntity> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            JSONObject list= taskReleaseService.queryProcess2(request.getParam().getUserId(),request.getParam().getTaskId());
            baseResp.setData(list);
            return baseResp;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return BaseResp.fail(e.getLocalizedMessage());
        }
    }



}
