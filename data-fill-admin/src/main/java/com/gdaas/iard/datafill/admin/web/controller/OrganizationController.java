/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.repo.dao.entity.StatisticalEntity;
import com.gdaas.iard.datafill.admin.service.OrganizationService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.OrganizationEntity;
import com.gdaas.iard.datafill.admin.service.StatisticalService;
import com.gdaas.iard.datafill.admin.util.ExcelUtil;
import com.gdaas.iard.datafill.admin.web.common.BaseResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> 前端控制器</p>
 * 机构表
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-11-12
 */
@Api(tags = "OrganizationController")
@RestController
@RequestMapping("/OrganizationController")
public class OrganizationController {
    @Autowired
    private OrganizationService targetService;

    @Autowired
    private StatisticalService statisticalService;

    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<OrganizationEntity> param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(param.getParam().getOrganizationLevel()!="" || param.getParam().getOrganizationLevel() != null){
            switch (param.getParam().getOrganizationLevel()){
                case "分行":
                    param.getParam().setOrganizationLevel("1");
                    break;
                case "支行":
                    param.getParam().setOrganizationLevel("2");
                    break;
                case "网点":
                    param.getParam().setOrganizationLevel("3");
                    break;
            }
            String[] level = param.getParam().getOrganizationLevel().split(",");
            //查询机构等级为0的数据
            queryWrapper.in(OrganizationEntity::getOrganizationLevel,level);
        }
        //机构号搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getOrganizationCode()),OrganizationEntity::getOrganizationCode,param.getParam().getOrganizationCode());
        //机构名称搜索
        queryWrapper.like(StringUtils.isNotEmpty(param.getParam().getOrganizationName()),OrganizationEntity::getOrganizationName,param.getParam().getOrganizationName());
        Page page = new Page(param.getPage(), param.getRows());
        targetService.page(page, queryWrapper);
        for(OrganizationEntity organization:(List<OrganizationEntity>)page.getRecords()){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
                    break;
            }
            if(organization.getPorganizationName() == null || "".equals(organization.getPorganizationName())){
                organization.setPorganizationName("---");
            }
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
    public BaseResp findAll(@RequestBody OrganizationEntity param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization:models){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
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
    public BaseResp find(@RequestBody String id) {
        OrganizationEntity entity = targetService.getById(id);
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
    public BaseResp addItem(@RequestBody OrganizationEntity param) {
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
    public BaseResp updateItem(@RequestBody OrganizationEntity param) {
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
    public BaseResp deleteItems(@RequestBody OrganizationEntity param) {
        List<Integer> ids = new ArrayList<>();
        ids.add(param.getOrganizationId());
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }

    /**
     * 获取全部pid相同的数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询所有数据")
    @PostMapping("/pid")
    public BaseResp findPIDAll(@RequestBody OrganizationEntity param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        //查询所有相同父ID的数据
        queryWrapper.eq(StringUtils.isNotEmpty(param.getPorganizationId()+""),OrganizationEntity::getPorganizationId,param.getPorganizationId());
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization:models){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
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
    @ApiOperation("查询所有机构")
    @PostMapping("/listOrg")
    public BaseResp findListOrg() {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization:models){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
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
    @ApiOperation("查询所有机构")
    @PostMapping("/IdOrPid")
    public BaseResp findIdOrPid(@RequestBody String id) {
        String newid = id.replace("=","");
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationId,newid).or().eq(OrganizationEntity::getPorganizationId,newid);
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization:models){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 机构导出
     * @param request
     * @param response
     */
    @RequestMapping("/orgexcel.do")
    @ResponseBody
    public void ftbExcelExport(HttpServletRequest request, HttpServletResponse response) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        ExcelUtil.organizationExcel(models,response);
    }

    /**
     * 机构模板导出
     * @param request
     * @param response
     */
    @RequestMapping("/orgmbexcel.do")
    @ResponseBody
    public void mbftbExcelExport(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtil.mborganizationExcel(response);
    }

    /**
     * 获取根据父机构名称搜索子机构数据
     *
     * @author jerryniu
     */
    @ApiOperation("父机构名称查询数据")
    @PostMapping("/PNameOrg")
    public BaseResp findPNameOrg(@RequestBody String organizationName) {
        String orgName = URLDecoder.decode(organizationName);
        String neworganizationName = orgName.replace("=","");
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(neworganizationName),OrganizationEntity::getPorganizationName,neworganizationName);
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization:models){
            switch (organization.getOrganizationLevel()){
                case "1":
                    organization.setOrganizationLevel("分行");
                    break;
                case "2":
                    organization.setOrganizationLevel("支行");
                    break;
                case "3":
                    organization.setOrganizationLevel("网点");
                    break;
            }
        }
        return BaseResp.success(models);
    }

    /**
     * 查询穿梭框中的机构
     *
     * @author
     */
    @ApiOperation("查询穿梭框中的机构")
    @PostMapping("/shuttlelist")
    public BaseResp findShuttlelist(@RequestBody OrganizationEntity param) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(param.getOrganizationLevel()!="" || param.getOrganizationLevel() != null){
            String[] level = param.getOrganizationLevel().split(",");
            //查询机构等级为0的数据
            queryWrapper.in(OrganizationEntity::getOrganizationLevel,level);
        }
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        List<JSONObject> jsonslist = new ArrayList<>();
        JSONObject json;
        for(int i = 0;i<models.size();i++){
            json = new JSONObject();
            json.put("id",models.get(i).getOrganizationId());
            json.put("label",models.get(i).getOrganizationName());
            List<JSONObject> jsons = recursionFindOrg(models.get(i).getOrganizationId());
            json.put("children", jsons);
            json.put("data",models.get(i));
            jsonslist.add(json);
        }
        return BaseResp.success(jsonslist);
    }
    //递归查询子机构
    private List<JSONObject> recursionFindOrg(Integer organizationId){
        LambdaQueryWrapper<OrganizationEntity> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(OrganizationEntity::getPorganizationId,organizationId);
        List<OrganizationEntity> models1 = targetService.list(queryWrapper1);
        List<JSONObject> jsonLists = new ArrayList<>();
        JSONObject json;
        for(int i = 0;i<models1.size();i++){
            json = new JSONObject();
            json.put("id",models1.get(i).getOrganizationId());
//            json.put("pid",organizationId);
            json.put("label",models1.get(i).getOrganizationName());
            List<JSONObject> jsonList = recursionFindOrg(models1.get(i).getOrganizationId());
            json.put("children", jsonList);
            json.put("data",models1.get(i));
            jsonLists.add(json);
        }
        return jsonLists;
    }

