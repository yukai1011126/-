/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.repo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.gdaas.iard.datafill.wechat.repo.dao.AbstractBaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>模板任务数据项值</p>
 *
 * @author like
 * @since 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_template_data_value")
@ApiModel(value = "TTemplateDataValueEntity对象", description = "模板任务数据项值")
public class TTemplateDataValueEntity extends AbstractBaseEntity<TTemplateDataValueEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "模板名ID ")
    @TableField("template_id")
    private String templateId;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "模板编号 ")
    @TableField("template_number")
    private String templateNumber;

    @ApiModelProperty(value = "最后数据字典ID ")
    @TableField("dict_id")
    private String dictId;

    @ApiModelProperty(value = "最后数据字典名 ")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty(value = "最后数据字典编码 根据code相创建字段")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "计量单位 ")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "数据类型 varchar , decimal")
    @TableField("data_type")
    private String dataType;

    @ApiModelProperty(value = "长度 ")
    @TableField("data_length")
    private Integer dataLength;

    @ApiModelProperty(value = "可选值  ")
    @TableField("select_value")
    private String selectValue;

    @ApiModelProperty(value = "默认值  ")
    @TableField("default_value")
    private String defaultValue;

    @ApiModelProperty(value = "计算公式 ")
    @TableField("formula")
    private String formula;

    @ApiModelProperty(value = "序号 ")
    @TableField("sort_number")
    private String sortNumber;

    @ApiModelProperty(value = "任务ID ")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "任务名 ")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务对应的表名 ")
    @TableField("task_table")
    private String taskTable;

    @ApiModelProperty(value = "统计日期 ")
    @TableField("statistic_date")
    private Date statisticDate;

    @ApiModelProperty(value = "填报行政地区ID ")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty(value = "填报行政地区name ")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "填报部门单位ID ")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "填报部门单位name ")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "数据项值  ")
    @TableField("data_value")
    private String dataValue;

    @ApiModelProperty(value = "记录创建时间 ")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间时间 ")
    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
