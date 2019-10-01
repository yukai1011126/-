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
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_area_department_role_user")
@ApiModel(value = "TAreaDepartmentRoleUserEntity对象", description = "")
public class TAreaDepartmentRoleUserEntity extends AbstractBaseEntity<TAreaDepartmentRoleUserEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "地区—单位部门ID ")
    @TableField("area_dept_id")
    private String areaDeptId;

    @ApiModelProperty(value = "角色ID ")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "角色名 ")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "user_id ")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "user_name ")
    @TableField("user_name")
    private String userName;

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