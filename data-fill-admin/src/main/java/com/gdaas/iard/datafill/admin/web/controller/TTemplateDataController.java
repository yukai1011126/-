/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TDataDictEntity;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TTemplateDataEntity;
import com.gdaas.iard.datafill.admin.repo.dao.entity.TTemplateEntity;
import com.gdaas.iard.datafill.admin.service.TDataDictService;
import com.gdaas.iard.datafill.admin.service.TTemplateDataService;
import com.gdaas.iard.datafill.admin.service.TTemplateService;
import com.gdaas.iard.datafill.admin.util.MyUtil;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * <p> 前端控制器</p>
 *
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-10-05
 */
@Log4j2
@CrossOrigin
@Api(tags = "TTemplateDataController")
@RestController
@RequestMapping("/TTemplateDataController")
public class TTemplateDataController {
    @Autowired
    private TTemplateService targetService;
    @Autowired
    private TDataDictService targetDictService;
    @Autowired
    private TTemplateDataService targetDataService;
    /**
     * 获取数据列表
     *
     * @author jerryniu
     */
    @ApiOperation("查询分页")
    @PostMapping("/list")
    public BaseResp findListByPage(@RequestBody BaseRequest<TTemplateDataEntity> param) {
        Page page = MyUtil.pageDecorate(param);
        String templatemode = param.getParam().getTemplateId();
        LambdaQueryWrapper<TTemplateDataEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(templatemode), TTemplateDataEntity::getTemplateId, templatemode);
        log.info("查询参数为：{}",param.getParam().toString());
        targetDataService.page(page, queryWrapper);
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
        List<TTemplateDataEntity> models = targetDataService.list();
        return BaseResp.success(models);
    }

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @PostMapping("/find")
    public BaseResp find(@RequestBody BaseRequest<TTemplateDataEntity> baseRequest) {
        TTemplateDataEntity entity = targetDataService.getById(baseRequest.getParam().getId());
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
    @Transactional
    public BaseResp addItem(@RequestBody TTemplateDataEntity entity){
        boolean isOk = StringUtils.isEmpty(entity.getId());
        List<TTemplateDataEntity> tlist = null;
        TTemplateEntity ttemplate = targetService.getById(entity.getTemplateId());
        try {
            // 删除陈旧模板数据
            String templatedatalist = ttemplate.getTemplatedatalist();
            String[] templatedatalists = StringUtils.isNotEmpty(templatedatalist) ? templatedatalist.split(",") : null;
            if(templatedatalists != null && templatedatalists.length > 0){
                List<String> list = new ArrayList<>();
                for(String templatedataid : templatedatalists){
                    list.add(templatedataid);
                }
                targetDataService.removeByIds(list);
            }

            // 根据字典数据批量增加模板数据
            String dict = entity.getDictId();
            String[] dictids = StringUtils.isEmpty(dict) ? null : dict.split(",");
            StringBuilder ttemplatedataids = new StringBuilder();
            if(dictids != null && dictids.length > 0){
                tlist = new ArrayList<>();
                for(int i = 0 , len = dictids.length ; i < len ; i++){
                    TDataDictEntity tDataDictEntity = targetDictService.getById(dictids[i]);
                    // 添加数据字典到模板数据集合中(同时添加模板信息)
                    TTemplateDataEntity tTemplateDataEntitytemp = (TTemplateDataEntity) MyUtil.addOrEditDecorate(copyTemToTemdata(i,tDataDictEntity,ttemplate), true);
                    ttemplatedataids.append(tTemplateDataEntitytemp.getId()).append(",");
                    tlist.add(tTemplateDataEntitytemp);
                }
            } else {
                return BaseResp.error("数据字典编号为空");
            }
            ttemplate.setTemplatedatalist(ttemplatedataids.substring(0,ttemplatedataids.length()-1));
            targetService.updateById(ttemplate);
            isOk = targetDataService.saveBatch(tlist);
            log.info("保存数据结束：{}，保存结果：{}",entity.toString(),isOk);
        } catch (Exception e) {
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
    public BaseResp deleteItems(@RequestBody List<Long> ids) {
        boolean isOk = targetService.removeByIds(ids);
        if (isOk) {
            return BaseResp.success("数据删除成功");
        }
        return BaseResp.fail("数据删除失败");
    }

    private TTemplateDataEntity copyTemToTemdata(int i, TDataDictEntity tDataDictEntity,TTemplateEntity ttemplate){
        Function<TDataDictEntity, TTemplateDataEntity> func = x -> {
            TTemplateDataEntity tTemplateDataEntity = new TTemplateDataEntity();
            tTemplateDataEntity.setDataType(x.getDataType());
            tTemplateDataEntity.setDataLength(x.getDataLength());
            tTemplateDataEntity.setDefaultValue(x.getDefaultValue());
            tTemplateDataEntity.setFormula(x.getFormula());
            tTemplateDataEntity.setSelectValue(x.getSelectValue());
            tTemplateDataEntity.setDictId(x.getId());
            tTemplateDataEntity.setDictName(x.getName());
            tTemplateDataEntity.setDictCode(x.getCode());
            tTemplateDataEntity.setTemplateNumber(String.valueOf(i+1));
            return tTemplateDataEntity;
        };
        return func.apply(tDataDictEntity)
                .setTemplateId(ttemplate.getId())
                .setTemplateName(ttemplate.getTemplateName());
    }

}