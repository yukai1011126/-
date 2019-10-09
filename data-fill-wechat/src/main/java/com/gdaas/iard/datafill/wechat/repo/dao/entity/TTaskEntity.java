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
 * <p>我的待处理工作任务表</p>
 *
 * @author like
 * @since 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_task")
@ApiModel(value = "TTaskEntity对象", description = "我的待处理工作任务表")
public class TTaskEntity extends AbstractBaseEntity<TTaskEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "模板ID ")
    @TableField("template_id")
    private String templateId;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "任务处理人员所在机构ID ")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty(value = "任务处理人员所在机构name ")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "任务处理人员部门名 ")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "任务处理人员部门ID ")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "模板任务表ID 模板任务表ID")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty(value = "任务名 ")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务处理人员ID ")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "任务处理人员 ")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "审核任务类型 0 待填报，1 待审核 ，2 待下达 ")
    @TableField("task_type")
    private String taskType;

    @ApiModelProperty(value = "审核截止时间  ")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "审核下发时间 ")
    @TableField("start_time")
    private Date startTime;

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
        return null;
    }
}
