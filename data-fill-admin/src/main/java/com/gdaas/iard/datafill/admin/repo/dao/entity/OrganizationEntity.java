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
import java.math.BigDecimal;
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
@TableName("organization")
@ApiModel(value = "OrganizationEntity对象", description = "")
public class OrganizationEntity extends AbstractBaseEntity<OrganizationEntity> {

    @ApiModelProperty(value = "机构ID")
    @TableId(value = "organization_id", type = IdType.AUTO)
    private Integer organizationId;

    @ApiModelProperty(value = "机构编号")
    @TableField("organization_code")
    private String organizationCode;

    @ApiModelProperty(value = "机构名称")
    @TableField("organization_name")
    private String organizationName;

    @ApiModelProperty(value = "机构等级")
    @TableField("organization_level")
    private String organizationLevel;

    @ApiModelProperty(value = "父级机构id")
    @TableField("p_organization_id")
    private Integer porganizationId;

    @ApiModelProperty(value = "父级机构名称")
    @TableField("p_organization_name")
    private String porganizationName;

    @ApiModelProperty(value = "区分社区,自贸区")
    @TableField("communitie")
    private String communitie;

    @ApiModelProperty(value = "X轴坐标")
    @TableField("axis_x")
    private BigDecimal axisX;

    @ApiModelProperty(value = "Y轴坐标")
    @TableField("axis_y")
    private BigDecimal axisY;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @Override
    protected Serializable pkVal() {
        return this.organizationId;
    }
}
