/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao.entity;

import com.gdaas.iard.datafill.admin.repo.dao.AbstractBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>字典表</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_data_dict")
@ApiModel(value = "TDataDictEntity对象", description = "字典表")
public class TDataDictEntity extends AbstractBaseEntity<TDataDictEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "指标名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "指标代码 用于创建字段名")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "父ID ")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "层级 ")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "ID合并 ")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "名字合并项 ")
    @TableField("merge_name")
    private String mergeName;

    @ApiModelProperty(value = "计量单位 ")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "数据类型 varchar  decimal")
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

    @ApiModelProperty(value = "排序_序号 ")
    @TableField("sort_number")
    private String sortNumber;

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
