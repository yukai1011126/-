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
 * <p>我的报表</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_my_report")
@ApiModel(value = "TMyReportEntity对象", description = "我的报表")
public class TMyReportEntity extends AbstractBaseEntity<TMyReportEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "模板ID ")
    @TableField("template_id")
    private String templateId;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "报表名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "报表日期 ")
    @TableField("report_date")
    private Date reportDate;

    @ApiModelProperty(value = "报表URL ")
    @TableField("report_url")
    private String reportUrl;

    @ApiModelProperty(value = "报表用户ID ")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "报表用户ID ")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "状态 1  有效，0 无效")
    @TableField("status")
    private String status;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
