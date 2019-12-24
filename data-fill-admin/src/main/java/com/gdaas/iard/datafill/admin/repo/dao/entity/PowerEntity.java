/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao.entity;

import com.gdaas.iard.datafill.admin.repo.dao.AbstractBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 *
 * @author like
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("power")
@ApiModel(value = "PowerEntity对象", description = "")
public class PowerEntity extends AbstractBaseEntity<PowerEntity> {

    @ApiModelProperty(value = "权限id")
    @TableId(value = "power_id", type = IdType.AUTO)
    private Integer powerId;

    @ApiModelProperty(value = "权限等级")
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "权限名称")
    @TableField("power_name")
    private String powerName;

    @ApiModelProperty(value = "权限菜单")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @Override
    protected Serializable pkVal() {
        return this.powerId;
    }
}
