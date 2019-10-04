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
 * <p>系统配置表</p>
 *
 * @author like
 * @since 2019-10-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_base_config")
@ApiModel(value = "TBaseConfigEntity对象", description = "系统配置表")
public class TBaseConfigEntity extends AbstractBaseEntity<TBaseConfigEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "配置类型 ")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "配置名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "配置代码 ")
    @TableField("key")
    private String key;

    @ApiModelProperty(value = "配置值 ")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "描述 ")
    @TableField("description")
    private String description;

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
