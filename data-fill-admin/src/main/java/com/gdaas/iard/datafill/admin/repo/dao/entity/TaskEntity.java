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
@TableName("task")
@ApiModel(value = "TaskEntity对象", description = "")
public class TaskEntity extends AbstractBaseEntity<TaskEntity> {

    @ApiModelProperty(value = "任务id")
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @ApiModelProperty(value = "任务名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务内容")
    @TableField("task_text")
    private String taskText;

    @ApiModelProperty(value = "任务开始时间")
    @TableField("startime")
    private Date startime;

    @ApiModelProperty(value = "任务结束时间")
    @TableField("endtime")
    private Date endtime;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @ApiModelProperty(value = "任务所属日期")
    @TableField("task_date")
    private Date taskDate;

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }
}
