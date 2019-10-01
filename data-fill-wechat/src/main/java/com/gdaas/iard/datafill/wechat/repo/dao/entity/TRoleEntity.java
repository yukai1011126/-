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
 * <p>角色表</p>
 *
 * @author like
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(value = "TRoleEntity对象", description = "角色表")
public class TRoleEntity extends AbstractBaseEntity<TRoleEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "角色名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色备注 ")
    @TableField("memo")
    private String memo;

    @ApiModelProperty(value = "角色类型 1 填报 2 审核 3 URL 权限角色，")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "状态 1 正常，0 无效")
    @TableField("status")
    private String status;

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
