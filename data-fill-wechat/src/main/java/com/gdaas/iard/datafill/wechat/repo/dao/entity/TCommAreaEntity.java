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
 * <p></p>
 *
 * @author like
 * @since 2019-09-25
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

    @TableField("name")
    private String name;

    @TableField("short_name")
    private String shortName;

    @TableField("parent_id")
    private String parentId;

    @TableField("level")
    private String level;

    @TableField("path")
    private String path;

    @TableField("merge_name")
    private String mergeName;

    @TableField("short_merge_name")
    private String shortMergeName;

    @TableField("longitude")
    private String longitude;

    @TableField("latitude")
    private String latitude;

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
