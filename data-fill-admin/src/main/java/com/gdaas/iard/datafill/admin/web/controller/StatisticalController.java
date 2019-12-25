/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.repo.dao.entity.OrganizationEntity;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TaskEntity;
import com.gdaas.iard.datafill.admin.service.OrganizationService;
import com.gdaas.iard.datafill.admin.service.StatisticalService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.StatisticalEntity;
import com.gdaas.iard.datafill.admin.service.TaskService;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> 前端控制器</p>
 * 统计表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "StatisticalController")
@RestController
@RequestMapping("/StatisticalController")
public class StatisticalController {
    @Autowired
    private StatisticalService targetService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<StatisticalEntity> param) {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        //任务名称搜索
        queryWrapper.eq(StringUtils.isNotEmpty(param.getParam().getTaskName()),StatisticalEntity::getTaskName,param.getParam().getTaskName());
        //机构名搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getOrganizationName()),StatisticalEntity::getOrganizationName,param.getParam().getOrganizationName());
        //机构等级搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getOrganizationLevel()),StatisticalEntity::getOrganizationLevel,param.getParam().getOrganizationLevel());
        Page page = new Page(param.getPage(), param.getRows());
        targetService.page(page, queryWrapper);
        for(StatisticalEntity statistical:(List<StatisticalEntity>)page.getRecords()){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(page);
    }

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/plist")
    public BaseResp findpListByPage(@RequestBody BaseRequest<StatisticalEntity> param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper1;
        LambdaQueryWrapper<StatisticalEntity> queryWrapper;
        queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(OrganizationEntity::getOrganizationLevel,"1","2");
        List<OrganizationEntity> models = organizationService.list(queryWrapper1);
        List<StatisticalEntity> lists = new ArrayList<>();
        for(OrganizationEntity organization: models){
            int denominator = 0;//分母
            int numerator = 0;//分子
            queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(OrganizationEntity::getPorganizationId,organization.getOrganizationId());
            List<OrganizationEntity> models1 = organizationService.list(queryWrapper1);
            //取到支行下的网点
            List<Integer> organizationList = new ArrayList<>();
            organizationList.add(organization.getOrganizationId());
            for(OrganizationEntity organization1 : models1){
                organizationList.add(organization1.getOrganizationId());
            }
            //查询支行网点下是否有任务
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(StatisticalEntity::getOrganizationId,organizationList);
            //任务名称搜索
            queryWrapper.eq(StringUtils.isNotEmpty(param.getParam().getTaskName()),StatisticalEntity::getTaskName,param.getParam().getTaskName());
            List<StatisticalEntity> statisticalModels = targetService.list(queryWrapper);
            for(StatisticalEntity statistical : statisticalModels){
                denominator++;
                if("1".equals(statistical.getState())){
                    numerator++;
                }
            }
            if(denominator>0){
                StatisticalEntity statistical = new StatisticalEntity();
                statistical.setStatisticalId(statisticalModels.get(0).getStatisticalId());
                statistical.setOrganizationId(organization.getOrganizationId());
                statistical.setOrganizationName(organization.getOrganizationName());
                statistical.setOrganizationLevel(organization.getOrganizationLevel());
                statistical.setTaskId(statisticalModels.get(0).getTaskId());
                statistical.setTaskName(statisticalModels.get(0).getTaskName());
                statistical.setTaskDate(statisticalModels.get(0).getTaskDate());
                statistical.setState(numerator+"/"+denominator);
                statistical.setTaskgroupId("");
                statistical.setCreatetime(statisticalModels.get(0).getCreatetime());
                statistical.setUpdatetime(statisticalModels.get(0).getUpdatetime());
                lists.add(statistical);
            }
        }
        //手动分页
        Page page = new Page(param.getPage(), param.getRows());
        page.setRecords(lists);
        page.setTotal(lists.size());
        page.setSize(20);
        page.setSearchCount(true);
        long pages = page.getTotal() / page.getSize();
        if (page.getTotal() % page.getSize() != 0L) {
            ++pages;
        }
        page.setPages(pages);
        page.setCurrent(param.getPage());

        return BaseResp.success(page);
    }

    /**
     * 获取全部pid相同的数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @PostMapping("/pidtask")
    public BaseResp findPidTask(@RequestBody StatisticalEntity param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        //查询相同 pid or id的数据
        queryWrapper.eq(OrganizationEntity::getPorganizationId,param.getOrganizationId()).or().eq(OrganizationEntity::getOrganizationId,param.getOrganizationId());
        List<OrganizationEntity> models = organizationService.list(queryWrapper);
        LambdaQueryWrapper<StatisticalEntity> queryWrapper1;
        List<StatisticalEntity> lists = new ArrayList<>();
        for(OrganizationEntity organization:models){
            queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(StatisticalEntity::getOrganizationId,organization.getOrganizationId());
            queryWrapper1.eq(StatisticalEntity::getTaskName,param.getTaskName());
            List<StatisticalEntity> statisticalModels = targetService.list(queryWrapper1);
            if(statisticalModels.size()>0){
                statisticalModels.get(0).setStatisticalId(statisticalModels.get(0).getStatisticalId()+2014);
                switch (statisticalModels.get(0).getState()){
                    case "0":
                        statisticalModels.get(0).setState("未反馈");
                        break;
                    case "1":
                        statisticalModels.get(0).setState("完成");
                        break;
                    case "2":
                        statisticalModels.get(0).setState("未完成");
                        break;
                }
                lists.add(statisticalModels.get(0));
            }
        }
        return BaseResp.success(lists);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @PostMapping("/all")
    public BaseResp findAll() {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<StatisticalEntity> models = targetService.list(queryWrapper);
        for(StatisticalEntity statistical:models){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<StatisticalEntity> param) {
        Integer id = param.getParam().getStatisticalId();
        StatisticalEntity entity = targetService.getById(id);
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
    public BaseResp addItem(@RequestBody StatisticalEntity param) {
        if(param.getOrganizationLevel()!="" || param.getOrganizationLevel() != null) {
            switch (param.getOrganizationLevel()) {
                case "分行":
                    param.setOrganizationLevel("1");
                    break;
                case "支行":
                    param.setOrganizationLevel("2");
                    break;
                case "网点":
                    param.setOrganizationLevel("3");
                    break;
            }
        }
        if(param.getState()!="" || param.getState() != null) {
            switch (param.getState()) {
                case "未反馈":
                    param.setState("0");
                    break;
                case "完成":
                    param.setState("1");
                    break;
                case "未完成":
                    param.setState("2");
                    break;
            }
        }
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
    public BaseResp updateItem(@RequestBody StatisticalEntity param) {
        if(param.getOrganizationLevel()!="" || param.getOrganizationLevel() != null) {
            switch (param.getOrganizationLevel()) {
                case "分行":
                    param.setOrganizationLevel("1");
                    break;
                case "支行":
                    param.setOrganizationLevel("2");
                    break;
                case "网点":
                    param.setOrganizationLevel("3");
                    break;
            }
        }
        if(param.getState()!="" || param.getState() != null) {
            switch (param.getState()) {
                case "未反馈":
                    param.setState("0");
                    break;
                case "完成":
                    param.setState("1");
                    break;
                case "未完成":
                    param.setState("2");
                    break;
            }
        }
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
    public BaseResp deleteItems(@RequestBody StatisticalEntity param) {
        List<Integer> ids = new ArrayList<>();
        ids.add(param.getStatisticalId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询任务分配的机构")
    @PostMapping("/taskOrOrg")
    public BaseResp findtaskOrOrg(@RequestBody TaskEntity param) {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(param.getTaskName()),StatisticalEntity::getTaskName,param.getTaskName());
        List<StatisticalEntity> models = targetService.list(queryWrapper);
        for(StatisticalEntity statistical:models){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询机构组下的任务")
    @PostMapping("/TaskOrg")
    public BaseResp findTaskOrg(@RequestBody List<OrganizationEntity> organizations) {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<String> newNames = new ArrayList<>();
        for(OrganizationEntity organization:organizations){
            String newName = organization.getOrganizationName().replace("=","");
            newNames.add(newName);
        }
        queryWrapper.in(StatisticalEntity::getOrganizationName,newNames);
        List<StatisticalEntity> models = targetService.list(queryWrapper);
        for(StatisticalEntity statistical:models){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询任务分配的机构")
    @PostMapping("/taskState")
    public BaseResp findtaskState(@RequestBody List<List<Object>> objects) {
        System.out.println(objects);
        System.out.println(objects.get(0).get(0));
        System.out.println(objects.get(1));
        System.out.println(objects.get(1).get(0));
        for(int i = 1;i<objects.get(1).size();i++){
            String labelstate = objects.get(1).get(i).toString();
            String[] strs = labelstate.split(",");
            String gstate = strs[1].replace("property=","");
            String state = gstate.replace("}","");
            System.out.println("1"+state.trim()+"---");
        }
        LambdaQueryWrapper<StatisticalEntity> queryWrapper2;
        List<JSONObject> listJson = new ArrayList<>();
        LambdaQueryWrapper<StatisticalEntity> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(StatisticalEntity::getTaskName,objects.get(0));
        List<StatisticalEntity> models1 = targetService.list(queryWrapper1);
        List<StatisticalEntity> models2;
        JSONObject json;

        for(Object param: objects.get(0)){
            json = new JSONObject();
            json.put("TaskName",param);
            for(int i = 1;i<objects.get(1).size();i++){
                String labelstate = objects.get(1).get(i).toString();
                String[] strs = labelstate.split(",");
                String gstate = strs[1].replace("property=","");
                String state = gstate.replace("}","").trim();
                json.put(state,"---");
            }
//            for(StatisticalEntity statistical1:models1){
//                json.put("state"+statistical1.getOrganizationId(),"---");
//            }
            queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(StatisticalEntity::getTaskName,param);
            models2 = targetService.list(queryWrapper2);
            for(StatisticalEntity statistical2:models2){
                if (statistical2.getState() != "" || statistical2.getState() != null) {
                    switch (statistical2.getState()) {
                        case "0":
                            statistical2.setState("未反馈");
                            break;
                        case "1":
                            statistical2.setState("完成");
                            break;
                        case "2":
                            statistical2.setState("未完成");
                            break;
                        default:
                            statistical2.setState("---");
                            break;
                    }
                }
                json.put("state"+statistical2.getOrganizationId(),statistical2.getState());
            }
            listJson.add(json);
        }

        return BaseResp.success(listJson);
    }

    /**
     * 添加数据
     *
     * @author jerryniu
     */
    @ApiOperation(value = "添加记录", notes = "id自增")
    @PostMapping(value = "/Alladd")
    public BaseResp alladdItem(@RequestBody List<StatisticalEntity> params) {
        boolean isOk = false;
        for(StatisticalEntity param:params) {
            if (param.getOrganizationLevel() != "" || param.getOrganizationLevel() != null) {
                switch (param.getOrganizationLevel()) {
                    case "分行":
                        param.setOrganizationLevel("1");
                        break;
                    case "支行":
                        param.setOrganizationLevel("2");
                        break;
                    case "网点":
                        param.setOrganizationLevel("3");
                        break;
                }
            }
            if (param.getState() != "" || param.getState() != null) {
                switch (param.getState()) {
                    case "未反馈":
                        param.setState("0");
                        break;
                    case "完成":
                        param.setState("1");
                        break;
                    case "未完成":
                        param.setState("2");
                        break;
                }
            }
            isOk = targetService.save(param);
        }
        if (isOk) {
            return BaseResp.success("数据添加成功");
        }
        return BaseResp.fail("数据添加失败");
    }

    /**
     * 获取全部数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询机构下所有任务")
    @PostMapping("/OrgAllTask")
    public BaseResp findOrgAllTask(@RequestBody(required=false) String OrganizationName) {
        String newOrganizationName = OrganizationName.replace("=","");

        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(newOrganizationName),StatisticalEntity::getOrganizationName,newOrganizationName);
        List<StatisticalEntity> models = targetService.list(queryWrapper);
        for(StatisticalEntity statistical:models){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/allList")
    public BaseResp findAllListByPage(@RequestBody BaseRequest<StatisticalEntity> param) {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        //机构名搜索
        List<String> newNames = new ArrayList<>();
        newNames.add(param.getParam().getOrganizationName());
        List<OrganizationEntity> models = new ArrayList<>();
        if(StringUtils.isNotEmpty(param.getParam().getOrganizationName())){
            LambdaQueryWrapper<OrganizationEntity> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(OrganizationEntity::getPorganizationName,param.getParam().getOrganizationName());
            models = organizationService.list(queryWrapper1);
        }
        for(OrganizationEntity organization:models){
            String newName = organization.getOrganizationName().replace("=","");
            newNames.add(newName);
        }
        queryWrapper.in(StatisticalEntity::getOrganizationName,newNames);
        Page page = new Page(param.getPage(), param.getRows());
        targetService.page(page, queryWrapper);
        for(StatisticalEntity statistical:(List<StatisticalEntity>)page.getRecords()){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(page);
    }

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("搜索查询分页")
    @PostMapping("/searchList")
    public BaseResp findSearchListByPage(@RequestBody BaseRequest<StatisticalEntity> param) throws ParseException {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(param.getParam().getOrganizationId() != null){
            //机构ID搜索
            queryWrapper.like(StatisticalEntity::getOrganizationId,param.getParam().getOrganizationId());
        }
        if(param.getDate() != null){
            String[] s = param.getDate().split("T");
            //获取当天日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(s[0]);//当天日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date2  = calendar.getTime();//第二天日期
            //创建时间搜索
            queryWrapper.eq(StatisticalEntity::getTaskDate,date2);
        }
        Page page = new Page(param.getPage(), param.getRows());
        targetService.page(page, queryWrapper);
        for(StatisticalEntity statistical:(List<StatisticalEntity>)page.getRecords()){
            switch (statistical.getState()){
                case "0":
                    statistical.setState("未反馈");
                    break;
                case "1":
                    statistical.setState("完成");
                    break;
                case "2":
                    statistical.setState("未完成");
                    break;
            }
            switch (statistical.getOrganizationLevel()){
                case "1":
                    statistical.setOrganizationLevel("分行");
                    break;
                case "2":
                    statistical.setOrganizationLevel("支行");
                    break;
                case "3":
                    statistical.setOrganizationLevel("网点");
                    break;
            }
            if(statistical.getTaskgroupId() == null || "".equals(statistical.getTaskgroupId())){
                statistical.setTaskgroupId("---");
            }
        }
        return BaseResp.success(page);
    }

    /**
     * 走马灯查询最新数据
     *
     * @author jerryniu
     */
    @ApiOperation("走马灯查询最新数据")
    @PostMapping("/lantern")
    public BaseResp findLantern() {
        LambdaQueryWrapper<StatisticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StatisticalEntity::getState,"1");
        queryWrapper.orderByDesc(StatisticalEntity::getUpdatetime);
        List<StatisticalEntity> models = targetService.list(queryWrapper);
            List<String> lantern = new ArrayList<>();
            if(models.size()>8){
                for(int i = 0;i<8;i++){
                    switch (models.get(i).getState()){
                        case "0":
                            models.get(i).setState("未反馈");
                            break;
                        case "1":
                            models.get(i).setState("完成");
                            break;
                        case "2":
                            models.get(i).setState("未完成");
                            break;
                    }
                    switch (models.get(i).getOrganizationLevel()){
                        case "1":
                            models.get(i).setOrganizationLevel("分行");
                            break;
                        case "2":
                            models.get(i).setOrganizationLevel("支行");
                            break;
                        case "3":
                            models.get(i).setOrganizationLevel("网点");
                            break;
                    }
                    lantern.add(models.get(i).getOrganizationName()+models.get(i).getTaskName()+models.get(i).getState());
                }
            }else{
                for(StatisticalEntity statistical: models){
                    switch (statistical.getState()){
                        case "0":
                            statistical.setState("未反馈");
                            break;
                        case "1":
                            statistical.setState("完成");
                            break;
                        case "2":
                            statistical.setState("未完成");
                            break;
                    }
                    switch (statistical.getOrganizationLevel()){
                        case "1":
                            statistical.setOrganizationLevel("分行");
                            break;
                        case "2":
                            statistical.setOrganizationLevel("支行");
                            break;
                        case "3":
                            statistical.setOrganizationLevel("网点");
                            break;
                    }
                    lantern.add(statistical.getOrganizationName()+statistical.getTaskName()+statistical.getState());
                }
            }
        return BaseResp.success(lantern.toArray(new String[lantern.size()]));
    }

    /**
     * 查询支行百分比数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询支行百分比数据")
    @PostMapping("/percentage")
    public BaseResp findPercentage() throws ParseException {
        List<Map<String,Object>> dataName = new ArrayList<>();
        Map<String,Object> datamap;

        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String string  = sdf.format(new Date());
        Date date = sdf.parse(string);//当天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date2  = calendar.getTime();//第二天日期

        LambdaQueryWrapper<OrganizationEntity> queryWrapper;
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"2");
        //查询所有支行机构
        List<OrganizationEntity> models = organizationService.list(queryWrapper);
        LambdaQueryWrapper<StatisticalEntity> queryWrapper1;
        for(OrganizationEntity organizationEntity: models){
            datamap = new HashMap<>();

            int flag = 0;
            int sum = 0;
            queryWrapper1 = new LambdaQueryWrapper<>();
            //查询支行所有任务的完成情况
            queryWrapper1.eq(StatisticalEntity::getOrganizationName,organizationEntity.getOrganizationName());
            //所属时间搜索
            queryWrapper1.between(StatisticalEntity::getTaskDate,date,date2);
            List<StatisticalEntity> statisticalmodels = targetService.list(queryWrapper1);
            for(StatisticalEntity statistical: statisticalmodels){
                if("1".equals(statistical.getState())){
                    flag++;
                }
                sum++;
            }
            //查询支行下属网点
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrganizationEntity::getPorganizationId,organizationEntity.getOrganizationId());
            List<OrganizationEntity> orgmodels = organizationService.list(queryWrapper);
            for(OrganizationEntity organ: orgmodels){
                queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(StatisticalEntity::getOrganizationName,organ.getOrganizationName());
                //所属时间搜索
                queryWrapper1.between(StatisticalEntity::getTaskDate,date,date2);
                List<StatisticalEntity> stamodels = targetService.list(queryWrapper1);
                for(StatisticalEntity statistical: stamodels){
                    if("1".equals(statistical.getState())){
                        flag++;
                    }
                    sum++;
                }
            }
            datamap.put("organizationName",organizationEntity.getOrganizationName());//机构名
            if(sum == 0) {
                datamap.put("percentage",100);//没有任务完成度百分之百
            }else if(flag == 0){
                datamap.put("percentage",0);//百分比
            } else{
                datamap.put("percentage",flag*100/sum);//百分比
            }
            dataName.add(datamap);
        }
        Collections.sort(dataName, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer s1 = (Integer)o1.get("percentage");
                Integer s2 = (Integer)o2.get("percentage");
                if (s1 < s2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return BaseResp.success(dataName);
    }

    /**
     * 任务查询支行百分比数据
     *
     * @author jerryniu
     */
    @ApiOperation("任务查询支行百分比数据")
    @PostMapping("/Taskpercentage")
    public BaseResp findTaskPercentage(@RequestBody(required=false) String name) throws ParseException {
        String taskName = URLDecoder.decode(name).replace("=","");
        List<Map<String,Object>> dataName = new ArrayList<>();
        Map<String,Object> datamap;

        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String string  = sdf.format(new Date());
        Date date = sdf.parse(string);//当天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date2  = calendar.getTime();//第二天日期

        LambdaQueryWrapper<OrganizationEntity> queryWrapper;
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"2");
        //查询所有支行机构
        List<OrganizationEntity> models = organizationService.list(queryWrapper);
        LambdaQueryWrapper<StatisticalEntity> queryWrapper1;
        for(OrganizationEntity organizationEntity: models){
            datamap = new HashMap<>();

            int flag = 0;
            int sum = 0;
            queryWrapper1 = new LambdaQueryWrapper<>();
            //查询支行所有任务的完成情况
            queryWrapper1.eq(StatisticalEntity::getOrganizationName,organizationEntity.getOrganizationName());
            queryWrapper1.eq(StatisticalEntity::getTaskName,taskName);
            //所属时间搜索
            queryWrapper1.between(StatisticalEntity::getTaskDate,date,date2);
            List<StatisticalEntity> statisticalmodels = targetService.list(queryWrapper1);
            for(StatisticalEntity statistical: statisticalmodels){
                if("1".equals(statistical.getState())){
                    flag++;
                }
                sum++;
            }
            //查询支行下属网点
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrganizationEntity::getPorganizationId,organizationEntity.getOrganizationId());
            List<OrganizationEntity> orgmodels = organizationService.list(queryWrapper);
            for(OrganizationEntity organ: orgmodels){
                queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(StatisticalEntity::getOrganizationName,organ.getOrganizationName());
                queryWrapper1.eq(StatisticalEntity::getTaskName,taskName);
                //所属时间搜索
                queryWrapper1.between(StatisticalEntity::getTaskDate,date,date2);
                List<StatisticalEntity> stamodels = targetService.list(queryWrapper1);
                for(StatisticalEntity statistical: stamodels){
                    if("1".equals(statistical.getState())){
                        flag++;
                    }
                    sum++;
                }
            }
            datamap.put("organizationName",organizationEntity.getOrganizationName());//机构名
            if(sum == 0) {
                datamap.put("percentage",100);//没有任务完成度百分之百
            }else if(flag == 0){
                datamap.put("percentage",0);//百分比
            } else{
                datamap.put("percentage",flag*100/sum);//百分比
            }
            dataName.add(datamap);
        }
        Collections.sort(dataName, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer s1 = (Integer)o1.get("percentage");
                Integer s2 = (Integer)o2.get("percentage");
                if (s1 < s2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return BaseResp.success(dataName);
    }
}
