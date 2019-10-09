/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.repo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.gdaas.iard.datafill.wechat.repo.dao.AbstractBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>任务审核记录表</p>
 *
 * @author like
 * @since 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_task_audit_log")
@ApiModel(value = "TTaskAuditLogEntity对象", description = "任务审核记录表")
public class TTaskAuditLogEntity extends AbstractBaseEntity<TTaskAuditLogEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableField("id")
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

    @ApiModelProperty(value = "任务ID ")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "任务名 ")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务截止时间  ")
    @TableField("task_end_time")
    private Date taskEndTime;

    @ApiModelProperty(value = "任务下发时间 ")
    @TableField("task_start_time")
    private Date taskStartTime;

    @ApiModelProperty(value = "任务上一处理人员 ")
    @TableField("task_prev_user")
    private String taskPrevUser;

    @ApiModelProperty(value = "审核机构id ")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty(value = "审核机构名 ")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "审核人员单位ID ")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "审核人员单位名 ")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "审核人员角色 ")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "审核人员角色 名 ")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "审核人员ID ")
    @TableField("audit_user_id")
    private String auditUserId;

    @ApiModelProperty(value = "任务审核人员 ")
    @TableField("audit_user")
    private String auditUser;

    @ApiModelProperty(value = "任务审核时间  ")
    @TableField("audit_time")
    private Date auditTime;

    @ApiModelProperty(value = "审核任务状态 1 提交，2 退回 ")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "审核意见 ")
    @TableField("comment")
    private String comment;

    @ApiModelProperty(value = "任务创建人 ")
    @TableField("task_creator")
    private String taskCreator;

    @ApiModelProperty(value = "审核时的位置 （经度） ")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "审核时的位置,（纬度 ")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "记录创建时间 ")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间时间 ")
    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
