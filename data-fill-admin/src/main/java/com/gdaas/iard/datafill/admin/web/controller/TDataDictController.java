/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gdaas.iard.datafill.admin.service.TDataDictService;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TDataDictEntity;
import com.gdaas.iard.datafill.admin.util.MyUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * <p>字典表 前端控制器</p>
 *
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-10-05
 */
@Log4j2
@CrossOrigin
@Api(tags = "TDataDictController")
@RestController
@RequestMapping("/TDataDictController")
public class TDataDictController {
    @Autowired
    private TDataDictService targetService;
    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<TDataDictEntity> param) {
        Page page = MyUtil.pageDecorate(param);
        String name = param.getParam().getName();
        String level = param.getParam().getLevel();
        String parentId = param.getParam().getParentId();
        LambdaQueryWrapper<TDataDictEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(name), TDataDictEntity::getName, name)
                .like(StringUtils.isNotEmpty(parentId), TDataDictEntity::getParentId, parentId)
                .eq(StringUtils.isNotEmpty(level), TDataDictEntity::getLevel, level);
        log.info("查询参数为：{}",param.getParam().toString());
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
        List<TDataDictEntity> models = targetService.list();
        List<Map<String,Object>> data = new ArrayList<>();
        for(TDataDictEntity td : models){
            Map<String,Object> map = null;
            map = new HashMap<>();
            map.put("id",td.getId());
            map.put("parentId",td.getParentId());
            map.put("label",td.getName());
            data.add(map);
        }
        JSONArray result = listToTree(JSONArray.parseArray(JSON.toJSONString(data)),"id","parentId","children");
        return BaseResp.success(result != null ? result : "数据为空");
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TDataDictEntity> baseRequest) {
        TDataDictEntity entity = targetService.getById(baseRequest.getParam().getId());
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
    public BaseResp addItem(@RequestBody TDataDictEntity entity){
        boolean isOk = StringUtils.isEmpty(entity.getId());
        String level;
        try {
            if(isOk){
                String sequence = targetService.findTopSequence("sort_number","t_data_dict");
                entity.setSortNumber(StringUtils.isEmpty(sequence) ? "1" : sequence);
                if(StringUtils.isEmpty(entity.getParentId())){
                    entity.setParentId("0");
                    entity.setPath(entity.getId());
                    entity.setMergeName(entity.getName());
                    level = "1";
                }else{
                    TDataDictEntity parentData = targetService.getById(entity.getParentId());
                    entity.setPath(parentData.getPath() + "," + entity.getId());
                    entity.setMergeName(parentData.getName() + "," + entity.getName());
                    level = String.valueOf((Integer.parseInt(parentData.getLevel())+ 1));
                }
                entity.setLevel(level);
            }
            entity = (TDataDictEntity) MyUtil.addOrEditDecorate(entity, isOk);
            isOk = isOk ? targetService.save(entity) : targetService.updateById(entity);
            log.info("保存数据结束：{}，保存结果：{}",entity.toString(),isOk);
        } catch (ParseException e) {
            log.info("数据保存异常：{}",e);
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

    /**
     - listToTree
     - <p>方法说明<p>
     - 将JSONArray数组转为树状结构
     - @param arr 需要转化的数据
     - @param id 数据唯一的标识键值
     - @param pid 父id唯一标识键值
     - @param child 子节点键值
     - @return JSONArray
     */
    JSONArray listToTree(JSONArray arr, String id, String pid, String child){
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<arr.size();i++){
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for(int j=0;j<arr.size();j++){
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                r.add(aVal);
            }
        }
        return r;
    }

}
