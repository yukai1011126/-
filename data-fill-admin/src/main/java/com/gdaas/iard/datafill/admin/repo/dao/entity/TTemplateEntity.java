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
@TableName("t_template")
@ApiModel(value = "TTemplateEntity对象", description = "")
public class TTemplateEntity extends AbstractBaseEntity<TTemplateEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "模板名 ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "模板编号 ")
    @TableField("template_number")
    private String templateNumber;

    @ApiModelProperty(value = "模板描述 ")
    @TableField("memo")
    private String memo;

    @ApiModelProperty(value = "配置人员 ")
    @TableField("config_user")
    private String configUser;

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
