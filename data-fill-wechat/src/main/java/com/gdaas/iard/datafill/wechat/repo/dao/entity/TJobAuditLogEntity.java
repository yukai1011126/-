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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p></p>
 *
 * @author like
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_job_audit_log")
@ApiModel(value = "TJobAuditLogEntity对象", description = "")
public class TJobAuditLogEntity extends AbstractBaseEntity<TJobAuditLogEntity> {

    @TableField("id")
    private String id;

    @TableField("template_id")
    private String templateId;

    @TableField("template_name")
    private String templateName;

    @TableField("area_id")
    private String areaId;

    @TableField("area_name")
    private String areaName;

    @TableField("job_id")
    private String jobId;

    @TableField("job_name")
    private String jobName;

    @TableField("job_end_time")
    private Date jobEndTime;

    @TableField("job_start_time")
    private Date jobStartTime;

    @TableField("job_prev_user")
    private String jobPrevUser;

    @TableField("audit_area_id")
    private String auditAreaId;

    @TableField("audit_area_name")
    private String auditAreaName;

    @TableField("dept_id")
    private String deptId;

    @TableField("dept_name")
    private String deptName;

    @TableField("audit_user")
    private String auditUser;

    @TableField("audit_time")
    private Date auditTime;

    @TableField("status")
    private String status;

    @TableField("job_creator")
    private String jobCreator;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("comment")
    private String comment;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
