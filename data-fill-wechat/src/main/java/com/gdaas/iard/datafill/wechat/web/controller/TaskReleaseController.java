package com.gdaas.iard.datafill.wechat.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskAuditLogEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskReleaseEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateDataValueEntity;
import com.gdaas.iard.datafill.wechat.service.TTaskAuditLogService;
import com.gdaas.iard.datafill.wechat.service.TTaskReleaseService;
import com.gdaas.iard.datafill.wechat.service.TTaskService;
import com.gdaas.iard.datafill.wechat.service.TTemplateDataValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务 管理
 */
@Log4j2
@Api(tags = "TaskController")
@RestController
@RequestMapping("/taskRelease")
public class TaskReleaseController {
    @Autowired
    private TTaskReleaseService targetService;

    @Autowired
    private TTaskAuditLogService tTaskAuditLogService;

    @Autowired
    private TTemplateDataValueService dataValueService;


    /**
     * 获取数据列表
     *
     * @author  LIKE
     */
    @ApiOperation("查询任务分页")
    @PostMapping("/queryTaskRelease")
    public BaseResp queryTaskRelease(@RequestBody BaseRequest<TTaskReleaseEntity> request) {
        BaseResp baseResp= BaseResp.success();
       try{
           log.info(request);
//           Page page = new Page(request.getPage(), request.getRows());
           QueryWrapper<TTaskReleaseEntity> wrapper=new QueryWrapper<>();
           wrapper.lambda().eq(TTaskReleaseEntity::getTaskId,request.getParam().getTaskId())
           .orderByDesc(TTaskReleaseEntity::getCreateTime);
           List<TTaskReleaseEntity> list=targetService.list( wrapper);
           baseResp.setData(list);
           return baseResp;
       }catch (Exception e){
           e.printStackTrace();
           log.error(e.getMessage());
           return BaseResp.fail(e.getLocalizedMessage());
       }
    }




    /**
     * 获取数据列表
     *
     * @author  LIKE
     */
    @ApiOperation("查询任务分页")
    @PostMapping("/queryTaskForward")
    public BaseResp queryTaskForward(@RequestBody BaseRequest<String> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            log.info(request);
//           Page page = new Page(request.getPage(), request.getRows());
            QueryWrapper<TTaskReleaseEntity> wrapper=new QueryWrapper<>();
            wrapper.lambda().eq(TTaskReleaseEntity::getTaskId,request.getParam())
                    .orderByDesc(TTaskReleaseEntity::getCreateTime);
            List<TTaskReleaseEntity> list=targetService.list( wrapper);
            baseResp.setData(list);
            return baseResp;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return BaseResp.fail(e.getLocalizedMessage());
        }
    }



}
