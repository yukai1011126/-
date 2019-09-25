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
 * @since 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "TUserEntity对象", description = "")
public class TUserEntity extends AbstractBaseEntity<TUserEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @TableField("title")
    private String title;

    @TableField("mobile")
    private String mobile;

    @TableField("open_Id")
    private String openId;

    @TableField("nick_name")
    private String nickName;

    @TableField("gender")
    private String gender;

    @TableField("avatar_url")
    private String avatarUrl;

    @TableField("country")
    private String country;

    @TableField("povince")
    private String povince;

    @TableField("city")
    private String city;

    @TableField("memo")
    private String memo;

    @TableField("status")
    private String status;

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
