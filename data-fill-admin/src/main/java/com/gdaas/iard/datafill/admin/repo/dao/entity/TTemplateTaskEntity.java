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
 * <p>模板任务</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_template_task")
@ApiModel(value = "TTemplateTaskEntity对象", description = "模板任务")
public class TTemplateTaskEntity extends AbstractBaseEntity<TTemplateTaskEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "模板数据项ID ")
    @TableField("template_id")
    private String templateId;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "模板编号 ")
    @TableField("template_number")
    private String templateNumber;

    @ApiModelProperty(value = "任务名 ")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "任务备注 ")
    @TableField("task_memo")
    private String taskMemo;

    @ApiModelProperty(value = "任务截止时间  ")
    @TableField("task_end_time")
    private Date taskEndTime;

    @ApiModelProperty(value = "任务下发时间 ")
    @TableField("task_start_time")
    private Date taskStartTime;

    @ApiModelProperty(value = "任务创建人 ")
    @TableField("task_creator")
    private String taskCreator;

    @ApiModelProperty(value = "任务状态 ")
    @TableField("task_status")
    private String taskStatus;

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
