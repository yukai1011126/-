/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.repo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.gdaas.iard.datafill.wechat.repo.dao.AbstractBaseEntity;
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
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_template_data")
@ApiModel(value = "TTemplateDataEntity对象", description = "")
public class TTemplateDataEntity extends AbstractBaseEntity<TTemplateDataEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @TableField("template_name")
    private String templateName;

    @TableField("template_number")
    private String templateNumber;

    @TableField("dict_id")
    private String dictId;

    @TableField("level1")
    private String level1;

    @TableField("level2")
    private String level2;

    @TableField("level3")
    private String level3;

    @TableField("level4")
    private String level4;

    @TableField("unit")
    private String unit;

    @TableField("data_type")
    private String dataType;

    @TableField("data_length")
    private Integer dataLength;

    @TableField("select_value")
    private String selectValue;

    @TableField("default_value")
    private String defaultValue;

    @TableField("formula")
    private String formula;

    @TableField("sort_number")
    private String sortNumber;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
