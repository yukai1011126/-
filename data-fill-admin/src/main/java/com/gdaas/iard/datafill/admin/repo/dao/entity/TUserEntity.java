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
 * <p>用户表</p>
 *
 * @author like
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "TUserEntity对象", description = "用户表")
public class TUserEntity extends AbstractBaseEntity<TUserEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "姓名 ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "手机 登录名")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "密码 ")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐 ")
    @TableField("na_cl")
    private String naCl;

    @ApiModelProperty(value = "微信openID ")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "微信昵称 ")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "性别 0 未知 1 男 2 女")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "头像URL ")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "国家 ")
    @TableField("country")
    private String country;

    @ApiModelProperty(value = "省 ")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "市  ")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "备注 ")
    @TableField("memo")
    private String memo;

    @ApiModelProperty(value = "状态 1 正常，0 无效")
    @TableField("status")
    private String status;

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
