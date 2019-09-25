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
@TableName("t_notice")
@ApiModel(value = "TNoticeEntity对象", description = "")
public class TNoticeEntity extends AbstractBaseEntity<TNoticeEntity> {

    @ApiModelProperty(value = "主键ID ")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "公告标题  ")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "公告摘要 ")
    @TableField("summary")
    private String summary;

    @ApiModelProperty(value = "公告内容 ")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "发布日期 ")
    @TableField("notice_date")
    private Date noticeDate;

    @ApiModelProperty(value = "发布人 ")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "有效期 ")
    @TableField("expire_date")
    private Date expireDate;

    @ApiModelProperty(value = "状态 ")
    @TableField("STATUS")
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
