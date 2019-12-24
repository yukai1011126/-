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
@TableName("statistical")
@ApiModel(value = "StatisticalEntity对象", description = "")
public class StatisticalEntity extends AbstractBaseEntity<StatisticalEntity> {

    @ApiModelProperty(value = "统计id")
    @TableId(value = "statistical_id", type = IdType.AUTO)
    private Integer statisticalId;

    @ApiModelProperty(value = "任务组id")
    @TableField("taskgroup_id")
    private String taskgroupId;

    @ApiModelProperty(value = "任务id")
    @TableField("task_id")
    private Integer taskId;

    @ApiModelProperty(value = "任务名称")
    @TableField("task_name")
    private String taskName;

    @ApiModelProperty(value = "机构id")
    @TableField("organization_id")
    private Integer organizationId;

    @ApiModelProperty(value = "机构名称")
    @TableField("organization_name")
    private String organizationName;

    @ApiModelProperty(value = "机构级别")
    @TableField("organization_level")
    private String organizationLevel;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "任务所属日期")
    @TableField("task_date")
    private Date taskDate;
    @Override
    protected Serializable pkVal() {
        return this.statisticalId;
    }
}
