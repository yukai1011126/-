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
 * <p></p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_comm_area")
@ApiModel(value = "TCommAreaEntity对象", description = "")
public class TCommAreaEntity extends AbstractBaseEntity<TCommAreaEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "地区名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "简称 ")
    @TableField("short_name")
    private String shortName;

    @ApiModelProperty(value = "父ID ")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "层级 ")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "代码的合并 ")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "名字合并项 从大到小，号分开")
    @TableField("merge_name")
    private String mergeName;

    @ApiModelProperty(value = "名字合并项 ")
    @TableField("short_merge_name")
    private String shortMergeName;

    @ApiModelProperty(value = "经度 ")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度 ")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "拼音 ")
    @TableField("pinying")
    private String pinying;

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
