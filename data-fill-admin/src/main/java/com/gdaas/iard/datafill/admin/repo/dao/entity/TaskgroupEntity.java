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
@TableName("taskgroup")
@ApiModel(value = "TaskgroupEntity对象", description = "")
public class TaskgroupEntity extends AbstractBaseEntity<TaskgroupEntity> {

    @ApiModelProperty(value = "任务组id")
    @TableId(value = "taskgroup_id", type = IdType.AUTO)
    private Integer taskgroupId;

    @ApiModelProperty(value = "任务id")
    @TableField("task_id")
    private Integer taskId;

    @ApiModelProperty(value = "任务组名称")
    @TableField("taskgroup_name")
    private String taskgroupName;

    @ApiModelProperty(value = "任务组开始时间")
    @TableField("taskgroup_startime")
    private Date taskgroupStartime;

    @ApiModelProperty(value = "任务组结束时间")
    @TableField("taskgroup_endtime")
    private Date taskgroupEndtime;

    @ApiModelProperty(value = "任务组创建时间")
    @TableField("taskgroup_createtime")
    private Date taskgroupCreatetime;

    @ApiModelProperty(value = "任务组更新时间")
    @TableField("taskgroup_updatetime")
    private Date taskgroupUpdatetime;

    @Override
    protected Serializable pkVal() {
        return this.taskgroupId;
    }
}
