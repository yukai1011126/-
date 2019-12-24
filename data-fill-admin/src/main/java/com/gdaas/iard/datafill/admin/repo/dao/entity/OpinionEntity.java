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
@TableName("opinion")
@ApiModel(value = "OpinionEntity对象", description = "")
public class OpinionEntity extends AbstractBaseEntity<OpinionEntity> {

    @ApiModelProperty(value = "反馈id")
    @TableId(value = "opinion_id", type = IdType.AUTO)
    private Integer opinionId;

    @ApiModelProperty(value = "反馈内容")
    @TableField(value = "opinion_text")
    private String opinionText;

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

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @Override
    protected Serializable pkVal() {
        return this.opinionId;
    }
}
