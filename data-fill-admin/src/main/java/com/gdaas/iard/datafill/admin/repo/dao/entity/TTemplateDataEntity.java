/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gdaas.iard.datafill.admin.repo.dao.AbstractBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>模板配置数据项表</p>
 *
 * @author like
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_template_data")
@ApiModel(value = "TTemplateDataEntity对象", description = "模板配置数据项表")
public class TTemplateDataEntity extends AbstractBaseEntity<TTemplateDataEntity> {

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

    @ApiModelProperty(value = "记录创建时间 ")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间时间 ")
    @TableField("update_time")
    private Date updateTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
