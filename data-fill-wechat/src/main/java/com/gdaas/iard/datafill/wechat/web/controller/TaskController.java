package com.gdaas.iard.datafill.wechat.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskAuditLogEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTemplateDataValueEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.TTaskAuditLogService;
import com.gdaas.iard.datafill.wechat.service.TTaskService;
import com.gdaas.iard.datafill.wechat.service.TTemplateDataValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务 管理
 */
@Log4j2
@Api(tags = "TaskController")
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TTaskService targetService;

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
    @PostMapping("/queryMyTask")
    public BaseResp queryMyTask(@RequestBody BaseRequest<TTaskEntity> request) {
        BaseResp baseResp= BaseResp.success();
       try{
           log.info(request);
           Page page = new Page(request.getPage(), request.getRows());
           QueryWrapper<TTaskEntity> wrapper=new QueryWrapper<>();
           wrapper.lambda().eq(TTaskEntity::getAuditUserId,request.getParam().getAuditUserId())
           .orderByDesc(TTaskEntity::getCreateTime);
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
     * 获取数据列表
     *
     * @author  LIKE
     */
    @ApiOperation("查询已经处理的任务分页")
    @PostMapping("/queryMyHistoryTask")
    public BaseResp queryMyHistoryTask(@RequestBody BaseRequest<TTaskAuditLogEntity> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            log.info(request);

            IPage<TTaskAuditLogEntity> page = new Page(request.getPage(), request.getRows());
            QueryWrapper<TTaskAuditLogEntity> wrapper=new QueryWrapper<>();
            wrapper.lambda().eq(TTaskAuditLogEntity::getAuditUserId,request.getParam().getAuditUserId())
                            .orderByDesc(TTaskAuditLogEntity::getCreateTime);
            page=  tTaskAuditLogService.page(page, wrapper);
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
    @PostMapping("/queryMyTaskCount")
    public BaseResp queryMyTaskCount(@RequestBody BaseRequest<TTaskEntity> request) {
        BaseResp baseResp= BaseResp.success();
        try{
            QueryWrapper<TTaskEntity> wrapper=new QueryWrapper<>();
            wrapper.lambda().eq(TTaskEntity::getAuditUserId,request.getParam().getAuditUserId());
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


    /**
     * 查询我的任务数量
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/queryTaskDataValue")
    public BaseResp queryTaskDataValue(@RequestBody BaseRequest<TTemplateDataValueEntity> request) {
        BaseResp baseResp= BaseResp.success();

        QueryWrapper<TTemplateDataValueEntity> wrapper=new QueryWrapper<>();
        wrapper.lambda().orderByDesc(TTemplateDataValueEntity::getSortNumber)
                .eq(TTemplateDataValueEntity::getTaskId,request.getParam().getTaskId());

        List<TTemplateDataValueEntity> list=dataValueService.list(wrapper);
        Map<String,Object>  data=new ConcurrentHashMap<>();
        data.put("list",list);
        data.put("size",list.size());
        data.put("taskName",list.get(0).getTaskName());
        baseResp.setData(data);
        return baseResp;


    }

}
