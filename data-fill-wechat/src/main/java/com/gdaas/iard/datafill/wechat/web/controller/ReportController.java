package com.gdaas.iard.datafill.wechat.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TMyReportEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskAuditLogEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.TMyReportService;
import com.gdaas.iard.datafill.wechat.service.TTaskAuditLogService;
import com.gdaas.iard.datafill.wechat.service.TTaskService;
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
     * 查询我的任务数量
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/queryMyReportCount")
    public BaseResp queryMyTaskCount(@RequestBody BaseRequest<TMyReportEntity> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            QueryWrapper<TMyReportEntity> wrapper=new QueryWrapper<>();
            wrapper.lambda().eq(TMyReportEntity::getUserId,request.getParam().getUserId());
            int count= targetService.count(wrapper);
            Map<String,Object>  data=new ConcurrentHashMap<>();
            data.put("count",count);
            baseResp.setData(data);
            return baseResp;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return BaseResp.fail(e.getLocalizedMessage());
        }
    }






}
