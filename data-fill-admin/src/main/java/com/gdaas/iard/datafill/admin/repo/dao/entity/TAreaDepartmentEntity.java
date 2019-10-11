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
 * <p></p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_area_department")
@ApiModel(value = "TAreaDepartmentEntity对象", description = "")
public class TAreaDepartmentEntity extends AbstractBaseEntity<TAreaDepartmentEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @TableField("area_id")
    private String areaId;

    @TableField("area_name")
    private String areaName;

    @ApiModelProperty(value = "部门单位名ID ")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "部门单位名 最后一级的名字")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty(value = "类型 ")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "记录创建时间 ")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间时间 ")
    @TableField("update_time")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
