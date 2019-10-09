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
 * <p>任务发布表</p>
 *
 * @author like
 * @since 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_task_release")
@ApiModel(value = "TTaskReleaseEntity对象", description = "任务发布表")
public class TTaskReleaseEntity extends AbstractBaseEntity<TTaskReleaseEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "模板ID ")
    @TableField("template_id")
    private String templateId;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "任务下达机构ID 最高层级的机构ID")
    @TableField("area_owner_id")
    private String areaOwnerId;

    @ApiModelProperty(value = "任务下达机构名 ")
    @TableField("area_owner_name")
    private String areaOwnerName;

    @ApiModelProperty(value = "任务接收机构ID ")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty(value = "任务接收机构名 ")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "任务接收单位部门名 ")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "任务接收单位部门ID ")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "任务接收单位部门对应的角色ID ")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "任务接收单位部门对应的角色name ")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "任务转发状态 0 初始，1 已下达  2 已接收")
    @TableField("task_accept_status")
    private String taskAcceptStatus;

    @ApiModelProperty(value = "任务ID ")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "任务名 ")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "此机构的填报状态 0 初始 1 待填报，2 待审核  3 终审核")
    @TableField("task_status")
    private String taskStatus;

    @ApiModelProperty(value = "任务截止时间  ")
    @TableField("task_end_time")
    private Date taskEndTime;

    @ApiModelProperty(value = "任务下发时间 ")
    @TableField("task_start_time")
    private Date taskStartTime;

    @ApiModelProperty(value = "任务创建人 ")
    @TableField("task_creator")
    private String taskCreator;

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