    @ApiOperation("查询支行机构")
    @PostMapping("/subbranchlist")
    public BaseResp findSubbranchlist() {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"2");
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        List<OrganizationEntity> list = new ArrayList<>();
        for(OrganizationEntity organizationEntity: models){
            if(!"第一营业部".equals(organizationEntity.getOrganizationName()) && !"分行营业部".equals(organizationEntity.getOrganizationName())){
                list.add(organizationEntity);
            }
        }
        return BaseResp.success(list);
    }

    @ApiOperation("机构名称查询数据")
    @PostMapping("/findNameOrg")
    public BaseResp findNameOrg(@RequestBody String organizationName) {
        System.out.println(organizationName);
        String orgName = URLDecoder.decode(organizationName);
        String neworganizationName = orgName.replace("=","");
        System.out.println(neworganizationName);
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationName,neworganizationName);
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        return BaseResp.success(models);
    }

    @ApiOperation("查询支行网点机构坐标")
    @PostMapping("/findAxisMap")
    public BaseResp findAxisMap() throws ParseException {
        //返回map
        List<Map<String,Object>> Lists = new ArrayList<>();
        Map<String,Object> map;
        //坐标,数值
        List<Object> axislist;
        LambdaQueryWrapper<OrganizationEntity> queryWrapper;
        LambdaQueryWrapper<StatisticalEntity> statisticalWrapper;
        //获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String string  = sdf.format(new Date());
        Date date = sdf.parse(string);//当天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date2  = calendar.getTime();//第二天日期
        //查询部室坐标
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"1");
        List<OrganizationEntity> models3 = targetService.list(queryWrapper);
        for(OrganizationEntity organization: models3){
            map = new HashMap<>();
            axislist = new ArrayList<>();
            axislist.add(organization.getAxisX());
            axislist.add(organization.getAxisY());
            //部室数值
            statisticalWrapper = new LambdaQueryWrapper<>();
            statisticalWrapper.eq(StatisticalEntity::getOrganizationId,organization.getOrganizationId());
            statisticalWrapper.in(StatisticalEntity::getState,"0","2");
            //所属时间搜索
            statisticalWrapper.between(StatisticalEntity::getTaskDate,date,date2);
            List<StatisticalEntity> models = statisticalService.list(statisticalWrapper);
            if(models.size()>0){
                axislist.add("3");
            }else {
                axislist.add("6");
            }
            //部室名称
            map.put("name",organization.getOrganizationName());
            map.put("value",axislist);
            Lists.add(map);
        }
        //查询支行坐标
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"2");
        List<OrganizationEntity> models = targetService.list(queryWrapper);
        for(OrganizationEntity organization: models){
            map = new HashMap<>();
            axislist = new ArrayList<>();
            axislist.add(organization.getAxisX());
            axislist.add(organization.getAxisY());
            //支行数值
            statisticalWrapper = new LambdaQueryWrapper<>();
            statisticalWrapper.eq(StatisticalEntity::getOrganizationId,organization.getOrganizationId());
            statisticalWrapper.in(StatisticalEntity::getState,"0","2");
            //所属时间搜索
            statisticalWrapper.between(StatisticalEntity::getTaskDate,date,date2);
            List<StatisticalEntity> statisticalmodels = statisticalService.list(statisticalWrapper);
            if(statisticalmodels.size()>0){
                axislist.add("2");
            }else {
                axislist.add("5");
            }
            //支行名称
            map.put("name",organization.getOrganizationName());
            map.put("value",axislist);
            Lists.add(map);
        }
        //查询网点坐标
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getOrganizationLevel,"3");
        List<OrganizationEntity> models1 = targetService.list(queryWrapper);
        for(OrganizationEntity organization : models1){
            map = new HashMap<>();
            axislist = new ArrayList<>();
            axislist.add(organization.getAxisX());
            axislist.add(organization.getAxisY());
            //网点数值
            statisticalWrapper = new LambdaQueryWrapper<>();
            statisticalWrapper.eq(StatisticalEntity::getOrganizationId,organization.getOrganizationId());
            statisticalWrapper.in(StatisticalEntity::getState,"0","2");
            //所属时间搜索
            statisticalWrapper.between(StatisticalEntity::getTaskDate,date,date2);
            List<StatisticalEntity> statisticalmodels = statisticalService.list(statisticalWrapper);
            if(statisticalmodels.size()>0){
                axislist.add("1");
            }else {
                axislist.add("4");
            }
            //支行名称
            map.put("name",organization.getOrganizationName());
            map.put("value",axislist);
            Lists.add(map);
        }
        return BaseResp.success(Lists);
    }
}
