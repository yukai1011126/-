/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.repo.dao.entity.OrganizationEntity;
import com.gdaas.iard.datafill.admin.repo.dao.entity.StatisticalEntity;
import com.gdaas.iard.datafill.admin.service.OrganizationService;
import com.gdaas.iard.datafill.admin.service.StatisticalService;
import com.gdaas.iard.datafill.admin.service.TaskService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TaskEntity;
import com.gdaas.iard.datafill.admin.util.ExcelUtil;
import com.gdaas.iard.datafill.admin.util.ImportExcel;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> 前端控制器</p>
 * 任务表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "TaskController")
@RestController
@RequestMapping("/TaskController")
public class TaskController {
    @Autowired
    private TaskService targetService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private StatisticalService statisticalService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<TaskEntity> param) {
        LambdaQueryWrapper<TaskEntity> queryWrapper = new LambdaQueryWrapper<>();
        //任务名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getTaskName()),TaskEntity::getTaskName,param.getParam().getTaskName());
        //任务内容搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getTaskText()),TaskEntity::getTaskText,param.getParam().getTaskText());
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
        LambdaQueryWrapper<TaskEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<TaskEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TaskEntity> param) {
        Integer id = param.getParam().getTaskId();
        TaskEntity entity = targetService.getById(id);
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
    public BaseResp addItem(@RequestBody TaskEntity param) {
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
    public BaseResp updateItem(@RequestBody TaskEntity param) {
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
    public BaseResp deleteItems(@RequestBody TaskEntity param) {
        List<Integer> ids = new ArrayList<>();
        ids.add(param.getTaskId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }

    /**
     * 任务模板导出
     * @param request
     * @param response
     */
    @RequestMapping("/taskmbexcel.do")
    @ResponseBody
    public void mbftbExcelExport(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtil.mbtaskExcel(response);
    }

    /**
     * 任务导入
     * @param file
     * @return
     */
    @RequestMapping(value="/taskdaoru")
    @ResponseBody
    public BaseResp daoru(@RequestParam("file") MultipartFile file){
        try {
            LambdaQueryWrapper<TaskEntity> queryWrapper;
            ImportExcel ip = new ImportExcel();
            Map<String,Object> map = ip.imputExcelTask(file);
            if(map.get("error") == null){
                List<Map<String,Object>> list = (List<Map<String,Object>>) map.get("ok");
                TaskEntity task;
                Map<String,String> release;
                for (Map<String,Object> map1 : list) {
                    task = (TaskEntity)map1.get("Task");
                    targetService.save(task);
                    queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(TaskEntity::getTaskName,task.getTaskName());
                    //任务数据
                    List<TaskEntity> taskmodels = targetService.list(queryWrapper);
                    release = (Map<String,String>)map1.get("release");
                    LambdaQueryWrapper<OrganizationEntity> queryWrapper1;
                    if("1".equals(release.get("type"))) {
                        String[] num = release.get("number").split("\\|");//机构编号 逗号分隔
                        queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.in(OrganizationEntity::getOrganizationCode,num);
                        List<OrganizationEntity> partModels = organizationService.list(queryWrapper1);
                        addStatistical(taskmodels,partModels);
                    }
                    //支行(不包含自贸区)
                    if("1".equals(release.get("subbranch"))) {
                        queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.eq(OrganizationEntity::getOrganizationLevel,"2");
                        queryWrapper1.ne(OrganizationEntity::getCommunitie,"2");
                        List<OrganizationEntity> subModels = organizationService.list(queryWrapper1);
                        addStatistical(taskmodels,subModels);
                    }
                    //自贸区
                    if("1".equals(release.get("fta"))) {
                        queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.eq(OrganizationEntity::getOrganizationLevel,"2");
                        queryWrapper1.eq(OrganizationEntity::getCommunitie,"2");
                        List<OrganizationEntity> subModels = organizationService.list(queryWrapper1);
                        addStatistical(taskmodels,subModels);
                    }
                    //网点(不包含社区)
                    if("1".equals(release.get("dot"))) {
                        queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.eq(OrganizationEntity::getOrganizationLevel,"3");
                        queryWrapper1.eq(OrganizationEntity::getCommunitie,"0");
                        List<OrganizationEntity> subModels = organizationService.list(queryWrapper1);
                        addStatistical(taskmodels,subModels);
                    }
                    //社区
                    if("1".equals(release.get("community"))) {
                        queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.eq(OrganizationEntity::getOrganizationLevel,"3");
                        queryWrapper1.eq(OrganizationEntity::getCommunitie,"1");
                        List<OrganizationEntity> subModels = organizationService.list(queryWrapper1);
                        addStatistical(taskmodels,subModels);
                    }

                }
                return BaseResp.success("导入成功!");
            }else{
                String a = (String) map.get("error");
                return BaseResp.fail("导入失败!"+a.substring(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResp.fail("导入失败!"+e);
        }
    }
    //添加到统计表,任务下发
    private void addStatistical(List<TaskEntity> taskEntityList,List<OrganizationEntity> organizationEntityList) {
        StatisticalEntity statisticalEntity;
        for(OrganizationEntity organization : organizationEntityList){
            statisticalEntity = new StatisticalEntity();
            statisticalEntity.setTaskId(taskEntityList.get(0).getTaskId());
            statisticalEntity.setTaskName(taskEntityList.get(0).getTaskName());
            statisticalEntity.setOrganizationId(organization.getOrganizationId());
            statisticalEntity.setOrganizationName(organization.getOrganizationName());
            statisticalEntity.setOrganizationLevel(organization.getOrganizationLevel());
            statisticalEntity.setState("0");
            statisticalEntity.setTaskDate(taskEntityList.get(0).getTaskDate());
            statisticalService.save(statisticalEntity);
        }
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询任务名")
    @PostMapping("/datataskname")
    public BaseResp findDateTaskName() throws ParseException {
        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String string  = sdf.format(new Date());
        Date date = sdf.parse(string);//当天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date2  = calendar.getTime();//第二天日期
        LambdaQueryWrapper<TaskEntity> queryWrapper = new LambdaQueryWrapper<>();
        //所属时间搜索
        queryWrapper.between(TaskEntity::getTaskDate,date,date2);
        List<TaskEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询任务名")
    @PostMapping("/alltaskname")
    public BaseResp findAllTaskName() throws ParseException {
        LambdaQueryWrapper<TaskEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<TaskEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

}
